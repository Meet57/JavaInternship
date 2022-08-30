package HIghWaterMark;

import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

public class Server implements Runnable {

    @Override
    public void run() {
        ZContext context = new ZContext();
        ZMQ.Socket socket = context.createSocket(SocketType.PUB);

        socket.bind("tcp://localhost:5555");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        socket.setHWM(5);
        socket.setSndHWM(5);

        for (long i = 1; i <= 100; i++) {
            String reply = String.valueOf(i);
            if (socket.send(reply.getBytes(), ZMQ.NOBLOCK))
            {
                System.out.print(i+" ");
            }
            else
            {
                System.err.print(i+" ");
            }
        }

        System.out.println("OUT");
        socket.send("exit".getBytes());

        context.close();
    }
}
