package com.zhl.concurrency.chapter10;

import java.util.HashSet;
import java.util.Set;

/**
 * 通过公开调用来避免在相互协作的对象之间产生死锁.
 */
public class Chapter_10_6 {


    class Taxi {

        private Point location, destination;
        private final Dispatcher dispatcher;

        public Taxi(Dispatcher dispatcher) {
            this.dispatcher = dispatcher;
        }

        public synchronized Point getLocation() {
            return location;
        }

        public void setLocation(Point location) {
            boolean reachedDestination;
            synchronized (this) {
                this.location = location;
                reachedDestination = location.equals(destination);
            }
            if (reachedDestination) {
                dispatcher.notifyAvailable(this);
            }
            // this.location = location;
            // if (location.equals(destination)) {
            //     dispatcher.notifyAvailable(this);
            // }
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

        public Image getImage() {
            Set<Taxi> copy;
            synchronized (this) {
                copy = new HashSet<>(taxis);
            }

            Image image = new Image();
            for (Taxi t : copy) {
                image.drawMarker(t.getLocation());
            }
            return image;

            // Image image = new Image();
            // for (Taxi t : taxis) {
            //     image.drawMarker(t.getLocation());
            // }
            // return image;
        }
    }


    class Point {
    }

    class Image {
        public void drawMarker(Point point) {
        }
    }
}











