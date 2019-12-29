package me.ciu.o.pattern;

public class Facade {

    public interface Shape {
        void draw();
    }

    public class Square implements Shape {
        @Override
        public void draw() {
            System.out.println("Square::draw()");
        }
    }

    public class Rectangle implements Shape {
        @Override
        public void draw() {
            System.out.println("Rectangle::draw()");
        }
    }

    public class ShapeMaker {
        private Shape rectangle;
        private Shape square;

        public ShapeMaker() {
            rectangle = new Rectangle();
            square = new Square();
        }

        public void draw() {
            rectangle.draw();
            square.draw();
        }
    }
}