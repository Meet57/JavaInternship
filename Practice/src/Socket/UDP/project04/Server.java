package Socket.UDP.project04;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Server {
    public static void main(String[] args) throws IOException {
        byte[] result = new byte[1024];
        String data;

        DatagramSocket server = new DatagramSocket(8080);
        DatagramPacket packet;

        while (true){
            packet = new DatagramPacket(result,result.length);
            server.receive(packet);
            data = new String(packet.getData()).trim();
            if(data.equals("EXIT")) {
                result = null;
                break;
            };
            System.out.println(data);
            result = new byte[1024];
        }
        System.out.println("SERVER GOT ALL MESSAGES");

        server.close();
    }
}
