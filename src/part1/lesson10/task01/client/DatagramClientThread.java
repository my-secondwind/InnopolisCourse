package part1.lesson10.task01.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Objects;

import static part1.lesson10.task01.StringConstants.*;
import static part1.lesson10.task01.server.ServerInfo.BUFFER_SIZE;

/**
 * DatagramClientThread
 * <p>
 * Implements Thread for clients DatagramSocket
 *
 * @author Ekaterina Belolipetskaya
 */
public class DatagramClientThread extends Thread {
    private DatagramSocket datagramSocket;
    private String clientName;
    private InetAddress serverAddress;
    private int serverPort;
    private Thread multicastClientThread;


    DatagramClientThread(int clientPort, String clientName, InetAddress serverAddress, int serverPort) throws SocketException {
        this.datagramSocket = new DatagramSocket(clientPort);
        this.clientName = clientName;
        this.serverPort = serverPort;
        this.serverAddress = serverAddress;
    }

    /**
     * Thread for sending messages to the server by UPD.
     * Read text from console and send datagramPackets.
     * Stat chat in case {@code START_CHAT_STRING} is printed.
     */
    @Override
    public void run() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String text = reader.readLine();
            while (!(EMPTY_STRING.equals(text))) {
                if (Objects.equals(text, START_CHAT_STRING)) {
                    startMulticastSocket();
                } else {
                    DatagramPacket sendPacket = new DatagramPacket(text.getBytes(), text.length(), serverAddress, serverPort);
                    datagramSocket.send(sendPacket);
                }
                text = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        datagramSocket.close();
    }

    /**
     * Send message-request to server and start new thread for multicast socket.
     */
    private void startMulticastSocket() {
        DatagramPacket sendPacket = new DatagramPacket(clientName.getBytes(), clientName.length(), serverAddress, serverPort);
        try {
            datagramSocket.send(sendPacket);
            byte[] buf = new byte[BUFFER_SIZE];
            DatagramPacket receivedPacket = new DatagramPacket(buf, buf.length);
            datagramSocket.receive(receivedPacket);
            String[] messageWord = new String(receivedPacket.getData()).split(SPACE);
            InetAddress multicastAddress = InetAddress.getByName(messageWord[0]);
            int multicastPort = Integer.parseInt(messageWord[1].trim());
            multicastClientThread = new MulticastClientThread(multicastAddress, multicastPort);
            multicastClientThread.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
