package part1.lesson10.task02.client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

import static part1.lesson10.task02.server.ServerInfo.BUFFER_SIZE;

/**
 * @author Ekaterina Belolipetskaya
 */
public class ClientReceiveThread extends Thread {
    private DatagramSocket clientSocket;

    ClientReceiveThread(DatagramSocket datagramSocket) {
        this.clientSocket = datagramSocket;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                byte[] buf = new byte[BUFFER_SIZE];
                DatagramPacket receivedPacket = new DatagramPacket(buf, buf.length);
                clientSocket.receive(receivedPacket);
                System.out.println(new String(receivedPacket.getData()));
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }
    }
}
