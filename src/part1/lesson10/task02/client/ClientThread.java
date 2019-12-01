package part1.lesson10.task02.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import static part1.lesson10.task02.StringConstants.EMPTY_STRING;
import static part1.lesson10.task02.server.ServerInfo.SERVER_ADDRESS;
import static part1.lesson10.task02.server.ServerInfo.SERVER_PORT;

/**
 * @author Ekaterina Belolipetskaya
 */
public class ClientThread extends Thread {
    private final String clientName;
    private final DatagramSocket clientSocket;
    private ClientReceiveThread receiveThread;

    ClientThread(int clientPort, String clientName) throws IOException {
        this.clientName = clientName;
        this.clientSocket = new DatagramSocket(clientPort);
        receiveThread = new ClientReceiveThread(clientSocket);
        receiveThread.start();
        sendMessageToServer(this.clientName);
    }

    /**
     * Thread for sending messages to the server by UPD.
     * Read text from console and send datagramPackets.
     */
    @Override
    public void run() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String text = reader.readLine();
            while (!(EMPTY_STRING.equals(text))) {
                sendMessageToServer(text);
                text = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        closeClient();
    }

    /**
     * Interrupt receiveThread and close client socket.
     */
    private void closeClient() {
        receiveThread.interrupt();
        clientSocket.close();
    }

    /**
     * Make a new packet and send it to the server
     *
     * @param message that replaced to the packet
     * @throws IOException if an I/O error occurs
     */
    private void sendMessageToServer(String message) throws IOException {
        byte[] buf = message.getBytes();
        DatagramPacket datagramPacket = new DatagramPacket(buf, buf.length, InetAddress.getByName(SERVER_ADDRESS), SERVER_PORT);
        clientSocket.send(datagramPacket);
    }
}
