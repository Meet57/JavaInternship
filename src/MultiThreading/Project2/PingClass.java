package MultiThreading.Project2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.concurrent.Callable;

public class PingClass {

    public static Callable<String> discovery(String ip) {
        return new Callable<String>() {
            @Override
            public String call() throws Exception {
                ProcessBuilder processBuilder = new ProcessBuilder();

                ArrayList<String> commandArgs = new ArrayList<>();

                commandArgs.add("/bin/sh");
                commandArgs.add("-c");
                commandArgs.add("ping -c 4 " + ip + " | tail -n 2 | head -n 1 | awk \'{print $4}\'");

                processBuilder.command(commandArgs);

                Process process = null;
                try {
                    System.out.println(Thread.currentThread().getName() + " Pinging : " + ip);
                    process = processBuilder.start();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                // to read the output
                BufferedReader input = new BufferedReader(new InputStreamReader
                        (process.getInputStream()));
                int s = 0;

                try {
                    s = Integer.valueOf(input.readLine());
                } catch (IOException e) {
                    s = -1;
                }

                return ip + " " + s;
            }
        };
    }
}
