package Thread;

import java.math.BigDecimal;

public class increase {

    public static void main(String[] args) throws InterruptedException {
        long t1 = System.currentTimeMillis();
        Thread thread= new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                for (BigDecimal j = new BigDecimal(0); !j.equals(new BigDecimal(1000000000)); j = j.add(BigDecimal.valueOf(1))) {
                    i++;
                }
                System.out.println(i);
            }
        });

        thread.start();
//        thread.join(); // 注释掉的话会继续执行主线程

        long t2 = System.currentTimeMillis();
        System.out.println(t2-t1);

    }
}
