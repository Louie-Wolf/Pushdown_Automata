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

    @Override
    public String toString() {
        return name;
    }
}
