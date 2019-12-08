package Thread.GuiTuRunning;

public class Running {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("主线程开始执行");
        int step = 1000000;
        Thread thread1 = new Thread(new Gui(step));
        Thread thread2 = new Thread(new Tu(step));
        thread1.start();
        thread2.start();
        System.out.println();
        System.out.println("主线程执行完毕");
        System.out.println();
    }
}
