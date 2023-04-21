package pushdown_automata.more1than0;

import java.util.EmptyStackException;
import java.util.Stack;

public class Q1 extends State{
    private Q0 q0;
    private Q2 q2;
    public Q1(String name, Stack<String> stack) {
        super(name, stack);
    }

    public void setStates(Q0 q0, Q2 q2) {
        this.q0 = q0;
        this.q2 = q2;
    }

    @Override
    public boolean changeToState(String leftWord) {
        String firstChar;
        String poppedChar;

        try {
            firstChar = String.valueOf(leftWord.charAt(0));
        } catch (StringIndexOutOfBoundsException e){
            firstChar = "";
        }

        try {
            poppedChar = stack.pop();
        } catch (EmptyStackException e){
            poppedChar = "$";
        }

        if (firstChar.equals("") && poppedChar.equals("$")){
            System.out.println("Rejected");
            return false;
        }

        if (firstChar.equals("0") && poppedChar.equals("1")){
            return changeToNextState(this, leftWord);
        } else if (firstChar.equals("0") && poppedChar.equals("0")){
            stack.push("0");
            stack.push("0");
            return changeToNextState(q0, leftWord);
        } else if (firstChar.equals("1") && poppedChar.equals("1")){
            stack.push("1");
            stack.push("1");
            return changeToNextState(q2, leftWord);
        } else if (firstChar.equals("") && poppedChar.equals("1")){
            stack.push("1");
            return changeToNextState(q2, leftWord);
        } else if (firstChar.equals("1") && poppedChar.equals("$")) {
            stack.push("1");
            return changeToNextState(this, leftWord);
        } else {
            System.out.println("Rejected");
            return false;
        }
    }
}
