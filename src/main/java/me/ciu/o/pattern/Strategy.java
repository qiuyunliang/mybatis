package me.ciu.o.pattern;

public class Strategy {

    public interface CalculateStrategy {
        public int handle(int num1, int num2);
    }

    public static class Add implements CalculateStrategy {
        @Override
        public int handle(int num1, int num2) {
            return num1 + num2;
        }
    }

    public static class Sub implements CalculateStrategy {
        @Override
        public int handle(int num1, int num2) {
            return num1 - num2;
        }
    }

    public static class Multiply implements CalculateStrategy {
        @Override
        public int handle(int num1, int num2) {
            return num1 * num2;
        }
    }

    public static class OperationContext {
        private CalculateStrategy strategy;

        public OperationContext(CalculateStrategy strategy) {
            this.strategy = strategy;
        }

        public int execute(int a, int b) {
            return strategy.handle(a, b);
        }
    }

    public static void main(String[] args) {
        OperationContext ctx = new OperationContext(new Add());
        System.out.println("10 + 5 = " + ctx.execute(10, 5));
        ctx = new OperationContext(new Sub());
        System.out.println("10 - 5 = " + ctx.execute(10, 5));
        ctx = new OperationContext(new Multiply());
        System.out.println("10 * 5 = " + ctx.execute(10, 5));
    }
}