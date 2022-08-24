import com.github.brainlag.nsq.NSQProducer;
import com.github.brainlag.nsq.exceptions.NSQException;

import java.util.concurrent.TimeoutException;

public class Producer {
    public static void main(String[] args) throws NSQException, TimeoutException, InterruptedException {
        NSQProducer producer = new NSQProducer().addAddress("10.20.40.226", 4150).start();

        for (int i = 0; i < 50; i++) {
            producer.produce("Meet",String.valueOf(i).getBytes());
            Thread.sleep(10);
        }

        producer.shutdown();
    }
}
