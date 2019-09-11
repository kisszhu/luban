package com.zhl.io.serial.framework.serializer;

/**
 * @program codeX
 * @description:
 * @author: meilong
 * @create: 2019/09/05 08:36
 */
public interface ISerializer {
    <T> byte[] serialize(T object);

    <T> T deserialize(byte[] data, Class<T> clazz);
}
