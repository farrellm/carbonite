package carbonite;

import java.io.IOException;
import java.lang.instrument.Instrumentation;
import java.lang.management.ManagementFactory;

import com.sun.tools.attach.AgentInitializationException;
import com.sun.tools.attach.AgentLoadException;
import com.sun.tools.attach.AttachNotSupportedException;
import com.sun.tools.attach.VirtualMachine;

public class SeerAgent {
    public static final String AGENT_KEY = "carbonite.agent.installed";

    private static Instrumentation _instr;

    /**
     * Attach the SeerAgent.  Must be called before getInstrumentation
     */
    private static synchronized void attach() {
        try {
            if (System.getProperty(AGENT_KEY) == null) {
                String path = SeerAgent.class.getProtectionDomain()
                    .getCodeSource().getLocation().getPath();
                String pid = ManagementFactory.getRuntimeMXBean().getName().split("@")[0];
                VirtualMachine vm = VirtualMachine.attach(pid);

                vm.loadAgent(path);
            }
        } catch (AttachNotSupportedException | AgentInitializationException |
                 AgentLoadException | IOException e) {
            throw new CarboniteLoadException(e);
        }
    }

    public static void agentmain(String args, Instrumentation instr) {
        _instr = instr;
        System.setProperty(AGENT_KEY, "true");
    }

    /**
     * @return instrumentation
     */
    public static Instrumentation getInstrumentation() {
        if (_instr == null)
            attach();
        return _instr;
    }
}
