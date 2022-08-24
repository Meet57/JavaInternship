import com.github.brainlag.nsq.NSQProducer;
import com.github.brainlag.nsq.exceptions.NSQException;

import java.util.Scanner;
import java.util.concurrent.TimeoutException;

public class Producer {
    public static void main(String[] args) throws NSQException {
        NSQProducer producer = new NSQProducer().addAddress("localhost", 4150).start();

        Scanner sc = new Scanner(System.in);
        String text = null;
        while (true){
            text = sc.nextLine();
            if(text.equals("exit")) break;
            try {
                producer.produce("Meet",text.getBytes());
            } catch (TimeoutException e) {
                throw new RuntimeException(e);
            }
        }
        producer.shutdown();
    }
}
