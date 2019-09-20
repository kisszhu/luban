package com.zhl.reflect.proxy.static1;

/**
 * @program codeX
 * @description:
 * @author: meilong
 * @create: 2019/09/20 09:17
 */
public class UserDao implements IUserDao {

    @Override
    public boolean save(String name) {
        System.out.println("目标对象,保持成功：" + name);
        return true;
    }
}
