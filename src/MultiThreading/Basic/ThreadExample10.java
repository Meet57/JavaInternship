package MultiThreading.Basic;

import java.util.HashMap;
import java.util.Map;

/*
* Race condition where two threads are trying to read and write the map
* where they found value and don't...
* However, the if else works atomic... like if value is there it remove...
* else add
* One thread add the value, other detect it, first one remove
* it and other read it and says its null
* */

public class ThreadExample10 {
    private static Runnable getRunnable(Map<String, String> sharedMap){
        return ()->{
            synchronized (sharedMap){
                for (int i = 0; i < 1000000; i++) {
                    if(sharedMap.containsKey("Key")){
                        String val = sharedMap.remove("Key");
                        if(val == null){
                            System.out.println(i+" Null value");
                        }
                    }else{
                        sharedMap.put("Key","Value");
                    }
                }
            }
        };
    }

    public static void main(String[] args) {
        Map<String,String> map = new HashMap<>();

        Thread t1 = new Thread(getRunnable(map));
        Thread t2 = new Thread(getRunnable(map));

        t1.start();
        t2.start();
    }
}
