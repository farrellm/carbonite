package carbonite;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;
import java.security.ProtectionDomain;
import java.util.HashMap;
import java.util.Map;

public class BytecodeSeer {
    public static Instrumentation _instr;
    public static void setInstrumentation(Instrumentation instr) {
	_instr = instr;
    }

    // factory
    private static BytecodeSeer _theInstance = null;

    public static synchronized BytecodeSeer getInstance() {
	if (_theInstance == null)
	    _theInstance = new BytecodeSeer();
	return _theInstance;
    }

    // instance
    private final Map <Class, byte[]> _cache = new HashMap<>();

    public synchronized byte[] getBytecode(Class c) {
	if (!_cache.containsKey(c))
            _cache.put(c, findBytecode(c));
	return _cache.get(c);
    }

    public static byte[] findBytecode(Class c) {
	if (_instr == null)
	    throw new RuntimeException(
                "Instrumentation not loaded. Please restart your JVM " +
		"and call SeerAgent.load()");

	SeerTransformer cft = new SeerTransformer();
	try {
	    _instr.addTransformer(cft, true);
	    _instr.retransformClasses(c);
	    return cft.getBytecode();
	    // return null;
	} catch (UnmodifiableClassException e) {
	    throw new RuntimeException(e);
	} finally {
	    _instr.removeTransformer(cft);
	}
    }
}
