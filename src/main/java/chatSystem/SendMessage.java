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
        new ContactDiscovery();
    }

    public void connect() {
        try {
            InetAddress broadcastAddress = InetAddress.getByName("255.255.255.255");
            int port = 2222;
            String message = "New_User:" + user.getusername();
            byte[] sendData = message.getBytes();
            DatagramPacket packet = new DatagramPacket(sendData, sendData.length, broadcastAddress, port);
            socket.send(packet);
            System.out.println("Broadcast sent successfully");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void sendmessage(String message ,int port, InetAddress IPadresse) throws IOException {
        int port1 = port;
        String message1 = message;
        byte[] sendData1 = message1.getBytes();
        DatagramPacket packet1 = new DatagramPacket(sendData1, sendData1.length, IPadresse, port1);
        socket.send(packet1);
    }

    public void messageToContact(String message ,int port, String contactPseudo) throws IOException {
        int port1 = port;
        String message1 = message;
        byte[] sendData1 = message1.getBytes();
        InetAddress ipAd = user.contactList.getIPAdress(contactPseudo);
        DatagramPacket packet1 = new DatagramPacket(sendData1, sendData1.length, ipAd, port1);
        socket.send(packet1);
    }

    public void sendGoodbye(){
        try{
            InetAddress broadcastAdress = InetAddress.getByName("255.255.255.255");
            int port = 2222;
            String message = "Goodbye:" + user.getusername();
            byte[] sendData = message.getBytes();
            DatagramPacket packet = new DatagramPacket(sendData, sendData.length, broadcastAdress, port);
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
