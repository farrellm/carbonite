package carbonite;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;
import java.security.ProtectionDomain;
import java.util.HashMap;
import java.util.Map;

import carbonite.SeerAgent;

public class BytecodeSeer {
    // factory
    private static final BytecodeSeer _theInstance;

    static {
        System.out.println("seer loaded");
        _theInstance = new BytecodeSeer(SeerAgent.getInstrumentation());
    }

    /**
     * Factory method
     * @returns the Seer
     */
    public static BytecodeSeer getInstance() {
        return _theInstance;
    }

    // instance
    private final Instrumentation _instr;
    private final Map <Class, byte[]> _cache = new HashMap<>();

    private BytecodeSeer(Instrumentation instr) {
        _instr = instr;
    }

    /**
     * Retrieve the bytecode for a class
     * @param c target class
     * @return bytecode
     */
    public synchronized byte[] getBytecode(Class c) {
        if (!_cache.containsKey(c))
            _cache.put(c, findBytecode(c));
        return _cache.get(c);
    }

    private byte[] findBytecode(Class c) {
        SeerTransformer cft = new SeerTransformer();
        try {
            _instr.addTransformer(cft, true);
            _instr.retransformClasses(c);
            return cft.getBytecode();
        } catch (UnmodifiableClassException e) {
            throw new RuntimeException(e);
        } finally {
            _instr.removeTransformer(cft);
        }
    }
}
