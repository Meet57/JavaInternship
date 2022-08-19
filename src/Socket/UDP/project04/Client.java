package Socket.UDP.project04;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Client {
    public static void main(String[] args) throws IOException, InterruptedException {
        DatagramSocket client = new DatagramSocket();
        DatagramPacket packet;

        File file = new File("/home/meet/IdeaProjects/Practice/src/Socket/UDP/project04/meet.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String st;
        while ((st = br.readLine()) != null) {
            packet = new DatagramPacket(
                    st.getBytes(),
                    st.getBytes().length,
                    InetAddress.getByName("10.20.40.226"),
                    8080);
            client.send(packet);
            Thread.sleep(100);
        }
        st = "EXIT";
        packet = new DatagramPacket(
                st.getBytes(),
                st.getBytes().length,
                InetAddress.getByName("10.20.40.226"),
                8080);
        client.send(packet);

        client.close();
    }
}
