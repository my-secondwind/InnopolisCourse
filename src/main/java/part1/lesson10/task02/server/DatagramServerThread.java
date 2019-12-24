package part1.lesson10.task02.server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.*;

import static part1.lesson10.task02.StringConstants.*;
import static part1.lesson10.task02.server.ServerInfo.BUFFER_SIZE;
import static part1.lesson10.task02.server.ServerInfo.SERVER_ADDRESS;

/**
 * DatagramServerThread
 * <p>
 * Implements Thread for server DatagramSocket
 *
 * @author Ekaterina Belolipetskaya
 */
public class DatagramServerThread extends Thread {
    private DatagramSocket datagramSocket;
    private Map<Integer, String> allClientsMap;
    private Map<Integer, String> chatClientsMap;

    DatagramServerThread(int port) throws IOException {
        this.datagramSocket = new DatagramSocket(port);
        this.allClientsMap = new HashMap<>();
        this.chatClientsMap = new HashMap<>();
    }

    /**
     * Listening for new messages.
     * Initiates registration for all new clients and
     * invoke handling of the received packet.
     */
    @Override
    public void run() {
        while (true) {
            try {
                byte[] buf = new byte[BUFFER_SIZE];
                DatagramPacket receivedPacket = new DatagramPacket(buf, buf.length);
                datagramSocket.receive(receivedPacket);
                if (!allClientsMap.containsKey(receivedPacket.getPort())) {
                    registerClient(receivedPacket);
                    continue;
                }
                handlePacket(receivedPacket);
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }
    }

    /**
     * Register clients by putting info about their names and ports into
     * {@code clientsMap}.
     *
     * @param packet clients packet, all info about client is taken from it.
     * @throws IOException if an I/O error occurs.
     */
    private void registerClientForBroadcast(DatagramPacket packet) throws IOException {
        int port = packet.getPort();
        String clientName = allClientsMap.get(packet.getPort());
        chatClientsMap.put(port, clientName);
        String message = clientName + HELLO_STRING;
        sendBroadcastMessage(message, 0);
    }

    /**
     * Register all active clients.
     *
     * @param packet packet with clients name.
     */
    private void registerClient(DatagramPacket packet) {
        allClientsMap.put(packet.getPort(), new String(packet.getData()).trim());
    }

    /**
     * Remove client from {@code chatClientsMap}.
     *
     * @param packet from client that will be deleted.
     */
    private void deleteClientFromBroadcast(DatagramPacket packet) throws IOException {
        chatClientsMap.remove(packet.getPort());
        String clientName = chatClientsMap.get(packet.getPort());
        String message = clientName + BYE_STRING;
        sendBroadcastMessage(message, packet.getPort());
    }

    /**
     * Handling the received packet.
     * Invoke adding/deleting clients to/from chat.
     * Invoke unicast or broadcast message sending in case
     * sender id present or not.
     *
     * @param receivedPacket that received from the client and will be handled.
     * @throws IOException if any I/O errors occurs.
     */
    private void handlePacket(DatagramPacket receivedPacket) throws IOException {
        String message = new String(receivedPacket.getData()).trim();
        if (Objects.equals(message, START_CHAT_STRING)) {
            registerClientForBroadcast(receivedPacket);
            return;
        }
        if (Objects.equals(message, END_CHAT_STRING)) {
            deleteClientFromBroadcast(receivedPacket);
            return;
        }
        String[] partMessage = message.split(SPACE, 2);
        Collection<String> names = allClientsMap.values();
        if (names.contains(partMessage[0])) {
            sendUnicastMessage(receivedPacket, partMessage[0]);
        } else {
            sendBroadcastMessage(prepareBroadcastMessage(receivedPacket), receivedPacket.getPort());
        }
    }

    /**
     * Sign message with clients name.
     *
     * @param packet that is got from the client. It will be resent
     *               to all registered clients.
     */
    private String prepareBroadcastMessage(DatagramPacket packet) {
        String message = new String(packet.getData()).trim();
        String clientName = chatClientsMap.get(packet.getPort());
        message = clientName + NAME_MESSAGE_SEPARATOR + message;
        return message;
    }

    /**
     * Send message to all registered in chat clients except
     * sender.
     *
     * @param message    that was got from the client and will be sent to chat.
     * @param senderPort port of the client - author of the message. It won't got
     *                   this message.
     * @throws IOException if ane I/O errors occurs.
     */
    private void sendBroadcastMessage(String message, int senderPort) throws IOException {
        Set<Integer> portsSet = chatClientsMap.keySet();
        for (Integer port : portsSet) {
            if (Objects.equals(port, senderPort)) continue;
            DatagramPacket packet = new DatagramPacket(message.getBytes(), message.length(), InetAddress.getByName(SERVER_ADDRESS), port);
            datagramSocket.send(packet);
        }
    }

    /**
     * Sign message and resend it to receiver.
     *
     * @param receivedPacket that will be resent to another client (receiver).
     * @param receiver       name of the client, that should get the packet.
     * @throws IOException if any I/O errors occurs.
     */
    private void sendUnicastMessage(DatagramPacket receivedPacket, String receiver) throws IOException {
        int receiverPort = 0;
        for (Map.Entry<Integer, String> portClientEntry : allClientsMap.entrySet()) {
            if (Objects.equals(portClientEntry.getValue(), receiver)) {
                receiverPort = portClientEntry.getKey();
            }
        }
        String message = new String(receivedPacket.getData()).trim();
        String clientName = allClientsMap.get(receivedPacket.getPort());
        message = UNICAST_MESSAGE + clientName + NAME_MESSAGE_SEPARATOR + message;
        DatagramPacket packet = new DatagramPacket(message.getBytes(), message.length(), InetAddress.getByName(SERVER_ADDRESS), receiverPort);
        datagramSocket.send(packet);
    }
}
