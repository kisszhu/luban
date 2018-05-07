package com.zhl.design.weather;

/**
 * Created by zhuhailong-dc on 2018/5/1.
 * 观察者接口
 */
public interface Observer {
    public void update(float temp,float humidity,float pressure);

}
