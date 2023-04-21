package pushdown_automata.more1than0;

import java.util.Stack;

public abstract class State {
    private final String name;
    protected final Stack<String> stack;

    public State(String name, Stack<String> stack) {
        this.name = name;
        this.stack = stack;
    }

    public abstract boolean changeToState(String leftWord);

    protected boolean changeToNextState(State state, String leftWord){
        System.out.println("Changing to " + state);
        try {
            return state.changeToState(leftWord.substring(1));
        } catch (StringIndexOutOfBoundsException e){
            return state.changeToState("");
        }
    }

    @Override
    public String toString() {
        return name;
    }
}
