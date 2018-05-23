package com.zhl.design;

import java.lang.reflect.ReflectPermission;

/**
 * Created by zhuhailong-dc on 2018/5/23.
 */
public class MyClass {
    private MyClass() {
        SecurityManager sm = new SecurityManager();
        if (sm != null) {
            sm.checkPermission(new ReflectPermission("suppressAccessChecks"));
        }
    }
}
