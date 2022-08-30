package Single;

import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

/*
* REQ REP
* it will be first come first serve
* */

public class Server {
    public static void main(String[] args) {
        ZContext context = new ZContext();
        ZMQ.Socket socket = context.createSocket(SocketType.REP);

        socket.bind("tcp://localhost:5555");

        byte[] response = socket.recv();
        System.out.println("[GOT] "+ new String(response,ZMQ.CHARSET));

        String reply = "Hello from Single.Server";
        socket.send(reply.getBytes());

        context.close();
    }
}
