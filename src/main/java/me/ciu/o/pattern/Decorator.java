package me.ciu.o.pattern;

public class Decorator {
    public interface Shape {
        void draw();
    }

    public static class Rectangle implements Shape {
        @Override
        public void draw() {
            System.out.println("Shape: Rectangle");
        }
    }

    public static abstract class ShapeDecorator implements Shape {
        protected Shape decoratedShape;

        public ShapeDecorator(Shape decoratedShape) {
            this.decoratedShape = decoratedShape;
        }

        public void draw() {
            decoratedShape.draw();
        }
    }

    public static class RedShapeDecorator extends ShapeDecorator {
        public RedShapeDecorator(Shape decoratedShape) {
            super(decoratedShape);
        }

        @Override
        public void draw() {
            decoratedShape.draw();
            setRedBorder(decoratedShape);
        }

        private void setRedBorder(Shape decoratedShape) {
            System.out.println("Border Color: Red");
        }
    }

    public static void main(String[] args) {
        Shape rectangle = new Rectangle();
        Shape redRectangle = new RedShapeDecorator(new Rectangle());
        System.out.println("Rectangle with normal border");
        rectangle.draw();
        System.out.println("Rectangle of red border");
        redRectangle.draw();
    }
}