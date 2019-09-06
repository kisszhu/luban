package com.zhl.serial.framework.serializer;

import org.apache.thrift.TBase;
import org.apache.thrift.TDeserializer;
import org.apache.thrift.TException;
import org.apache.thrift.TSerializer;
import org.apache.thrift.protocol.TBinaryProtocol;

/**
 * @program codeX
 * @description:
 * @author: meilong
 * @create: 2019/09/06 08:08
 */
public class ThriftSerializer implements ISerializer {

    @Override
    public <T> byte[] serialize(T object) {
        try {
            TSerializer serializer = new TSerializer(new TBinaryProtocol.Factory());
            return serializer.serialize((TBase) object);
        } catch (TException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> T deserialize(byte[] data, Class<T> clazz) {
        try {
            TBase o = (TBase) clazz.newInstance();
            TDeserializer tDeserializer = new TDeserializer();
            tDeserializer.deserialize(o, data);
            return (T) o;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
