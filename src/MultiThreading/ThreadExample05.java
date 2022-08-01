package MultiThreading;

/*
* if two methods are sync, and same object is trying to access
* it goes one by one
* different objects it run parallel
*
* if two methods are sync and static, and different objects is trying to access
* it will go one by one
* */

class Meet{
    public synchronized void print1(){
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(i);
        }
    }
    public synchronized void print2(){
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("\t"+i);
        }
    }



    public void callSync()
    {
        synchronized (Meet.class)
        {
            System.out.println("sout");
        }
    }

}

class MyRunnable implements Runnable{
    private Meet m;
    private int v;
    MyRunnable(Meet m,int v){
        this.m = m;
        this.v = v;
    }

    @Override
    public void run() {
        m.callSync();
        if (v == 1){
            m.print1();
        }else {
            m.print2();
        }
    }
}

public class ThreadExample05 {
    public static void main(String[] args) {
        Meet m=new Meet();
        Meet n=new Meet();
        Thread t1 = new Thread(new MyRunnable(m,1));
        Thread t2 = new Thread(new MyRunnable(m,2));

        t1.start();
        t2.start();
    }
}
