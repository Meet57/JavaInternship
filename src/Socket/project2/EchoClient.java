package Socket.project2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class EchoClient {
    public static void main(String[] args) throws IOException {
        System.out.println("Client started");
        Socket soc = new Socket("10.20.40.226", 8080);

        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        PrintWriter out = new PrintWriter(soc.getOutputStream(), true);
        out.println(str);

        BufferedReader in = new BufferedReader(new InputStreamReader(soc.getInputStream()));
        System.out.println(in.readLine());
    }
}
