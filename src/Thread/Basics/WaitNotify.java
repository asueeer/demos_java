package Thread.Basics;

import java.lang.management.ThreadInfo;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class WaitNotify {
    static boolean flag = true;
    static Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread waitThread = new Thread(new Wait(), "WaitThread");
        waitThread.start();
        TimeUnit.SECONDS.sleep(1);
        Thread notifyThread = new Thread(new Notify(), "NotifyThread");
        notifyThread.start();
    }

    private static class Wait implements Runnable {
        @Override
        public void run() {
            synchronized (lock){
                while(flag){
                    System.out.println(Thread.currentThread()+"flag is true. wait @"+ new
                            SimpleDateFormat("HH:mm:ss").format(new Date()));
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                System.out.println(Thread.currentThread()+"flag is false. wait @"+ new
                        SimpleDateFormat("HH:mm:ss").format(new Date()));
            }

        }
    }

    private static class Notify implements Runnable {
        @Override
        public void run() {
            synchronized (lock){
                System.out.println(Thread.currentThread()+"hold lock. notify@"+new
                        SimpleDateFormat("HH:mm:ss").format(new Date()));
                lock.notifyAll();
                flag = false;
                SleepUtils.second(5);
            }

            synchronized (lock){
                System.out.println(Thread.currentThread()+"hold lock again. notify@"+new
                        SimpleDateFormat("HH:mm:ss").format(new Date()));
                SleepUtils.second(5);
            }
        }
    }
}
