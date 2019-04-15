import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class IptoLong {


    public static void main(String[] args) {
        System.out.println(Long.toBinaryString(ipToLong("100.4.5.6")));

        ArrayList<String> strings = new ArrayList<>();

//        verifyBinNum();
//        getRandomN(new int[]{3, 6, 8, 20});
    }

    public static long ipToLong(String ip) {
        String[] section = ip.split("\\.");
        if (section.length != 4) {
            System.out.println("ip地址错误1");
            return 0l;
        }

        long sum = 0l;
        for (int i = 0; i < section.length; i++) {
            try {
                int m = 3 - i;
                long ii = new Long(section[i]);
                sum = (long) (sum + (ii * Math.pow(2, 8 * m)));

                if (ii < 0 || ii > 255) {
                    System.out.println("ip地址错误2");
                    return 0;
                }
            } catch (NumberFormatException e) {
                System.out.println("ip地址错误3");
                return 0;
            }
        }
        return sum;
    }

    public static int binNum(int x) {
        int n = 0;
        if (x < 0) {
            n++;
            x = Integer.MAX_VALUE - (-x) + 1;
        }

        while (x > 0) {
            if (1 == x % 2) n++;
            x = x / 2;
        }
        return n;
    }

    public static void verifyBinNum() {

        for (int m = Integer.MIN_VALUE; m < Integer.MAX_VALUE; m++) {
            int x = m;
            int i = binNum(x);

            int count = 0;
            char[] chars = Integer.toBinaryString(x).toCharArray();
            for (char c : chars) {
                if ('1' == c) {
                    count++;
                }
            }
            if (i != count) {
                System.out.println(m);
            }
        }

    }


    public static void getRandomN(int[] ints) {
        int n = ints.length;
        int maxIndex = n - 1;
        while (maxIndex >= 0) {
            int random = (int) Math.round(Math.random() * maxIndex);
            System.out.println(random + "---" + ints[random]);

            if (random != maxIndex) {
                ints[random] = ints[maxIndex];
            }
            maxIndex--;
        }
    }

}
