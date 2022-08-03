package MultiThreading.Lock;

class Gun{
    private int bullet = 40;
    synchronized public void fire(int b){
        for (int i = 1; i <= b; i++) {
            if(bullet==0){
                System.out.println("Fired " + (i-1) + " Now reloading");
                try{
                    wait();
                }catch (Exception e){
                    System.err.println(e);
                }
                System.out.println("Reloaded");
            }
            bullet--;
            firing();
        }
        System.out.println("Fired "+b+" bullets");
    }

    private void firing(){
        try {
            Thread.sleep(166);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.print("-");
    }
    synchronized public void reload(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        this.bullet = 40;
        notify();
    }
}

public class WaitNotify {
    public static void main(String[] args) {
        Gun g = new Gun();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                g.fire(60);
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
