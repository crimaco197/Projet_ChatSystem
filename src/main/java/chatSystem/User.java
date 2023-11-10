package chatSystem;

import java.io.IOException;
import java.net.*;
import java.util.HashMap;

public class User {

    String username;
    HashMap<String,String> adresses = new HashMap<> ();

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

    public void ReceiveContacts()
    {
        int port = 1789; // Specify the port to listen on

        try {
            DatagramSocket socket = new DatagramSocket(port);
            System.out.println("UDP Server is listening on port " + port);

            byte[] receiveData = new byte[1024];

            while (true) {
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                socket.receive(receivePacket);
                String message = new String(receivePacket.getData(), 0, receivePacket.getLength());

                String senderAddress = receivePacket.getAddress().getHostAddress();
                int senderPort = receivePacket.getPort();
                System.out.println("Greetings, " + senderAddress + " : " + senderPort + " : "+  message );
                if(message.startsWith("New_User:")){
                    if(adresses.containsKey(message.substring(10))){
                        adresses.put(message, senderAddress);
                        DatagramSocket socket1 = new DatagramSocket();
                        int port1 = 8886;
                        String message1 = "New_User:" + username;
                        byte[] sendData1 = message1.getBytes();

                        DatagramPacket packet1 = new DatagramPacket(sendData1,sendData1.length, receivePacket.getAddress(),port1);
                        socket1.send(packet1);
                        socket1.close();

                    }

                }

                System.out.println(adresses);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {

    }
}

