package com.zhl.design;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @program codeX
 * @description:
 * @author: meilong
 * @create: 2019/08/28 12:45
 */
public class Test {
    public static void main(String[] args) {
        Pattern p = Pattern.compile("10000(?:0|3)");
        Matcher m = p.matcher("100002");
        if (m.find()) {
            System.out.println(true);
        } else {
            System.out.println(false);
        }
    }
}
