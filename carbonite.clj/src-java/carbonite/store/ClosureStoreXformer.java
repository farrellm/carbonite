package carbonite.store;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

import com.sun.tools.attach.VirtualMachine;

public class ClosureStoreXformer implements ClassFileTransformer
{
    public byte[] transform(ClassLoader loader, String className,
			    Class<?> classBeingRedefined, ProtectionDomain protectionDomain,
			    byte[] classfileBuffer)
  	throws IllegalClassFormatException
    {
	byte[] transformed = null;

	System.out.println("Transforming " + className);

	return transformed;
    }


    static class POSIX {
	static { System.loadLibrary ("POSIX"); }
	native static int getpid ();
    }

    public static void attach() {
	
    }
}
