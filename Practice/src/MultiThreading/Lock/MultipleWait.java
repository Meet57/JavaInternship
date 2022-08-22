package MultiThreading.Lock;

import java.util.Scanner;

class MWait {

    synchronized public void multipleNotify() {
        System.out.println("\t"+Thread.currentThread().getName());
        Scanner sc = new Scanner(System.in);
        sc.nextLine();
        notify();
        sc.nextLine();
        notify();
        sc.nextLine();
        notify();
    }

    synchronized public void multipleWait(){
        System.out.println(Thread.currentThread().getName());
        try {
            wait();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(Thread.currentThread().getName());
    }
}

public class MultipleWait {
    public static void main(String[] args) {
        MWait m = new MWait();
        new Thread(new Runnable() {
            @Override
            public void run() {
                m.multipleWait();
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                m.multipleWait();
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                m.multipleWait();
            }
        }).start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                m.multipleNotify();
            }
        }).start();
    }
}

