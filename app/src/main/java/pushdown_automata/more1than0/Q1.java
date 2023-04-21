package pushdown_automata.more1than0;

import java.util.EmptyStackException;
import java.util.Stack;

public class Q1 extends State{
    private final Q0 q0;
    private Q2 q2;
    public Q1(String name, Stack<String> stack, Q0 q0, Q2 q2) {
        super(name, stack);
        this.q0 = q0;
        this.q2 = q2;
    }

    public void setStates(Q2 q2) {
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
            try {
                return changeToState(leftWord.substring(1));
            } catch (StringIndexOutOfBoundsException e){
                return changeToState("");
            }
        } else if (firstChar.equals("0") && poppedChar.equals("0")){
            stack.push("0");
            stack.push("0");
            try {
                return q0.changeToState(leftWord.substring(1));
            } catch (StringIndexOutOfBoundsException e){
                return q0.changeToState("");
            }
        } else if (firstChar.equals("1") && poppedChar.equals("1")){
            stack.push("1");
            stack.push("1");
            try {
                return q2.changeToState(leftWord.substring(1));
            } catch (StringIndexOutOfBoundsException e){
                return q2.changeToState("");
            }
        } else if (firstChar.equals("") && poppedChar.equals("1")){
            stack.push("1");
            try {
                return q2.changeToState(leftWord.substring(1));
            } catch (StringIndexOutOfBoundsException e){
                return q2.changeToState("");
            }
        } else if (firstChar.equals("1") && poppedChar.equals("$")) {
            stack.push("1");
            try {
                return changeToState(leftWord.substring(1));
            } catch (StringIndexOutOfBoundsException e){
                return changeToState("");
            }
        } else {
            return false;
        }
    }
}
