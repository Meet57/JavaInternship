package Example01;

import reactor.core.publisher.FluxSink;
import reactor.core.publisher.WorkQueueProcessor;

import java.time.Duration;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main{
    public static void main(String[] args) throws InterruptedException {

        ExecutorService es = Executors.newFixedThreadPool(2);

        WorkQueueProcessor<String> workQueueProcessor = WorkQueueProcessor.<String>builder().executor(es).build();

        workQueueProcessor.subscribe(t -> {
            System.out.println("1. "+Thread.currentThread().getName()+" "+t);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        workQueueProcessor.subscribe(t -> {
            System.out.println("2. "+Thread.currentThread().getName()+" "+t);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        workQueueProcessor.onNext("Meet");

        FluxSink<String> sink= workQueueProcessor.sink();
        for (int i = 65; i <= 78; i++) {
            sink.next(String.valueOf((char) i));
        }

        Thread.sleep(15000);

        for (int i = 79; i <= 90; i++) {
            sink.next(String.valueOf((char) i));
        }

        workQueueProcessor.awaitAndShutdown(Duration.ofSeconds(100));
    }
}