package carbonite;

import java.io.Closeable;
import java.io.IOException;
import java.io.ObjectOutputStream;

class CarboniteWriter implements Closeable {
    private final ObjectOutputStream _out;

    public CarboniteWriter(ObjectOutputStream out) {
        _out = out;
    }

    /**
     * Closeable
     */
    public void close() throws IOException {
        _out.close();
    }

    /**
     * Write out an object with its bytecode
     * @param obj object to write out
     */
    public void writeObject(Object obj) throws IOException {
        _out.writeObject(new CarboniteData(obj));
        _out.writeObject(obj);
    }

    /**
     * Support for << operator in Groovy
     * @param object to write out
     * @return this
     */
    public CarboniteWriter leftShift(Object obj) throws IOException {
        writeObject(obj);
        return this;
    }
}
