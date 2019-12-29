package me.ciu.o.pattern;

public class Bridge {

    public interface Software {
        public void run();
    }

    public static class Camera implements Software {
        @Override
        public void run() {
            System.out.println("take a photo.");
        }
    }

    public static abstract class Phone {
        protected Software software;

        public void setSoftware(Software software) {
            this.software = software;
        }

        public abstract void run();
    }

    public static class iPhone extends Phone {
        @Override
        public void run() {
            software.run();
        }
    }

    public static void main(String[] args) {
        Software software = new Camera();
        Phone iphone = new iPhone();
        iphone.setSoftware(software);
        iphone.run();
    }
}