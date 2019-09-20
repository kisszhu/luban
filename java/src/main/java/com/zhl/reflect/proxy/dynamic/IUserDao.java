package com.zhl.reflect.proxy.dynamic;

/**
 * @program codeX
 * @description:目标对象&代理对象需要实现的接口
 * @author: meilong
 * @create: 2019/09/20 09:16
 */
public interface IUserDao {
    /**
     * 保存
     *
     * @param name
     * @return
     */
    boolean save(String name);
}
