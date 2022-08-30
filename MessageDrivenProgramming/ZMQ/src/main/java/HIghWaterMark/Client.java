package HIghWaterMark;

import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

public class Client implements Runnable {
    @Override
    public void run() {
        ZContext context = new ZContext();
        ZMQ.Socket socket = context.createSocket(SocketType.SUB);

        socket.connect("tcp://localhost:5555");

        socket.subscribe(ZMQ.SUBSCRIPTION_ALL);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        while (true) {
            String res = new String(socket.recv());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if (res.equals("exit")) break;
            System.out.println(Thread.currentThread().getName() + " " + res);
        }

        context.close();
    }
}
