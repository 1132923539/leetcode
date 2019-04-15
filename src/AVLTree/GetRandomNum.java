package AVLTree;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

public class GetRandomNum {

    public static void main(String[] args) throws IOException {
//        FileOutputStream fos = new FileOutputStream("D://bigData.txt");
//        FileInputStream fis = new FileInputStream("D://bigData.txt");

        String str;
        FileWriter fw;
        try {
            // c盘生成txt文件并存入随机数
            fw = new FileWriter("D://bigData.txt");
            for (int m = 0; m < 10; m++) {
                for (int i = 0; i < 100000; i++) {
                    for (int j = 0; j < 10; j++) {
                        // 随机生成1~100以内的数
                        int temp = (int) (Math.random() * 10000 + 1);
                        StringBuilder sb = new StringBuilder(temp+"");
                        if (j != 9)
                            sb.append(",");
                        else
                            sb.append("\n");
                        fw.write(sb.toString());
                    }
                }
                fw.flush();
            }

            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


//        int b = 0;
//        int max = -1;
//        int min = 10000;
//        long sum = 0;
//        while ((b = fis.read()) != -1) {
//            System.out.println(b);
//            sum += b;
//            if (max < b) {
//                max = b;
//            }
//
//            if (min > b) {
//                min = b;
//            }
//        }
//
//        System.out.println("最大值：" + max);
//        System.out.println("最小值：" + min);
//        System.out.println("平均值：" + sum / 5000);

//        fis.close();
//        fos.flush();
//        fos.close();
    }

}
