package MultiThreading.ExecuService;

import java.util.concurrent.*;

public class ScheduledPool02 {
    public static void main(String[] args) throws InterruptedException {
        ScheduledExecutorService scs = Executors.newScheduledThreadPool(5);

        ScheduledFuture scheduledFuture = scs.scheduleAtFixedRate(
                ()->{
                    System.out.println("Hi");
                },
                1,
                2,
                TimeUnit.SECONDS
        );

        /*
        * it will print hi after 1 sec and then will repeat it every 2 sec
        * */

        Thread.sleep(5000);
        scs.shutdown();

        scs.awaitTermination(5, TimeUnit.SECONDS);
    }
}
