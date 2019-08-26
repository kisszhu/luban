package com.zhl.concurrency.chapter4;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 示例：车辆追踪
 */
public class MonitorVehicleTracker {

    // 基于监视器模式的车辆追踪
    private final Map<String, MutablePoint> locations;

    // 关于实例封闭机制，包装器类要独占容器的所有权，所以需要deepCopy
    public MonitorVehicleTracker(Map<String, MutablePoint> locations) {
        this.locations = deepCopy(locations);
    }

    public synchronized Map<String, MutablePoint> getLocations() {
        return deepCopy(locations);
    }

    public synchronized MutablePoint getLocation(String id) {
        MutablePoint loc = locations.get(id);
        // 防止可变对象 MutablePoint的引用溢出
        return loc == null ? null : new MutablePoint(loc);
    }

    public synchronized void setLocation(String id, int x, int y) {
        MutablePoint loc = locations.get(id);
        if (loc == null) {
            throw new IllegalArgumentException("No such ID: " + id);
        }
        loc.x = x;
        loc.y = y;
    }

    private static Map<String, MutablePoint> deepCopy(Map<String, MutablePoint> m) {
        Map<String, MutablePoint> result = new HashMap<>();
        for (String id : m.keySet()) {
            result.put(id, new MutablePoint(m.get(id)));
        }
        // 返回一个只读的map，不能执行remove、put等操作，会抛异常
        return Collections.unmodifiableMap(result);
    }
}

/**
 * 虽然类MutablePint不是线程安全的，但追踪器类是线程安全的。它所包含的Map对象
 * 和可变的Point对象都未曾发布。当需要返回车辆的位置时，通过MutablePoint拷贝
 * 构造函数或者deepCopy方法来复制正确的值，从而生成一个新的Map对象，并且该对象
 * 中的值与原有Map对象中的key值和value值都相同
 */
class MutablePoint {

    public int x, y;

    public MutablePoint() {
        x = 0;
        y = 0;
    }

    public MutablePoint(MutablePoint p) {
        this.x = p.x;
        this.y = p.y;
    }
}
