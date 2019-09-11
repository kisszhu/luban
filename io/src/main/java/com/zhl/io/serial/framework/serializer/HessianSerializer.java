package com.zhl.io.serial.framework.serializer;

import com.caucho.hessian.io.HessianInput;
import com.caucho.hessian.io.HessianOutput;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @program codeX
 * @description:支持跨语言传输的二进制序列化协议
 * @author: meilong
 * @create: 2019/09/06 07:42
 */
public class HessianSerializer implements ISerializer {

    @Override
    public <T> T deserialize(byte[] data, Class<T> clazz) {
        if (data == null) {
            throw new NullPointerException();
        }
        ByteArrayInputStream input = new ByteArrayInputStream(data);
        HessianInput hessianInput = new HessianInput(input);
        try {
            return (T) hessianInput.readObject();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    @Override
    public <T> byte[] serialize(T object) {
        if (object == null) {
            throw new NullPointerException();
        }
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        HessianOutput hessianOutput = new HessianOutput(output);
        try {
            hessianOutput.writeObject(object);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return output.toByteArray();
    }
}
