package carbonite;

import java.io.Serializable;

class CarboniteData implements Serializable {
    public static final int serialVersionUID = 1;
  
    public final String name;
    public final byte[] bytecode;
  
    public CarboniteData(Object foo) {
	name = foo.getClass().getName();
	bytecode = BytecodeSeer.getInstance().getBytecode(foo.getClass());
    }
}
