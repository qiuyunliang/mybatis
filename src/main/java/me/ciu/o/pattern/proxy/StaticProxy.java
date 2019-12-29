package me.ciu.o.pattern.proxy;

public class StaticProxy {

    public interface Subject {
        void show();
    }

    public class TargetSubject implements Subject {
        @Override
        public void show() {
            System.out.println("TargetSubject show.");
        }
    }

    public class ProxySubject implements Subject {
        private Subject subject;

        ProxySubject(Subject subject) {
            this.subject = subject;
        }

        @Override
        public void show() {
            subject.show();
        }
    }
}