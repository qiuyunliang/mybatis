package me.ciu.o.pattern.factory;

public class FactoryMethod {

    public interface Sender {
        void send();
    }

    public static class Sms implements Sender {
        @Override
        public void send() {
            System.out.println("send a sms.");
        }
    }

    public static class Email implements Sender {
        @Override
        public void send() {
            System.out.println("send a Email.");
        }
    }

    public interface Provider {
        Sender produce();
    }

    public static class SmsFactory implements Provider {
        @Override
        public Sms produce() {
            return new Sms();
        }
    }

    public static class EmailFactory implements Provider {
        @Override
        public Sender produce() {
            return new Email();
        }
    }

    public static void main(String[] args) {
        Sender email = new EmailFactory().produce();
        email.send();
        Sender sms = new SmsFactory().produce();
        sms.send();
    }
}