package com.zhl.io.serial.framework.serializer;

import org.apache.avro.io.*;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

/**
 * @program codeX
 * @description:
 * @author: meilong
 * @create: 2019/09/06 07:57
 */
public class AvroSerializer implements ISerializer {

    @Override
    public <T> byte[] serialize(T object) {
        try {
            DatumWriter userDatumWriter = new SpecificDatumWriter(object.getClass());
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            BinaryEncoder binaryEncoder = EncoderFactory.get().directBinaryEncoder(outputStream, null);
            userDatumWriter.write(object, binaryEncoder);
            return outputStream.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> T deserialize(byte[] data, Class<T> clazz) {
        try {
            DatumReader userDatumReader = new SpecificDatumReader(clazz);
            BinaryDecoder binaryDecoder = DecoderFactory.get().directBinaryDecoder(new ByteArrayInputStream(data), null);
            return (T) userDatumReader.read(clazz.newInstance(), binaryDecoder);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
