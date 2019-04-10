package com.zhl.p3c;

public class BC_EQUALS_METHOD_SHOULD_WORK_FOR_ALL_OBJECTS {
}

class Equals {

    /**
     * BC_EQUALS_METHOD_SHOULD_WORK_FOR_ALL_OBJECTS
     */
    public boolean equals(Object o) {
        /**
         * 当你在实现类的equals方法时，不应该对参数有任何的预先设定。
         * Bad practice
         *     Bad casts of object references
         *          BC_EQUALS_METHOD_SHOULD_WORK_FOR_ALL_OBJECTS method should not assume anything about the type of its argument
         */
        Equals equals = (Equals) o;

        // correct
        if (this == o) {
            return true;
        }
        if (o instanceof Equals) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
