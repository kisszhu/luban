package com.zhl.concurrency.chapter3;

import java.math.BigInteger;
import java.util.Arrays;

/**
 * 使用Volatile类型来发布不可变对象.
 * 每当需要对一组相关数据以原子方式执行某个操作时，就可以考虑创建一个不可变的类来包含这些数据
 */
public class VolatileCachedFactorizer {
    private volatile OneValueCache cache = new OneValueCache(null, null);

    public BigInteger[] service(BigInteger i) {
        BigInteger[] factors = cache.getFactors(i);
        if (factors == null) {
            factors = factor(i);
            cache = new OneValueCache(i, factors);
        }
        return factors;
    }

    public BigInteger[] factor(BigInteger i) {
        //.....
        return null;
    }
}

/**
 * 对于在访问和更新多个相关变量时出现的竞争条件问题，可以通过将这些变量全部保存在一个不可变对象中来消除。
 * 如果是一个可变的对象，那么就必须使用锁来确保原子性。
 */
class OneValueCache {
    private final BigInteger lastNumber;
    private final BigInteger[] lastFactors;

    public OneValueCache(BigInteger i, BigInteger[] factors) {
        lastNumber = i;
        lastFactors = Arrays.copyOf(factors, factors.length);  // 注意如果没有使用copyOf就不是不可变的
    }

    public BigInteger[] getFactors(BigInteger i) {
        if (lastNumber == null || !lastNumber.equals(i)) {
            return null;
        } else {
            return Arrays.copyOf(lastFactors, lastFactors.length); // 注意如果没有使用copyOf就不是不可变的
        }
    }
}


