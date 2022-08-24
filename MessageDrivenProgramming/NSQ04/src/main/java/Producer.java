import com.github.brainlag.nsq.NSQProducer;
import com.github.brainlag.nsq.exceptions.NSQException;

import java.util.concurrent.TimeoutException;

public class Producer {
    public static void main(String[] args) throws NSQException, TimeoutException, InterruptedException {
        NSQProducer producer = new NSQProducer().addAddress("10.20.40.226", 4150).start();

        for (int i = 0; i < 50; i++) {
            if(i%2==0){
                producer.produce("Work",("Patel "+String.valueOf(i)).getBytes());
            }else{
                producer.produce("Work",("Meet "+String.valueOf(i)).getBytes());
            }
        }

        producer.shutdown();
    }
}
