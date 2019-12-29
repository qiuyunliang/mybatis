package me.ciu.o.pattern;

import java.util.ArrayList;
import java.util.List;

public class Observer {

    public static abstract class UpdateObserver {
        protected Subject subject;

        public abstract void update();
    }

    public static class BinaryObserver extends UpdateObserver {
        public BinaryObserver(Subject subject) {
            this.subject = subject;
            this.subject.attach(this);
        }

        @Override
        public void update() {
            System.out.println("Binary String: " + Integer.toBinaryString(subject.getState()));
        }
    }

    public static class Subject {
        private List<UpdateObserver> observers = new ArrayList<UpdateObserver>();
        private int state;

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
            notifyAllObservers();
        }

        public void attach(UpdateObserver observer) {
            observers.add(observer);
        }

        public void notifyAllObservers() {
            for (UpdateObserver observer : observers) {
                observer.update();
            }
        }
    }

    public static void main(String[] args) {
        Subject subject = new Subject();
        new BinaryObserver(subject);
        System.out.println("First state change: 15");
        subject.setState(15);
        System.out.println("Second state change: 10");
        subject.setState(10);
    }
}