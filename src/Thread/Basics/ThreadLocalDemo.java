package Thread.Basics;

import java.util.concurrent.TimeUnit;

public class ThreadLocalDemo {
    private static final ThreadLocal<Long> Time_ThreadLocal = new ThreadLocal<Long>(){
        protected Long initialValue(){
            return System.currentTimeMillis();
        }
    };

    public static final void begin(){
        Time_ThreadLocal.set(System.currentTimeMillis());
    }

    public static final long end(){
        return System.currentTimeMillis() - Time_ThreadLocal.get();
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadLocalDemo.begin();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("Cost:" + ThreadLocalDemo.end() + " mills.");
    }
}
