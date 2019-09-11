package com.zhl.io.serial.framework.serializer;

import org.apache.commons.lang3.reflect.MethodUtils;

/**
 * @program codeX
 * @description:TODO c x d y
 * @author: meilong
 * @create: 2019/09/06 08:19
 */
public class ProtoBufSerializer implements ISerializer {

    @Override
    public <T> byte[] serialize(T object) {
        try {
            return (byte[]) MethodUtils.invokeMethod(object, "toByteArray");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public <T> T deserialize(byte[] data, Class<T> clazz) {
        try {
            Object o = MethodUtils.invokeStaticMethod(clazz, "getDefaultInstance");
            return (T) MethodUtils.invokeMethod(o, "parseFrom", new Object[]{data});
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
