/**
 * @author eltons,  Date on 2018-12-10.
 * 将任意整数倒序（简单）
 */
public class Solution5 {

    public String longestPalindrome(String s) {

        return "";

    }


    public static void main(String[] args) {
        System.out.println(Integer.MIN_VALUE+"--"+Integer.MAX_VALUE );
//        System.out.println((int)Math.pow(-2,31));
//        System.out.println(getBit(1056389759));
        System.out.println(reverse(1463847421));
//        System.out.println(reverseStd(Integer.MIN_VALUE));
    }

    public static int reverseStd(int x) {
        int rev = 0;
        while (x != 0) {
            int pop = x % 10;
            x /= 10;
            if (rev > Integer.MAX_VALUE/10 || (rev == Integer.MAX_VALUE / 10 && pop > 7)) return 0;
            if (rev < Integer.MIN_VALUE/10 || (rev == Integer.MIN_VALUE / 10 && pop < -8)) return 0;
            rev = rev * 10 + pop;
        }
        return rev;
    }

    public static int reverse(int x) {
        if(Integer.MIN_VALUE== x ){
            return -1;
        }

        int flag =1;
        if (x<0){
            x = -x;
            flag =-1;
        }

        int newX = 0;
        while(x >0){
            System.out.println(newX+"-----"+isOverstep(newX));
            if(isOverstep(newX)) return 0;
            newX = 10 * newX;

            int redundant = x%10;
            int temp = newX;
            newX = newX+redundant;
            if(newX <temp) return 0;

            x=x/10 ;
        }
        return newX *flag;

    }

    private static Boolean isOverstep(int x){
        return x>(Integer.MAX_VALUE-1)/10;
    }
}
