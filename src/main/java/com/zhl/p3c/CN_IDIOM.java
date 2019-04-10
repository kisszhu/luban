package com.zhl.p3c;

public class CN_IDIOM implements Cloneable {
    /**
     * 实现Cloneable接口的类应该重写Object.clone。Cloneable接口不包含clone方法。
     * 因此，某个对象实现了这个接口，还需要实现clone方法
     * Bad practice
     *     Bad implementation of cloneable idiom
     *         Class implements Cloneable but does not define or use clone method
     */
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}


