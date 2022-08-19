package Socket.TCP.project1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        System.out.println("Waiting for clients...");
        ServerSocket st = new ServerSocket(8080);

        Socket soc = st.accept();
        System.out.println("Connection Established");
    }
}
