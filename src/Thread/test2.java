package Thread;

public class test2 {
    private static final long count = 100000000;

    public static void main(String[] args) throws InterruptedException {
//        concurrent();
        serial();
    }


    private static void concurrent() throws InterruptedException {
        long start = System.currentTimeMillis();

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                int a = 0;
                for (int i = 0; i < count; i++) {
                    a = a+5;
                }
            }
        });

        thread.start();

        int b = 0;
        for (int i = 0; i < count; i++) {
            b--;
        }
        thread.join(); // 这条语句的作用是：现在正在运行主线程，允许thread线程加入，等thread执行完之后，主线程才会继续执行

        long time = System.currentTimeMillis() - start;
        System.out.println("多线程所用时间：" + time + "ms, b = ");
    }


    private static void serial(){
        long start = System.currentTimeMillis();
        int a = 0;
        for (int i = 0; i < count; i++) {
            a = a+5;
        }

        int b = 0;
        for (int i = 0; i < count; i++) {
            b--;
        }

        long time = System.currentTimeMillis() - start;

        System.out.println("串行所用时间：" + time + "ms, b = "+b);

    }
}
