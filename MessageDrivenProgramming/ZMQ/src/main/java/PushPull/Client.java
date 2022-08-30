package PushPull;

import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

public class Client implements Runnable {
    @Override
    public void run() {
        ZContext context = new ZContext();
        ZMQ.Socket socket = context.createSocket(SocketType.PULL);

        socket.connect("tcp://localhost:5555");


        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        while (true) {
            String res = socket.recvStr();
            System.out.println("GOT "+res);
            if (res.equals("exit")) break;
//            System.out.println(res);
        }

        context.close();
    }
}
