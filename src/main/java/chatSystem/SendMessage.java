package chatSystem;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;


public class SendMessage {
    private Utilisateur user;
    private DatagramSocket socket;

    public SendMessage(Utilisateur user) throws SocketException {
        this.user = user;
        this.socket = new DatagramSocket();
    }

    public void connect() {
        try {
            InetAddress broadcastAddress = InetAddress.getByName("255.255.255.255");
            int port = 8888;
            String message = "New_User:" + user.getusername();
            byte[] sendData = message.getBytes();
            DatagramPacket packet = new DatagramPacket(sendData, sendData.length, broadcastAddress, port);
            socket.send(packet);
            System.out.println("Broadcast sent successfully");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendmessage(int port, InetAddress IPadresse) throws IOException {
        int port1 = port;
        String message1 = "New_User:" + user.getusername();
        byte[] sendData1 = message1.getBytes();
        DatagramPacket packet1 = new DatagramPacket(sendData1, sendData1.length, IPadresse, port1);
        socket.send(packet1);
    }

    public void close() {
        socket.close();
    }
}
