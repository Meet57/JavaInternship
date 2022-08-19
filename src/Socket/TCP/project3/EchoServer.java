package Socket.TCP.project3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        System.out.println("Waiting for clients...");
        ServerSocket st = new ServerSocket(8080);

        Socket soc = st.accept();
        System.out.println("Connection Established");

        BufferedReader in = new BufferedReader(new InputStreamReader(soc.getInputStream()));
        PrintWriter out = new PrintWriter(soc.getOutputStream(),true);
        Scanner sc = new Scanner(System.in);

        String str = "";
        while (!str.equals("Over")) {
            str = in.readLine();
            System.out.println("Client: "+str);
            System.out.print("Server: ");
            str = sc.nextLine();
            out.println(str);
        }

//        BufferedReader in = new BufferedReader(new InputStreamReader(soc.getInputStream()));
//        String str = in.readLine();
//        System.out.println("Server got: "+ str);
//
//        PrintWriter out = new PrintWriter(soc.getOutputStream(),true);
//        out.println("Server says: "+str);
    }
}
