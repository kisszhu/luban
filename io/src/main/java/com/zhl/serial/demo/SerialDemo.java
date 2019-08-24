package com.zhl.serial.demo;

import java.io.*;

/**
 * @program codeX
 * @description:序列化/反序列化Dmoe
 * @author: meilong
 * @create: 2019/08/24 11:10
 */
public class SerialDemo {

    public static void main(String[] args) throws Exception {
        // 序列化
        FileOutputStream fos = new FileOutputStream("user.out");
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        User user = new User("张三", "12345");
        oos.writeObject(user);
        oos.flush();
        oos.close();

        FileInputStream fis = new FileInputStream("user.out");
        ObjectInputStream ois = new ObjectInputStream(fis);
        User obj = (User) ois.readObject();
        System.out.println(obj.getUserName());
        System.out.println(obj.getPassword());
    }

    /**
     * writeObject的代码中，可以看到下面注释掉的代码。
     * 判断了String、Array、Enum、判断了 obj instanceof Serializable
     *
     * 再来看下serializable接口类的作用，它仅仅是一个标签，关键的是”谁”在检查和使用这个标签。比如jackson就不太在乎这个标签，
     * 即使没有实现serializable接口，也可以被序列化。
     * so serializable并没有特殊作用，只是很多的序列化库会检查一下而己，这样可以避免在你不知道的情况下把不想公开的数据给序列化了
     */

//    // remaining cases
//            if (obj instanceof String) {
//        writeString((String) obj, unshared);
//    } else if (cl.isArray()) {
//        writeArray(obj, desc, unshared);
//    } else if (obj instanceof Enum) {
//        writeEnum((Enum<?>) obj, desc, unshared);
//    } else if (obj instanceof Serializable) {
//        writeOrdinaryObject(obj, desc, unshared);
//    } else {
//        if (extendedDebugInfo) {
//            throw new NotSerializableException(
//                    cl.getName() + "\n" + debugInfoStack.toString());
//        } else {
//            throw new NotSerializableException(cl.getName());
//        }
//    }
}
