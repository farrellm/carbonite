package carbonite;

import java.io.IOException;
import java.io.ObjectOutputStream;

class CarboniteWriter {
    private final ObjectOutputStream _out;

    public CarboniteWriter(ObjectOutputStream out) {
	_out = out;
    }

    public void writeObject(Object obj) throws IOException {
	_out.writeObject(new CarboniteData(obj));
	_out.writeObject(obj);
    }
}
