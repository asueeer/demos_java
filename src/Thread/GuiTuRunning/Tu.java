package Thread.GuiTuRunning;

import java.util.Random;

import static java.lang.Thread.sleep;

public class Tu implements Runnable{
    String name = "兔兔";
    int step = 10;

    public Tu(int step) {
        this.step = step;
    }


    @Override
    public void run() {
        Random ra = new Random();
        int sum = 0;
        for (int i = 0; i < this.step; i++) {
            if(ra.nextInt()%2==0){
                sum+=2;
                System.out.println(name+"跑了2步，一共跑了" + sum +"步");
            }else{
                System.out.println(name+"睡觉了，一共跑了"+ sum +"步");
            }
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
