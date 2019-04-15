import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Ali_interview {

    public static void main1(String[] args) {
        Object lock = new Object();

        Runnable thread1 = () -> {
            int n = 1;

            while (true) {
                synchronized (lock) {
                    if (n > 100) break;

                    if (1 == n % 2) {
                        System.out.println(Thread.currentThread().getName() + "--" + n);
                    }
                    try {
                        lock.notifyAll();
                        if (99 != n)
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    n += 2;
                }
            }
        };

        Runnable thread2 = () -> {
            int n = 2;

            while (true) {
                synchronized (lock) {
                    if (n > 100) break;

                    if (0 == n % 2) {
                        System.out.println(Thread.currentThread().getName() + "--" + n);
                    }
                    try {
                        lock.notifyAll();
                        if (100 != n)
                            lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    n += 2;
                }
            }
        };
        new Thread(thread1, "Printer1").start();
        new Thread(thread2, "Printer2").start();
    }

    public static void main(String[] args) {
        char[] chars = {'a','b','c','d'};
        ArrayList<ArrayList<Character>> result = new ArrayList<>();
        result.add(new ArrayList<>());
        sub(chars,0,result);

        result.remove(0);
        System.out.println(result);
    }

    private static void sub(char[] chars, int n, ArrayList<ArrayList<Character>> subList) {
        if (n >= chars.length) return;

        char c = chars[n];
        int size = subList.size();
        for (int i=0;i<size;i++) {
            ArrayList<Character> newChar = new ArrayList<>(subList.get(i));
            newChar.add(c);
            subList.add(newChar);
        }
        sub(chars, ++n, subList);
    }



        public static void test(String[] strArrays){
            int length = strArrays.length;
            for (int i = 1;i<=length;i++){
                for(int j = 0;j<=length-i;j++){
                    System.out.println(Arrays.toString(subArray(strArrays,j,j+i)));
                }
            }
        }

        public static String[] subArray(String[] array ,int start,int end){
            String[] arrays = new String[end-start];
            for(int i=0;i<=arrays.length-1;i++){
                arrays[i] = array[start+i];
            }
            return arrays;
        }

        public static void main3(String[] args) {
            test(new String[]{"a","b","c","d","e","f","g"});
        }

}


