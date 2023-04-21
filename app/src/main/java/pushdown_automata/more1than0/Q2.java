package pushdown_automata.more1than0;

import java.util.EmptyStackException;
import java.util.Stack;

public class Q2 extends State{
    private Q1 q1;
    public Q2(String name, Stack<String> stack) {
        super(name, stack);
    }

    public void setStates(Q1 q1) {
        this.q1 = q1;
    }

    @Override
    public boolean changeToState(String leftWord) {
        if(leftWord.equals("")){
            System.out.println("Accepted");
            return true;
        }

        String firstChar = String.valueOf(leftWord.charAt(0));
        String poppedChar;

        try {
            poppedChar = stack.pop();
        } catch (EmptyStackException e){
            poppedChar = "$";
        }

        if (firstChar.equals("0") && poppedChar.equals("1")){
            return changeToNextState(q1, leftWord);
        } else if (firstChar.equals("1") && poppedChar.equals("1")){
            stack.push("1");
            stack.push("1");
            return changeToNextState(this, leftWord);
        } else {
            System.out.println("Rejected");
            return false;
        }
    }
}
