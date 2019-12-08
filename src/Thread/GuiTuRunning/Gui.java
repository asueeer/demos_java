package Thread.GuiTuRunning;

public class Gui implements Runnable {
    String name = "Gui";
    int step = 10;

    public Gui(int step) {
        this.step = step;
    }

    @Override
    public void run() {
        int sum = 0;
        for (int i = 0; i < this.step; i++) {
            sum++;
            System.out.println(name+"跑了1步，一共跑了"+sum+"步");
            try {
                Thread.currentThread().sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
