package module12;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class HomeworkTask1 {
    public static void main(String[] args) throws InterruptedException {
        Runnable timer1sec = new Runnable() {
            int currentValue;

            @Override
            public void run() {
                currentValue++;
                System.out.println("currentValue = " + currentValue);
            }
        };

        Runnable timer5sec = () -> System.out.println("5 seconds passed...");

        ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);
        executor.scheduleAtFixedRate(timer5sec, 5, 5, TimeUnit.SECONDS);
        executor.scheduleAtFixedRate(timer1sec, 1, 1, TimeUnit.SECONDS);

        Thread.sleep(20000);
        executor.shutdown();
    }
}
