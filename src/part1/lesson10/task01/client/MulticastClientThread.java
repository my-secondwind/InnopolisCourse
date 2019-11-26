package part1.lesson10.task01.client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

import static part1.lesson10.task01.server.ServerInfo.BUFFER_SIZE;

/**
 * MulticastClientThread
 * <p>
 * Implements Thread for clients MulticastSocket
 *
 * @author Ekaterina Belolipetskaya
 */
public class MulticastClientThread extends Thread {
    private MulticastSocket multicastClientSocket;


    MulticastClientThread(InetAddress serverAddress, int port) throws IOException {
        this.multicastClientSocket = new MulticastSocket(port);
        multicastClientSocket.joinGroup(serverAddress);
    }

    /**
     * Listening for messages that are sent to multicastSocket group
     */
    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                byte[] buf = new byte[BUFFER_SIZE];
                DatagramPacket receivedPacket = new DatagramPacket(buf, buf.length);
                multicastClientSocket.receive(receivedPacket);
                System.out.println(new String(receivedPacket.getData()));
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
            if (Thread.currentThread().isInterrupted()) {
                multicastClientSocket.close();
                return;
            }
        }
    }
}
