package com.zhl.design;

/**
 * @program codeX
 * @description:非堆，测试printStackTrace导致的锁死
 * @author: meilong
 * @create: 2019/08/23 09:12
 */
public class CodeCache {
    public static void main(String[] args) {


        /**
         * -Dcom.sun.management.jmxremote
         * -Dcom.sun.management.jmxremote.port=8011
         * -Dcom.sun.management.jmxremote.ssl=false
         * -Dcom.sun.management.jmxremote.authenticate=false
         */
        while (true) {
            try {
                throw new NullPointerException("抛出空指针异常");
            } catch (NullPointerException ex) {
                ex.printStackTrace();
            }
        }
    }
}
