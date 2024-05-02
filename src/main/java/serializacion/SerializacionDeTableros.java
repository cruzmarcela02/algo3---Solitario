package serializacion;

import java.io.*;

public class SerializacionDeTableros implements Serializable {
    public SerializacionDeTableros() { }

    public void serializar(String nombreArchivo, Serializable objeto) throws IOException {
        var fos = new FileOutputStream(nombreArchivo);
        var buffer = new BufferedOutputStream(fos);
        var o = new ObjectOutputStream(buffer);
        o.writeObject(objeto);
        o.close();
    }

    public Serializable deserializar(String nombreArchivo) throws IOException, ClassNotFoundException {
        var fis = new FileInputStream(nombreArchivo);
        var buffer = new BufferedInputStream(fis);
        var o = new ObjectInputStream(buffer);

        var objeto = (Serializable) o.readObject();
        o.close();
        return objeto;
    }
}
