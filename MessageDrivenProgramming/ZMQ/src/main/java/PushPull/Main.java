package PushPull;

/*
* First Come First Server
* */

public class Main {
    public static void main(String[] args) {
        new Thread(new Server(),"SERVER").start();
        new Thread(new Client(),"A").start();
    }
}
