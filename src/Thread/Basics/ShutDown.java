package Thread.Basics;

import java.lang.management.ThreadInfo;

public class ShutDown {
    public static void main(String[] args) {
        Runner one = new Runner();
        Thread thread1 = new Thread(one);
        thread1.start();
        SleepUtils.second(1);
        thread1.interrupt();

        Runner two = new Runner();
        Thread thread2 = new Thread(two);
        thread2.start();
        SleepUtils.second(1);
        two.cancel();
    }

    private static class Runner implements Runnable{
        private long i;
        private volatile boolean on = true;

        @Override
        public void run() {
            while(on&& !Thread.currentThread().isInterrupted()) {
                i++;
            }
            System.out.println("i:"+i);
        }

        public void cancel(){
            on = false;
        }
    }
}
