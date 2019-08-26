package com.zhl.concurrency.chapter10;

import java.util.HashSet;
import java.util.Set;

/**
 * 在协作对象之间发生的死锁.
 */
public class Chapter_10_5 {

    class Taxi {

        private Point location, destination;
        private final Dispatcher dispatcher;

        public Taxi(Dispatcher dispatcher) {
            this.dispatcher = dispatcher;
        }

        public synchronized Point getLocation() {
            return location;
        }

        public synchronized void setLocation(Point location) {  //线程A: taxi
            this.location = location;
            if (location.equals(destination)) {
                dispatcher.notifyAvailable(this);  //线程A: dispatcher
            }
        }
    }

    class Dispatcher {
        private final Set<Taxi> taxis;
        private final Set<Taxi> availableTaxis;

        public Dispatcher() {
            this.taxis = new HashSet<>();
            this.availableTaxis = new HashSet<>();
        }

        public synchronized void notifyAvailable(Taxi taxi) {
            availableTaxis.add(taxi);
        }

        public synchronized Image getImage() {  //线程B： dispatcher
            Image image = new Image();
            for (Taxi t : taxis) {
                image.drawMarker(t.getLocation());  //线程B： taxi
            }
            return image;
        }
    }


    class Point {
    }

    class Image {
        public void drawMarker(Point point) {
        }
    }
}


