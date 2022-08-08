package MultiThreading.Project2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.*;

import org.apache.commons.net.util.SubnetUtils;

public class Worker {
    private String[] ipAddresses = null;
    private HashMap<String, String> result = null;

    Worker() {
        result = new HashMap<>();
    }

    Worker(String[] ip) {
        ipAddresses = ip;
        result = new HashMap<>();
    }

    public void setIpRange(String[] ips) {
        ipAddresses = ips;
    }

    public HashMap<String, String> run() {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        List<Callable<String>> callables = new ArrayList<>();

        for (int i = 0; i < ipAddresses.length; i++) {
            callables.add(PingClass.discovery(ipAddresses[i]));
        }

        try {
            List<Future<String>> results = executorService.invokeAll(callables);
            for (Future future : results) {
                String asd = (String) future.get();
                result.put(asd.split(" ")[0], asd.split(" ")[1]);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }

        executorService.shutdown();

        return result;
    }

    public String[] CIDRtoIP(String CIDR) {
        SubnetUtils subnetUtils = new SubnetUtils(CIDR);
        return subnetUtils.getInfo().getAllAddresses();
    }
}
