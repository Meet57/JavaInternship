package MultiThreading.Project2;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;

public class Main {
    public static void main(String args[]) throws Exception {
        String[] arr = new String[]{
                "10.20.40.226"
                , "10.20.40.221"
                , "10.20.40.222"
                , "10.20.40.223"
                , "10.20.42.142" //shekhar
                , "10.20.40.113"
                , "10.20.40.204"
                , "192.168.0.191"
                , "192.168.0.192"
        };
        Worker worker = new Worker();
        worker.setIpRange(worker.CIDRtoIP("10.20.40.0/23"));
//        worker.setIpRange(worker.CIDRtoIP("192.168.0.0/24"));
//        worker.setIpRange(arr);
//        worker.setIpRange(worker.CIDRtoIP("192.168.0.100/23"));

        HashMap<String, String> discovery = worker.run();

        discovery.entrySet().forEach(entry -> {
            if (entry.getValue().equals("4"))
                System.out.println(entry.getKey() + " : " + ((entry.getValue().equals("4")) ? "UP" : "DOWN"));
        });

//        Arrays.stream(worker.CIDRtoIP("10.20.40.0/22")).forEach(System.out::println);
    }
}


