package pushdown_automata.more1than0;

import java.util.EmptyStackException;
import java.util.Stack;

public class Q2 extends State{
    private final Q1 q1;
    public Q2(String name, Stack<String> stack, Q1 q1) {
        super(name, stack);
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
            try {
                return q1.changeToState(leftWord.substring(1));
            } catch (StringIndexOutOfBoundsException e){
                return q1.changeToState("");
            }
        } else if (firstChar.equals("1") && poppedChar.equals("1")){
            stack.push("1");
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
