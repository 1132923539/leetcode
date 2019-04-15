public class Solution13 {

    public static void main(String[] args) {

        countAndSay(6);
    }

    public static String countAndSay(int n) {
        StringBuilder sb = new StringBuilder("1");

        for(int i=1;i<n;i++){
            String temp = sb.toString();
            sb = new StringBuilder();

            int len = temp.length();

            int j =0;
            int k =j+1;
            int m =1;
            while(k<len){
                if(temp.charAt(j) == temp.charAt(k)){
                    m++;
                }else{
                    sb = sb.append(trans(m,temp.charAt(j)));
                    j = k;
                    m =1;
                }
                k++;
            }
            sb = sb.append(trans(m,temp.charAt(j)));
            System.out.println(i+"-----"+sb.toString());
        }
        return sb.toString();
    }

    private static String trans(int num, char c){
        return num+""+c;
    }
}
