package Practice;

import java.net.*;
import java.io.*;

public class URLLoader {
    public static void main(String[] args) throws Exception {

        URL oracle = new URL("https://jsonplaceholder.typicode.com/posts/1");
        BufferedReader in = new BufferedReader(
                new InputStreamReader(oracle.openStream()));
        in.lines().forEach(System.out::println);
//        String inputLine;
//        while ((inputLine = in.readLine()) != null)
//            System.out.println(inputLine);
        in.close();
    }
}
