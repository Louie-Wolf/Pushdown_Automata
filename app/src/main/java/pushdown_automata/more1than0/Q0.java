package pushdown_automata.more1than0;

import java.util.EmptyStackException;
import java.util.Stack;

public class Q0 extends State{
    private Q1 q1;
    private Q2 q2;
    public Q0(String name, Stack<String> stack) {
        super(name, stack);
    }

    public void setStates(Q1 q1, Q2 q2) {
        this.q1 = q1;
        this.q2 = q2;
    }

    @Override
    public boolean changeToState(String leftWord) {
        if(leftWord.equals("")){
            System.out.println("Rejected");
            return false;
        }

        String firstChar = String.valueOf(leftWord.charAt(0));
        String poppedChar;

        try {
            poppedChar = stack.pop();
        } catch (EmptyStackException e){
            poppedChar = "$";
        }

        if (firstChar.equals("0") && poppedChar.equals("$")){
            stack.push("0");
            return changeToNextState(this, leftWord);
        } else if (firstChar.equals("0") && poppedChar.equals("0")){
            stack.push("0");
            stack.push("0");
            return changeToNextState(this, leftWord);
        } else if (firstChar.equals("1") && poppedChar.equals("0")){
            return changeToNextState(this, leftWord);
        } else if (firstChar.equals("1") && poppedChar.equals("1")){
            stack.push("1");
            stack.push("1");
            return changeToNextState(q1, leftWord);
        } else if (firstChar.equals("1") && poppedChar.equals("$")){
            stack.push("1");
            return changeToNextState(q2, leftWord);
        } else {
            System.out.println("Rejected");
            return false;
        }
    }
}
