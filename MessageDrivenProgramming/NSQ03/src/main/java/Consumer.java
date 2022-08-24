import com.github.brainlag.nsq.NSQConsumer;
import com.github.brainlag.nsq.lookup.DefaultNSQLookup;
import com.github.brainlag.nsq.lookup.NSQLookup;
import java.util.concurrent.*;

public class Consumer {
    public static void main(String[] args) throws InterruptedException {
        NSQLookup lookup = new DefaultNSQLookup();
        lookup.addLookupAddress("10.20.40.226", 4161);
        NSQConsumer consumer = new NSQConsumer(lookup, "Meet", "Consumer2", (message) -> {
            String text = new String(message.getMessage());
            System.out.println("received: " + text);
            message.finished();
        });

//        ThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue)
//        Creates a new ThreadPoolExecutor with the given initial parameters and default thread factory and rejected execution handler.
        ExecutorService es = new ThreadPoolExecutor(3,3,100, TimeUnit.MILLISECONDS,new LinkedBlockingQueue<>(1));

        consumer.setExecutor(es);

        consumer.start();
        Thread.sleep(100000);
        consumer.shutdown();
    }
}
