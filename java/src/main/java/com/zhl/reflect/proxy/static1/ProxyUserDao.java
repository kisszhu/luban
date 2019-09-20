package com.zhl.reflect.proxy.static1;

/**
 * @program codeX
 * @description:
 * @author: meilong
 * @create: 2019/09/20 09:18
 */
public class ProxyUserDao implements IUserDao {

    private IUserDao userDao;

    public ProxyUserDao(IUserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public boolean save(String name) {
        System.out.println("代理对象,保存成功：" + name);
        userDao.save(name);
        return true;
    }
}
