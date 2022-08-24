package MultiThreading.Project3;/* -*-mode:java; c-basic-offset:2; indent-tabs-mode:nil -*- */
/**
 * This program will demonstrate remote exec.
 *   $ CLASSPATH=.:../build javac Exec.java
 *   $ CLASSPATH=.:../build java Exec
 * You will be asked username, hostname, displayname, passwd and command.
 * If everything works fine, given command will be invoked
 * on the remote side and outputs will be printed out.
 *
 */
import com.jcraft.jsch.*;
import java.awt.*;
import javax.swing.*;
import java.io.*;
public class Main {
    JSch jsch = new JSch();
    Session session;
    Channel channel;

    public Session openSession() throws Exception {

        if (null == session) {
            session = jsch.getSession("meet", "10.20.40.226",
                    22);


            session.setPassword(".,.");
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect(10 * 1000);

        }
        return session;
    }

    public Channel openChannel(Session session) throws Exception {

        if (null == channel) {
            channel = session.openChannel("exec");
        }

        return channel;
    }

    public String getSession(String command1) throws Exception {

        try {

            session = openSession();
            channel = openChannel(session);

            Channel channel = session.openChannel("exec");
            ((ChannelExec) channel).setCommand(command1);
            channel.setInputStream(null);

            InputStream in = channel.getInputStream();
            channel.connect();
            byte[] tmp = new byte[1024];
            String readText = "";
            while (true) {
                while (in.available() > 0) {
                    int i = in.read(tmp, 0, 1024);
                    if (i < 0)
                        break;
                    readText = new String(tmp, 0, i);

                    System.out.print(readText);
                }
                if (channel.isClosed()) {
                    break;
                }
                try {
                    Thread.sleep(1000);
                } catch (Exception ee) {
                }
            }
            channel.disconnect();

        } catch (Throwable t) {
            System.out.println(t);
        }
        return null;

    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        try {
            Main ssh = new Main();
            ssh.getSession("pwd");
        } catch (Exception e) {
        }

    }

}
