package pushdown_automata.more1than0;

import java.util.EmptyStackException;
import java.util.Stack;

public class Q0 extends State{
    private Q1 q1;
    private Q2 q2;
    public Q0(String name, Stack<String> stack, Q1 q1, Q2 q2) {
        super(name, stack);
        this.q1 = q1;
        this.q2 = q2;
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
            try {
                return changeToState(leftWord.substring(1));
            } catch (StringIndexOutOfBoundsException e){
                return changeToState("");
            }
        } else if (firstChar.equals("0") && poppedChar.equals("0")){
            stack.push("0");
            stack.push("0");
            try {
                return changeToState(leftWord.substring(1));
            } catch (StringIndexOutOfBoundsException e){
                return changeToState("");
            }
        } else if (firstChar.equals("1") && poppedChar.equals("0")){
            try {
                return changeToState(leftWord.substring(1));
            } catch (StringIndexOutOfBoundsException e){
                return changeToState("");
            }
        } else if (firstChar.equals("1") && poppedChar.equals("1")){
            stack.push("1");
            stack.push("1");
            try {
                return q1.changeToState(leftWord.substring(1));
            } catch (StringIndexOutOfBoundsException e){
                return q1.changeToState("");
            }
        } else if (firstChar.equals("1") && poppedChar.equals("$")){
            stack.push("1");
            try {
                return q2.changeToState(leftWord.substring(1));
            } catch (StringIndexOutOfBoundsException e){
                return q2.changeToState("");
            }
        } else {
            return false;
        }
    }
}
