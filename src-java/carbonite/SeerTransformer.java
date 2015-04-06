package carbonite;

import java.lang.instrument.ClassFileTransformer;
import java.security.ProtectionDomain;

public class SeerTransformer implements ClassFileTransformer {
    private byte[] _bytecode = null;

    public byte[] getBytecode() {
        if (_bytecode == null)
            throw new RuntimeException("Bytecode not set");

        return _bytecode;
    }

    public byte[] transform(ClassLoader loader, String className,
                            Class<?> classBeingRedefined,
                            ProtectionDomain protectionDomain,
                            byte[] classfileBuffer)
    {
        if (_bytecode != null)
            throw new RuntimeException("Resetting bytecode");

        _bytecode = classfileBuffer;
        return classfileBuffer;
    }
}
