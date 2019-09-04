package com.zhl.register.proxy.dynamic;


public interface PersonBean {
    public String getName();

    public void setName(String name);

    public String getGender();

    public void setGender(String gender);

    public String getInterests();

    public void setInterests(String interests);

    int getHotOrNotRating();

    void setHotOrNotRating(int num);
}
