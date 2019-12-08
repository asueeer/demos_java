package Thread.SleepDemo;

public class demo {

    public static void main(String[] args) {
        myThread m1 = new myThread("高铁");
        myThread m2 = new myThread("火车");
        myThread m3 = new myThread("汽车");

        m1.start();
        m2.start();
        m3.start();
    }
}
