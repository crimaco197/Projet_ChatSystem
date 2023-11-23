package chatSystem;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;


public class MessageSender {
    private Utilisateur user;
    private DatagramSocket socket;

    private int listenerPort;
    public MessageSender(int port, Utilisateur user) throws SocketException {
        this.user = user;
        this.socket = new DatagramSocket();
        listenerPort = port;
    }

    public void connect() {
        try {
            InetAddress broadcastAddress = InetAddress.getByName("255.255.255.255");
            String message = "New_User:" + user.getusername();
            byte[] sendData = message.getBytes();
            DatagramPacket packet = new DatagramPacket(sendData, sendData.length, broadcastAddress, listenerPort);
            socket.send(packet);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void sendMessage(String message , int port, InetAddress IPaddress) throws IOException {
        byte[] sendData1 = message.getBytes();
        DatagramPacket packet1 = new DatagramPacket(sendData1, sendData1.length, IPaddress, listenerPort);
        socket.send(packet1);
    }

    public void messageToContact(String message ,int port, String contactPseudo) throws IOException {
        byte[] sendData1 = message.getBytes();
        InetAddress ipAd = user.contactList.getIPAddress(contactPseudo);
        DatagramPacket packet1 = new DatagramPacket(sendData1, sendData1.length, ipAd, listenerPort);
        socket.send(packet1);
    }

    public void sendGoodbye(){
        try{
            InetAddress broadcastAddress = InetAddress.getByName("255.255.255.255");
            String message = "Goodbye:" + user.getusername();
            byte[] sendData = message.getBytes();
            DatagramPacket packet = new DatagramPacket(sendData, sendData.length, broadcastAddress, listenerPort);
            socket.send(packet);
            System.out.println("Goodbye message sent successfully");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void close() {
        socket.close();
    }
}
