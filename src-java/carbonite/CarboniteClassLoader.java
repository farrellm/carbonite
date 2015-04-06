package carbonite;

import java.util.Map;

class CarboniteClassLoader extends ClassLoader {
    public CarboniteData _cd;

    CarboniteClassLoader(ClassLoader cl) {
        super(cl);
    }

    void setData(CarboniteData cd) {
        _cd = cd;
    }

    @Override
    protected Class findClass(String name) throws ClassNotFoundException {
        if (_cd != null && name.equals(_cd.name)) {
            try {
                return defineClass(name, _cd.bytecode, 0, _cd.bytecode.length);
            } finally {
                _cd = null;
            }
        } else {
            return super.findClass(name);
        }
    }
}
