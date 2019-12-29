package me.ciu.o.pattern.factory;

public class AbstractFactory {

    public interface Mouse {
        void work();
    }

    public static class DellMouse implements Mouse {
        @Override
        public void work() {
            System.out.println("DellMouse working.");
        }
    }

    public static class AppleMouse implements Mouse {
        @Override
        public void work() {
            System.out.println("AppleMouse working.");
        }
    }

    public interface KeyBroad {
        void work();
    }

    public static class DellKeyBroad implements KeyBroad {
        @Override
        public void work() {
            System.out.println("DellKeyBroad working.");
        }
    }

    public static class AppleKeyBroad implements KeyBroad {
        @Override
        public void work() {
            System.out.println("Apple KeyBroad working.");
        }
    }

    public static abstract class AbstractDeviceFactory {
        public abstract Mouse createMouse();
        public abstract KeyBroad createKeyBroad();
    }

    public static class DellFactory extends AbstractDeviceFactory {
        @Override
        public Mouse createMouse() {
            return new DellMouse();
        }

        @Override
        public KeyBroad createKeyBroad() {
            return new DellKeyBroad();
        }
    }

    public static class AppleFactory extends AbstractDeviceFactory {
        @Override
        public Mouse createMouse() {
            return new AppleMouse();
        }

        @Override
        public KeyBroad createKeyBroad() {
            return new AppleKeyBroad();
        }
    }

    public static void main(String[] args) {
        AbstractDeviceFactory dellFactory = new DellFactory();
        Mouse mouse = dellFactory.createMouse();
        mouse.work();
        AbstractDeviceFactory appleFactory = new AppleFactory();
        KeyBroad keyBroad = appleFactory.createKeyBroad();
        keyBroad.work();
    }
}