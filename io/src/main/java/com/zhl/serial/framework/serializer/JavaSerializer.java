package com.zhl.serial.framework.serializer;

/**
 * @program codeX
 * @description:
 * @author: meilong
 * @create: 2019/09/05 08:40
 */
public class JavaSerializer implements ISerializer {

    @Override
    public <T> T deserialize(byte[] data, Class<T> clazz) {
        return null;
    }

    @Override
    public <T> byte[] serialize(T object) {
        return new byte[0];
    }
}
