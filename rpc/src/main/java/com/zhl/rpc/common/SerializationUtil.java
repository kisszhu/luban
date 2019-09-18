package com.zhl.rpc.common;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.Schema;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @program codeX
 * @description:序列化工具类（基于Protostff实现）用于把对象序列化字节数组，把字节数组反序列化对象
 * @author: meilong
 * @create: 2019/09/03 08:38
 */
public class SerializationUtil {
    private static Map<Class<?>, Schema> cachedSchema = new ConcurrentHashMap<>();
    private static Objenesis objenesis = new ObjenesisStd(true);

    private SerializationUtil() {
    }

    /**
     * 序列化（对象->字节数组）
     *
     * @param obj
     * @param <T>
     * @return
     */
    public static <T> byte[] serialize(T obj) {
        Class clazz = obj.getClass();
        LinkedBuffer buffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);
        Schema schema = getSchema(clazz);
        byte[] res = ProtostuffIOUtil.toByteArray(obj, schema, buffer);
        buffer.clear();
        return res;
    }

    /**
     * 反序列化（字节数组->对象）
     *
     * @param data
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T deserialize(byte[] data, Class<T> clazz) {
        /**
         * 如果一个类没有参数为空的构造方法时，那么你直接调用newInstance方法试图得到一个实例对象的时候是会抛出异常的
         * 通过Objenesis可以避开这个问题
         */
        T message = objenesis.newInstance(clazz);
        Schema<T> schema = getSchema(clazz);
        ProtostuffIOUtil.mergeFrom(data, message, schema);
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