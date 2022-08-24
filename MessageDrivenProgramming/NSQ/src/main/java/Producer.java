import com.github.brainlag.nsq.NSQProducer;
import com.github.brainlag.nsq.exceptions.NSQException;

import java.util.concurrent.TimeoutException;

public class Producer {
    public static void main(String[] args) throws NSQException, TimeoutException, InterruptedException {
        NSQProducer producer = new NSQProducer().addAddress("localhost", 4150);
        producer.start();
        producer.produce("Meet", ("1").getBytes());
        producer.produce("Meet", ("2").getBytes());
        producer.produce("Meet", ("3").getBytes());
        producer.produce("Meet", ("4").getBytes());
        producer.shutdown();
    }
}
