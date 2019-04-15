


public class Roman_to_int {
    public static void main(String[] args) {


        String s="ILMC";
        char[] chars = s.toCharArray();
        int a = 10;
        switch (a) {
            case 'I':
                return;
            case 'V':
                break;
            default:
                break;
        }
    }

    public int romanToInt(String s) {
        char[] romanChars=s.toCharArray();
        int[] ints = getInts(romanChars);

        for(int i=0;i<ints.length-1;i++){
            if(ints[i]<ints[i+1]) ints[i] = -ints[i];
        }
        int sum =0;
        for(int i:ints){
            sum = sum+i;
        }
        return sum;
    }

    private int[] getInts(char[] romanChars){
        int size = romanChars.length;
        int[] ints = new int[size];
        for(int i=0;i<size;i++){
            ints[i] = transform(romanChars[i]);
        }
        return ints;
    }

    private int transform(char romanNum){
        switch(romanNum){
            case 'I':
                return 1;
            case 'V':
                return 5;
            case 'X':
                return 10;
            case 'L':
                return 50;
            case 'C':
                return 100;
            case 'D':
                return 500;
            case 'M':
                return 1000;
        }
        return 0;
    }
}
