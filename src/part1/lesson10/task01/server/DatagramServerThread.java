package part1.lesson10.task01.server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.HashMap;
import java.util.Map;

import static part1.lesson10.task01.StringConstants.NAME_MESSAGE_SEPARATOR;
import static part1.lesson10.task01.StringConstants.SPACE;
import static part1.lesson10.task01.server.ServerInfo.BUFFER_SIZE;
import static part1.lesson10.task01.server.ServerInfo.MULTI_SERVER_PORT;

/**
 * DatagramServerThread
 * <p>
 * Implements Thread for server DatagramSocket
 *
 * @author Ekaterina Belolipetskaya
 */
public class DatagramServerThread extends Thread {
    private DatagramSocket datagramSocket;
    private Map<Integer, String> clientsMap;
    private MulticastServer multicastServer;

    DatagramServerThread(int port) throws IOException {
        this.datagramSocket = new DatagramSocket(port);
        this.clientsMap = new HashMap<>();
        this.multicastServer = new MulticastServer(MULTI_SERVER_PORT);
    }

    /**
     * Register clients by putting info about their names and ports into
     * {@code clientsMap}.
     *
     * @param packet clients packet, all info about client is taken from it.
     * @throws IOException if an I/O error occurs.
     */
    private void registerClient(DatagramPacket packet) throws IOException {
        clientsMap.put(packet.getPort(), new String(packet.getData()));
        String authorizationData = multicastServer.getGroupAddress() + SPACE + multicastServer.getPort();
        packet = new DatagramPacket(authorizationData.getBytes(), authorizationData.length(), packet.getAddress(), packet.getPort());
        datagramSocket.send(packet);
    }

    /**
     * Sign message with clients name.
     * Notify all registered clients about new message.
     *
     * @param packet that is got from the client. It will be resent
     *               to all registered clients.
     * @throws IOException if an I/O error occurs.
     */
    private void notifyClients(DatagramPacket packet) throws IOException {
        String message = new String(packet.getData()).trim();
        String clientName = clientsMap.get(packet.getPort()).trim();
        message = clientName + NAME_MESSAGE_SEPARATOR + message;
        multicastServer.sendMessage(message);
    }

    /**
     * Listening for new messages.
     * Initiates registration for new clients and
     * resending messages for all clients in the group.
     */
    @Override
    public void run() {
        while (true) {
            try {
                byte[] buf = new byte[BUFFER_SIZE];
                DatagramPacket receivedPacket = new DatagramPacket(buf, buf.length);
                datagramSocket.receive(receivedPacket);
                if (clientsMap.containsKey(receivedPacket.getPort())) {
                    notifyClients(receivedPacket);
                } else {
                    registerClient(receivedPacket);
                }
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }
    }
}
