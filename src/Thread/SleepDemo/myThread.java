package Thread.SleepDemo;

public class myThread extends Thread {

    public myThread() {
    }

    public myThread(String name) {
        setName(name);
    }

    @Override
    public void run() {



        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + ":" + i);
            try {
                Thread.currentThread().sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //轮流休眠一秒，这样就不会出现一个进程长期霸占CPU？
        }
    }
}