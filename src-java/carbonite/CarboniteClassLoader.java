package carbonite;

import java.util.Map;

class CarboniteClassLoader extends ClassLoader {
    Map<Object, Object> _cd;
  
    CarboniteClassLoader(ClassLoader cl) {
	super(cl);
    }
  
    void setData(Map<Object, Object> cd) {
	_cd = cd;
    }
  
    @Override
    protected Class findClass(String name) throws ClassNotFoundException {
	if (_cd != null && name.equals(_cd.get("name"))) {
	    byte[] bytecode = (byte[])_cd.get("bytecode");
	    return defineClass(name, bytecode, 0, bytecode.length);
	} else {
	    return super.findClass(name);
	}
    }
}
