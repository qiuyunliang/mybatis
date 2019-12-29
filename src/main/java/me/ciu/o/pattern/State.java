package me.ciu.o.pattern;

public class State {

    public interface RunningState {
        public void doAction(StateContext context);
    }

    public static class StartState implements RunningState {
        public void doAction(StateContext context) {
            System.out.println("Player is in start state");
            context.setState(this);
        }

        public String toString() {
            return "Start State";
        }
    }

    public static class StopState implements RunningState {
        public void doAction(StateContext context) {
            System.out.println("Player is in stop state");
            context.setState(this);
        }

        public String toString() {
            return "Stop State";
        }
    }

    public static class StateContext {
        private RunningState state;

        public StateContext() {
            state = null;
        }

        public void setState(RunningState state) {
            this.state = state;
        }

        public RunningState getState() {
            return state;
        }
    }

    public static void main(String[] args) {
        StateContext context = new StateContext();
        StartState startState = new StartState();
        startState.doAction(context);
        System.out.println(context.getState().toString());
        StopState stopState = new StopState();
        stopState.doAction(context);
        System.out.println(context.getState().toString());
    }
}