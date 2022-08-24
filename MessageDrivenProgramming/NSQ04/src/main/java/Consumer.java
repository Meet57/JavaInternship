import com.github.brainlag.nsq.NSQConsumer;
import com.github.brainlag.nsq.lookup.DefaultNSQLookup;
import com.github.brainlag.nsq.lookup.NSQLookup;

import java.util.concurrent.*;

/*
* Consumer control
* Here Patel will only get Even Number and Meet Will get odd number
* */

public class Consumer {
    public static void main(String[] args) {
        NSQLookup lookup = new DefaultNSQLookup();
        lookup.addLookupAddress("10.20.40.226", 4161);
        NSQConsumer consumer = new NSQConsumer(lookup, "Work", "1", (message) -> {
            String text = new String(message.getMessage());
            if(text.split(" ")[0].equals("Meet")){
                System.out.println("Meet got : "+text.split(" ")[1]);
                message.finished();
            }else{
                message.requeue();
            }
        });

        NSQConsumer consumer2 = new NSQConsumer(lookup, "Work", "1", (message) -> {
            String text = new String(message.getMessage());
            if(text.split(" ")[0].equals("Patel")){
                System.out.println("Patel got : "+text.split(" ")[1]);
                message.finished();
            }else{
                message.requeue();
            }
        });

        ExecutorService es = Executors.newFixedThreadPool(3);

        consumer.setExecutor(es);
        consumer2.setExecutor(es);

        consumer.start();
        consumer2.start();
    }
}
