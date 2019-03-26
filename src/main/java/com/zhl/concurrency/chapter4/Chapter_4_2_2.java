package com.zhl.concurrency.chapter4;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 示例：车辆追踪,Java监视器模式
 */
public class Chapter_4_2_2 {

    // 基于监视器模式的车辆追踪
    private final Map<String, MutablePoint> locations;

    public Chapter_4_2_2(Map<String, MutablePoint> locations) {
        this.locations = deepCopy(locations); //TODO
    }

    public synchronized Map<String, MutablePoint> getLocations() {
        return deepCopy(locations);
    }

    public synchronized MutablePoint getLocation(String id) {
        MutablePoint loc = locations.get(id);
        //TODO 防止引用逸出
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
        //TODO
        return Collections.unmodifiableMap(result);
    }

}

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
