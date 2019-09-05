package com.zhl.serial.framework.serializer;

/**
 * @program codeX
 * @description:
 * @author: meilong
 * @create: 2019/09/05 08:36
 */
public interface ISerializer {
    public <T> byte[] serialize(T object);

    public <T> T deserialize(byte[] data, Class<T> clazz);
}
