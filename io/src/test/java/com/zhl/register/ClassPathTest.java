package com.zhl.register;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

/**
 * @program codeX
 * @description:读取ClassPath下文件的几种方式
 * @author: meilong
 * @create: 2019/08/22 09:47
 */
public class ClassPathTest {
    public static void main(String[] args) {
        /**
         * note:
         * 如果提示空指针异常的话，去查看target/test-classes/目录下是否存在test_classpath.properties,即这个文件是否被编译了，
         * 如果不存在，则添加如下配置：
         * <resource>
         *     <directory>src/test/resources</directory>
         *     <includes>
         *         <include>*.properties</include >
         *     </includes >
         * </resource >
         */
        ClassPathTest test = new ClassPathTest();

        // 根据当前类 获取resource
        String classpath = test.getClass().getResource("/test_classpath.properties").getPath();
        System.out.println(classpath);

        InputStream in = test.getClass().getResourceAsStream("/test_classpath.properties");
        String content = readStream(in);
        System.out.println("输出内容为：" + content);

        // 根据ClassLoader
        InputStream in2 = Thread.currentThread().getContextClassLoader().getResourceAsStream("test_classpath.properties");
        System.out.println("输出内容为：" + readStream(in2));

        InputStream in3 = test.getClass().getClassLoader().getResourceAsStream("test_classpath.properties");
        System.out.println("输出内容为：" + readStream(in3));
    }


    /**
     * 读取InputStream 到 String 字符串
     *
     * @param in
     * @return
     */
    public static String readStream(InputStream in) {
        try {
            // 创建字节数组输出流，用来输出读取到的内容
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            // 创建缓存大小 1kb
            byte[] buffer = new byte[1024];
            // 每次读取到内容的长度
            int len = -1;
            // 开始读取输入流中的内容
            while ((len = in.read(buffer)) != -1) {
                // 把读取到的内容写出输出流中
                output.write(buffer, 0, len);
            }
            // 把字节数组转换为字符串
            String content = output.toString();
            // 关闭输入流和输出流
            in.close();
            output.close();

            return content;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}