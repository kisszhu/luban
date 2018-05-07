package com.zhl.design.weather;

/**
 * Created by zhuhailong-dc on 2018/5/1.
 * 主题接口
 */
public interface Subject {

    public void registerObserver(Observer o);
    public void removeObserver(Observer o);
    public void notifyObservers();


}
