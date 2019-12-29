package me.ciu.o.pattern;

import java.util.Hashtable;

public class Prototype {

    public static abstract class Shape implements Cloneable {
        private int id;
        private String type;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        abstract void draw();

        @Override
        protected Object clone() {
            Object object = null;
            try {
                object = super.clone();
            } catch (CloneNotSupportedException e) {
            }
            return object;
        }
    }

    public static class Circle extends Shape {

        public Circle(int id, String type) {
            super.setId(id);
            super.setType(type);
        }

        @Override
        void draw() {
            System.out.println("draw a circle.");
        }
    }

    public static class Square extends Shape {

        public Square(int id, String type) {
            super.setId(id);
            super.setType(type);
        }

        @Override
        void draw() {
            System.out.println("draw a square.");
        }
    }

    public static class ShapeCache {
        private static final Hashtable<Integer, Shape> caches = new Hashtable<>();

        public static Shape get(int id) {
            return (Shape) caches.get(id).clone();
        }

        public static void load() {
            Circle circle = new Circle(1, "Circle");
            Square square = new Square(2, "Square");
            caches.put(circle.getId(), circle);
            caches.put(square.getId(), square);
        }
    }

    public static void main(String[] args) {
        ShapeCache.load();
        Shape cloneCircle = ShapeCache.get(1);
        Shape cloneSquare = ShapeCache.get(2);
    }
}