package carbonite;

import java.lang.management.ManagementFactory;
import java.io.IOException;
import com.sun.tools.attach.AgentInitializationException;
import com.sun.tools.attach.AgentLoadException;
import com.sun.tools.attach.AttachNotSupportedException;
import com.sun.tools.attach.VirtualMachine;

class CarboniteLoader {
    public static final String AGENT_KEY = "carbonite.agent.installed";

    static void load() {
	try {
	    if (System.getProperty(AGENT_KEY) == null) {
		String path = CarboniteLoader.class.getProtectionDomain()
		    .getCodeSource().getLocation().getPath();
		String pid = ManagementFactory.getRuntimeMXBean().getName().split("@")[0];
		VirtualMachine vm = VirtualMachine.attach(pid);

		vm.loadAgent(path);
		System.setProperty(AGENT_KEY, "true");
	    }
	} catch (AttachNotSupportedException | AgentInitializationException |
		 AgentLoadException | IOException e) {
	    throw new CarboniteLoadException(e);
	}
    }
}
