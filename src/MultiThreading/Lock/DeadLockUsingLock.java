package MultiThreading.Lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DeadLockUsingLock {
    public static Runnable myRunnable1(Lock lock1,Lock lock2){
        return new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " : Attempt to lock lock 1");
                lock1.lock();
                System.out.println(Thread.currentThread().getName() + " : lock 1 locked");

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                System.out.println(Thread.currentThread().getName() + " : Attempt to lock lock 2");
                lock2.lock();
                System.out.println(Thread.currentThread().getName() + " : lock 2 locked");

                /*
                * Some task Here
                * */

                lock1.unlock();
                System.out.println(Thread.currentThread().getName() + " : lock 1 unlock");

                lock2.unlock();
                System.out.println(Thread.currentThread().getName() + " : lock 2 unlock");
            }
        };
    }

    public static Runnable myRunnable2(Lock lock1,Lock lock2){
        return new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " : Attempt to lock lock 2");
                lock2.lock();
                System.out.println(Thread.currentThread().getName() + " : lock 2 locked");

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                System.out.println(Thread.currentThread().getName() + " : Attempt to lock lock 1");
                lock1.lock();
                System.out.println(Thread.currentThread().getName() + " : lock 1 locked");

                /*
                 * Some task Here
                 * */

                lock2.unlock();
                System.out.println(Thread.currentThread().getName() + " : lock 2 unlock");

                lock1.unlock();
                System.out.println(Thread.currentThread().getName() + " : lock 1 unlock");
            }
        };
    }

    public static void main(String[] args) {
        Lock lock1 = new ReentrantLock();
        Lock lock2 = new ReentrantLock();

        Thread t1 = new Thread(myRunnable1(lock1,lock2));
        Thread t2 = new Thread(myRunnable2(lock1,lock2));
//        Thread t2 = new Thread(myRunnable2(lock2,lock1));

        t1.start();
        t2.start();
    }
}
