package MultiThreading.Project2;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;

public class Main {
    public static void main(String args[]) throws Exception{
        String[] arr = new String[]{
                "10.20.40.226"
                ,"10.20.40.221"
                ,"10.20.40.222"
                ,"10.20.40.223"
                ,"10.20.42.142"
                ,"10.20.40.113"
                ,"10.20.40.204"
        };
//        Worker worker = new Worker(arr);
        Worker worker = new Worker();
        worker.setIpRange(worker.CIDRtoIP("10.20.40.230/29"));

        HashMap<String,String> discovery = worker.run();

        discovery.entrySet().forEach(entry -> {
            System.out.println(entry.getKey() + " = " + entry.getValue());
        });

//        Arrays.stream(worker.CIDRtoIP("10.20.40.230/28")).forEach(System.out::println);
    }
}


