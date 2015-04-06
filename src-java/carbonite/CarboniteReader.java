package carbonite;

import java.io.Closeable;
import java.io.IOException;
import java.io.ObjectInputStream;

class CarboniteReader implements Closeable {
    private final ObjectInputStream _in;
    private final CarboniteClassLoader _cl;

    public CarboniteReader(ObjectInputStream in, CarboniteClassLoader cl) {
        _in = in;
        _cl = cl;
    }

    /**
     * Read the next object, loading the class if necessary
     * @return object
     */
    public Object readObject() throws IOException, ClassNotFoundException {
        Object obj = _in.readObject();

        if (obj instanceof CarboniteData) {
            _cl.setData((CarboniteData)obj);
            return _in.readObject();
        } else {
            return obj;
        }
    }

    /**
     * Closable
     */
    public void close() throws IOException {
	_in.close();
    }
}
