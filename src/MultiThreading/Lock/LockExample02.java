package MultiThreading.Lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class HalfCounter{
    Lock lock = new ReentrantLock();
    public void loop1(int counter){
        lock.lock();
        for (int i = 0; i < counter/2; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName()+" : "+i);

        }
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        loop2(counter);
    }
    public void loop2(int counter){
        lock.unlock();
        for (int i = counter/2; i < counter; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName()+" : "+i);
        }
    }
}

public class LockExample02 {
    public static Runnable LockRunnable(HalfCounter c,int n){
        return new Runnable() {
            @Override
            public void run() {
                c.loop1(n);
            }
        };
    }

    public static void main(String[] args) {
        HalfCounter c = new HalfCounter();
        Thread t1 = new Thread(LockRunnable(c,10));
        Thread t2 = new Thread(LockRunnable(c,7));

        t1.start();
        t2.start();
    }
}
