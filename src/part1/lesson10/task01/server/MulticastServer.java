package part1.lesson10.task01.server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

import static part1.lesson10.task01.server.ServerInfo.MULTI_SERVER_ADDRESS;


/**
 * MulticastServer
 * <p>
 * Implements MulticastSocket for broadcasting messages
 *
 * @author Ekaterina Belolipetskaya
 */
class MulticastServer {
    private MulticastSocket multicastSocket;
    private InetAddress groupAddress;

    MulticastServer(int port) throws IOException {
        this.multicastSocket = new MulticastSocket(port);
        this.groupAddress = InetAddress.getByName(MULTI_SERVER_ADDRESS);
        this.multicastSocket.joinGroup(groupAddress);
    }

    /**
     * @return host address of {@code multicastSocket}
     */
    String getGroupAddress() {
        return groupAddress.getHostAddress();
    }

    /**
     * @return port of {@code multicastSocket}
     */
    int getPort() {
        return multicastSocket.getLocalPort();
    }

    /**
     * Broadcast {@code message} through {@code multicastSocket}
     *
     * @param message that will be broadcasted
     * @throws IOException if an I/O error occurs.
     */
    void sendMessage(String message) throws IOException {
        DatagramPacket packet = new DatagramPacket(message.getBytes(), message.length(), groupAddress, multicastSocket.getLocalPort());
        multicastSocket.send(packet);
    }
}
