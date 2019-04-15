import java.util.Stack;

public class Solution8 {

    public static void main(String[] args) {
        Stack<Character> characters = new Stack<>();

        if(!characters.empty()){
            Character p0 = characters.pop();
            System.out.println(p0);
        }


        System.out.println(isMatch('(',']'));
        System.out.println(isValid(
                "()[]{}"));
    }


    public static boolean isValid(String s) {

        int length = s.length();
        System.out.println(length%2);
        if(length%2 != 0) return false;

        Stack<Character> stack = new Stack();
        for(int i=0;i<length;i++){
            if(stack.empty()) {
                stack.push(s.charAt(i));
                continue;
            }

            Character c = stack.pop();
            System.out.println(c+"----"+s.charAt(i));
            if(!isMatch(c,s.charAt(i))){
                stack.push(c);
                stack.push(s.charAt(i));
            }
        }
        if(stack.empty()) return true;
        return false;

    }

    //一定要注意switch的用法
    private static boolean isMatch(char left,char right){
        boolean flag =false;
        switch(left){
            case '(':
                if(right == ')') flag = true;
                break;
            case '[':
                if(right == ']')  flag = true;
                break;
            case '{':
                if(right == '}')  flag = true;
                break;
        }
        return flag;
    }
}
