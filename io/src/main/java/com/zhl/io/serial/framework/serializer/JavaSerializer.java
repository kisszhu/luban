package com.zhl.io.serial.framework.serializer;

import java.io.*;

/**
 * @program codeX
 * @description:
 * @author: meilong
 * @create: 2019/09/05 08:40
 */
public class JavaSerializer implements ISerializer {

    @Override
    public <T> T deserialize(byte[] data, Class<T> clazz) {
        ByteArrayInputStream stream = new ByteArrayInputStream(data);
        try {
            ObjectInputStream ois = new ObjectInputStream(stream);
            return (T) ois.readObject();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public <T> byte[] serialize(T object) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        try {
            ObjectOutputStream oos = new ObjectOutputStream(stream);
            oos.writeObject(object);
            oos.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return stream.toByteArray();
    }
}
