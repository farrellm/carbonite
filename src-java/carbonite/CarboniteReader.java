package carbonite;

import java.io.IOException;
import java.io.ObjectInputStream;

class CarboniteReader  {
    private final ObjectInputStream _in;
    private final CarboniteClassLoader _cl;
    
    private CarboniteData _data;
    
    public CarboniteReader(ObjectInputStream in, CarboniteClassLoader cl) {
        _in = in;
	_cl = cl;
    }

    public Object readObject() throws IOException, ClassNotFoundException {
	Object obj = _in.readObject();

	if (obj instanceof CarboniteData) {
	    _cl.setData((CarboniteData)obj);
            return _in.readObject();
	} else {
	    return obj;
	}
    }
}
