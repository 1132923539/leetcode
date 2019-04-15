/**
 * 判断整数是否回文（简单）
 */
public class Solution6 {
    public static void main(String[] args) {
        System.out.println(isPalindrome(10));
    }

    public static boolean isPalindrome(int x) {
        if(x<0) return false;

        int n = getBit(x);
        int p = 0;
        int x0 =x;
        while(p<(n-p)){
            p++;
            System.out.println(x+"------"+(int)(x0/Math.pow(10,n-p))%10+"-------"+x%10);

            if((int)(x0/Math.pow(10,n-p))%10 != x%10) return false;
            x = x/10;

        }
        return true;
    }

    private static int getBit(int x){
        int n=0;
        while(x>0){
            x = x/10;
            n++;
        }
        return n;
    }
}
