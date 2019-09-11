package com.zhl.io.serial.framework.serializer;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtobufIOUtil;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.Schema;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @program codeX
 * @description:
 * @author: meilong
 * @create: 2019/09/06 08:11
 */
public class ProtoStuffSerializer implements ISerializer {

    private static Map<Class<?>, Schema> cachedSchema = new ConcurrentHashMap<>();
    private static Objenesis objenesis = new ObjenesisStd(true);

    @Override
    public <T> byte[] serialize(T object) {
        Class clazz = object.getClass();
        LinkedBuffer buffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);
        Schema schema = getSchema(clazz);
        byte[] res = ProtostuffIOUtil.toByteArray(object, schema, buffer);
        buffer.clear();
        return res;
    }

    @Override
    public <T> T deserialize(byte[] data, Class<T> clazz) {
        /**
         * 如果一个类没有参数为空的构造方法时，那么你直接调用newInstance方法试图得到一个实例对象的时候是会抛出异常的
         * 通过Objenesis可以避开这个问题
         */
        T message = objenesis.newInstance(clazz);
        Schema<T> schema = getSchema(clazz);
        ProtobufIOUtil.mergeFrom(data, message, schema);
        return message;
    }

    /**
     * 获取类的schema
     *
     * @param clazz
     * @param <T>
     * @return
     */
    private static <T> Schema<T> getSchema(Class<T> clazz) {
        Schema schema = cachedSchema.get(clazz);
        if (schema == null) {
            schema = RuntimeSchema.createFrom(clazz);
            if (schema != null) {
                cachedSchema.put(clazz, schema);
            }
        }
        return schema;
    }
}
