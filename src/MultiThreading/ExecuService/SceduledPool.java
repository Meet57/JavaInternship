package MultiThreading.ExecuService;

import java.util.concurrent.*;

public class SceduledPool {
    public static void main(String[] args) {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);

        ScheduledFuture scheduledFuture = scheduledExecutorService.schedule(new Callable() {
            public Object call() throws Exception {
                System.out.println("Executed!");
                return "Called!";
            }
        }, 5, TimeUnit.SECONDS);

        ScheduledFuture scheduledFuture2 = scheduledExecutorService.schedule(new Callable() {
            public Object call() throws Exception {
                System.out.println("Executed 2!");
                return "Called2 !";
            }
        }, 5, TimeUnit.SECONDS);

        try {
            System.out.println("result = " + scheduledFuture.get());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
        try {
            System.out.println("result = " + scheduledFuture2.get());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }

        scheduledExecutorService.shutdown();
    }
}
