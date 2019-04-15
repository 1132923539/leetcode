package AVLTree;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.CountDownLatch;

public class TestAVL {

    private static final Long bufFull = 100000l;
    private static boolean isSavedBuf1 = false;
    private static boolean isSavedBuf2 = false;
    private static final String readLock = "readLock";

    private static boolean isFinish = false;

    private static final CountDownLatch cdl = new CountDownLatch(2);

    public static void main(String[] args) throws FileNotFoundException {
        AVLTree<Integer> avl = new AVLTree<Integer>();
        final ArrayList buff1 = new ArrayList<Integer>(Math.toIntExact(bufFull));
        final ArrayList buff2 = new ArrayList<Integer>(Math.toIntExact(bufFull));
        File file = new File("D://bigData.txt");
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
        BufferedReader br = new BufferedReader(new InputStreamReader(bis, StandardCharsets.UTF_8), 10 * 1024 * 1024);

        Runnable t1 = () -> {
            try {
                readBigFile(buff1, buff2, br);
                cdl.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Runnable t2 = () -> {
            consumer(buff1, buff2, avl);
            cdl.countDown();
        };

        new Thread(t1, "我是读数据线程").start();
        new Thread(t2, "我是消费数据线程").start();

        try {
            cdl.await();
            avl.printTree();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void readBigFile(ArrayList<Integer> buff1, ArrayList<Integer> buff2, BufferedReader br) throws InterruptedException {
        try {
            //            File file = new File("D://bigData.txt");
            //            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
            //            BufferedReader br = new BufferedReader(new InputStreamReader(bis, StandardCharsets.UTF_8), 10 * 1024 * 1024);

            writeBuf(br, buff1, buff2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeBuf(BufferedReader br, ArrayList<Integer> buff1, ArrayList<Integer> buff2) throws IOException {
        while (br.ready()) {
            String line = br.readLine();
            String[] lineData = line.split(",");

            if (!isSavedBuf1) {
                Arrays.stream(lineData).map(Integer::new).forEach(buff1::add);
                if (buff1.size() >= bufFull) {
                    isSavedBuf1 = true;
                }
            }

            if (isSavedBuf1 && !isSavedBuf2) {
                for (String s : lineData) {
                    Integer integer = new Integer(s);
                    buff2.add(integer);
                }
                if (buff2.size() >= bufFull) {
                    isSavedBuf2 = true;
                }
            }
            if (isSavedBuf1 || isSavedBuf2)
                synchronized (readLock) {
                    readLock.notifyAll();
                }
            if (isSavedBuf1 && isSavedBuf2)
                synchronized (readLock) {
                    try {
                        readLock.notifyAll();
                        if (!isFinish)
                            readLock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        }
    }

    private static void consumer(ArrayList<Integer> buff1, ArrayList<Integer> buff2, AVLTree<Integer> maxTree) {
        int n = 0;
        while (true) {
            if (isFinish) break;
            if (isSavedBuf1) {
                dealBuff(buff1, maxTree, 1);
                n++;
                System.out.println("执行提取buff次数：" + n);
            } else if (isSavedBuf2) {
                dealBuff(buff2, maxTree, 2);
                n++;
                System.out.println("执行提取buff次数：" + n);
            }
            if (n >= 100) {
                isFinish = true;
            }
            if (!isSavedBuf1 || !isSavedBuf2) {
                synchronized (readLock) {
                    readLock.notifyAll();
                }
            }

            if (!isSavedBuf1 && !isSavedBuf2) {
                synchronized (readLock) {
                    try {
                        readLock.notifyAll();
                        if (!isFinish)
                            readLock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private static void dealBuff(ArrayList<Integer> buff, AVLTree<Integer> maxTree, int n) {
        Integer treeMin = maxTree.findMin() == null ? 0 : maxTree.findMin();
        for (Integer i : buff) {

            if (maxTree.getSumNode() <= 100) {
                maxTree.insert(i);
            } else {
                if (i > treeMin) {
                    maxTree.remove(treeMin);
                    maxTree.insert(i);
                }
            }
        }
        buff.clear();
        if (n == 1) {
            isSavedBuf1 = false;
        } else {
            isSavedBuf2 = false;
        }
    }
}