package MultiThreading.Lock;

import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

/*
 * In this program I have used wait and notify for reload and fire: two threads
 * I have also used Concept of synchronized and atomicVariable
 * */

class Gun {
    private int bullet = 40;
    private AtomicBoolean stop = new AtomicBoolean(false);

    synchronized public void fire(int b) {
        int i = 0;
        for (i = 1; i <= b; i++) {
            if (bullet == 0) {
                System.out.println("Fired " + (i - 1) + " Now reloading");
                try {
                    notify();
                    wait();
                } catch (Exception e) {
                    System.err.println(e);
                }
                if(bullet > 0){
                    System.out.println("Reloaded");
                }
            }
            if (stop.get() == true) break;
            bullet--;
            firing();
        }
        stop.set(true);
        notify();
        System.out.println("Fired " + (i - 1) + " bullets");
    }

    private void firing() {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.print("-");
    }

    synchronized public void reload() {
        Scanner sc = new Scanner(System.in);
        this.bullet = 40;
        if(stop.get() == false){
            System.out.print("Reload how many Bullets : ");
            int value =sc.nextInt();
            if (value != 0) {
                synchronized (this){
                    bullet = value;
                }
                notify();
                try {
                    wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                reload();
            } else {
                stop.set(true);
                notify();
            }
        }else{
            stop.set(true);
            notify();
        }
    }
}

public class WaitNotify {
    public static void main(String[] args) {
        Gun g = new Gun();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                g.fire(1000);
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                g.reload();
            }
        });

        t1.start();
        t2.start();
    }
}
