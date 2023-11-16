package chatSystem;

import java.io.IOException;
import java.net.*;

public class User {

    String username;
    String IP;
    ContactDiscovery ContactList = new ContactDiscovery();

    public User(String username, String IP) {
        this.username = username;
        this.IP = IP;

    }

    public void Connect()
    {
        try {
            DatagramSocket socket = new DatagramSocket();
            InetAddress broadcastAddress = InetAddress.getByName("255.255.255.255");
            int port = 8888;
            String message = "New_User:" + username;
            byte[] sendData = message.getBytes();
            DatagramPacket packet = new DatagramPacket(sendData,sendData.length, broadcastAddress,port);
            socket.send(packet);
            System.out.println("Broadcast sent successfully");
            socket.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void SendMessage(int port, InetAddress IPadresse) throws IOException {
        int port1 = port;
        String message1 = "New_User_Response:" + username;
        byte[] sendData1 = message1.getBytes();
        DatagramPacket packet1 = new DatagramPacket(sendData1, sendData1.length, IPadresse, port1);
        DatagramSocket socket = new DatagramSocket();
        socket.send(packet1);
    }

        public void ReceiveMessages() throws IOException {
        int port = 1789; // Specify the port to listen on

        try {
            DatagramSocket socket = new DatagramSocket(port);
            DatagramPacket receivePacket = new DatagramPacket(new byte[1024], 1024);

            while (true) {
                socket.receive(receivePacket);
                String message = new String(receivePacket.getData(), 0, receivePacket.getLength());
                String senderAddress = receivePacket.getAddress().getHostAddress();

                int senderPort = receivePacket.getPort();
                System.out.println("Greetings, " + senderAddress + " : " + senderPort + " : " + message);

                if (message.startsWith("New_User:")) {
                    if (!ContactList.getContacts().contains(message.substring(10))) {
                        ContactList.adduser(message, senderAddress);
                        //User newUser = new User();
                        //newUser.username = message.substring(10);
                        //newUser.IP = senderAddress;
                        SendMessage(8886, receivePacket.getAddress());
                        System.out.println(ContactList.getContacts());
                    }

                }
                else if(message.startsWith("New_User_Response:")) {
                    ContactList.adduser(message, senderAddress);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

