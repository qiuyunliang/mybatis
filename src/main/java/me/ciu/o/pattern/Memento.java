package me.ciu.o.pattern;

import java.util.ArrayList;
import java.util.List;

public class Memento {

    public static class StateMemento {
        private String state;

        public StateMemento(String state) {
            this.state = state;
        }

        public String getState() {
            return state;
        }
    }

    public static class CareTaker {
        private List<StateMemento> mementoList = new ArrayList<StateMemento>();

        public void add(StateMemento state) {
            mementoList.add(state);
        }

        public StateMemento get(int index) {
            return mementoList.get(index);
        }
    }

    public static class Originator {
        private String state;

        public void setState(String state) {
            this.state = state;
        }

        public String getState() {
            return state;
        }

        public StateMemento saveStateToMemento() {
            return new StateMemento(state);
        }

        public void getStateFromMemento(StateMemento memento) {
            state = memento.getState();
        }
    }

    public static void main(String[] args) {
        Originator originator = new Originator();
        CareTaker careTaker = new CareTaker();
        originator.setState("State #1");
        originator.setState("State #2");
        careTaker.add(originator.saveStateToMemento());
        originator.setState("State #3");
        careTaker.add(originator.saveStateToMemento());
        originator.setState("State #4");
        System.out.println("Current State: " + originator.getState());
        originator.getStateFromMemento(careTaker.get(0));
        System.out.println("First saved State: " + originator.getState());
        originator.getStateFromMemento(careTaker.get(1));
        System.out.println("Second saved State: " + originator.getState());
    }
}