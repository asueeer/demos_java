package Thread.CAS;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Counter {
    private int i = 0;

    private AtomicInteger automicI = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        final Counter cas = new Counter();

        List<Thread> threads = new ArrayList<Thread>(600);
        long start = System.currentTimeMillis();

        // 产生100个线程，每个线程让cas里的i++
        for (int j = 0; j < 100; j++) {

            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 10000; i++) {
                        cas.count();
                        cas.safeCount();
                    }
                }
            });

            threads.add(thread);
        }

        for(Thread t:threads){
            t.start();
        }

        // 等待所有线程执行完成
        for(Thread t:threads){
            t.join();
        }

        System.out.println(cas.i);
        System.out.println(cas.automicI.get());
        System.out.println(System.currentTimeMillis()-start);
    }

    private void safeCount() {
        for (;;){
            int i = automicI.get();
            i++;
            boolean suc = automicI.compareAndSet(i-1, i);
            if(suc){
                break;
            }
        }
    }

    private void count() {
        i++;
    }
}
