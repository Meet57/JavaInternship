package MultiThreading.ExecuService;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/*
* Easy undertanding of countdownlatch
* https://static.javatpoint.com/core/images/countdownlatch-in-java.png
*
* it takes a varibale in the constructor where it notes that how many cpuntdown are going to come
* so whenerver the threads are running with the delay
* we keep a countdown
* if the countdown is reached to the value it will continue the main thread from latch.await()
* */

public class CountDownLatchExample {
    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(4);

        Worker first = new Worker(1000, latch,
                "WORKER-1");
        Worker second = new Worker(2000, latch,
                "WORKER-2");
        Worker third = new Worker(3000, latch,
                "WORKER-3");
        Worker fourth = new Worker(4000, latch,
                "WORKER-4");

        first.start();
        second.start();
        third.start();
        fourth.start();

        try {
            latch.await(50, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // Main thread has started
        System.out.println(Thread.currentThread().getName() +
                " has finished");
    }
}

class Worker extends Thread
{
    private int delay;
    private CountDownLatch latch;

    public Worker(int delay, CountDownLatch latch,
                  String name)
    {
        super(name);
        this.delay = delay;
        this.latch = latch;
    }

    @Override
    public void run()
    {
        try
        {
            Thread.sleep(delay);
            latch.countDown();
            System.out.println(Thread.currentThread().getName()
                    + " finished : "+ latch.getCount());
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}
