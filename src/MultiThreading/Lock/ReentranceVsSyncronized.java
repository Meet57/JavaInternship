package MultiThreading.Lock;

public class ReentranceVsSyncronized {

    public static void sleep(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public synchronized void method1(int ret){
        sleep();
        if(ret==1) return;
        if(ret == 2){
            ret--;
            method2();
        }
        System.out.println("Hi");
        sleep();
        if(ret==1) return;
    }

    public synchronized void method2(){
        sleep();
        method1(1);
    }

    public static void main(String[] args) {
        ReentranceVsSyncronized rv = new ReentranceVsSyncronized();
        rv.method1(2);
    }
}
