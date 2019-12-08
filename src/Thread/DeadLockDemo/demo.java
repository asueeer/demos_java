package Thread.DeadLockDemo;

import static java.lang.Thread.sleep;

public class demo {
    private static String A = "A";
    private static String B = "B";

    public static void main(String[] args) {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (A){
                    try {
                        sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (B){
                        System.out.println("hello");
                    }
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (B){
                    try {
                        sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (A){
                        System.out.println("你好");
                    }
                }
            }
        });

        thread1.start();
        thread2.start();
    }
}
