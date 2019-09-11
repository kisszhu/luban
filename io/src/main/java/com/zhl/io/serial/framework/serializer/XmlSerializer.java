package com.zhl.io.serial.framework.serializer;

import com.thoughtworks.xstream.XStream;

/**
 * @program codeX
 * @description:
 * @author: meilong
 * @create: 2019/09/05 08:58
 */
public class XmlSerializer implements ISerializer {

    private static final XStream stream = new XStream();

    @Override
    public <T> T deserialize(byte[] data, Class<T> clazz) {
        String str = new String(data);
        return (T) stream.fromXML(str);
    }

    @Override
    public <T> byte[] serialize(T object) {
        return stream.toXML(object).getBytes();
    }
}
