package Socket.TCP.project1;

import java.io.IOException;
import java.net.Socket;

public class EchoClient {
    public static void main(String[] args) throws IOException {
        System.out.println("Client started");
        Socket soc = new Socket("localhost",8080);

    }
}
