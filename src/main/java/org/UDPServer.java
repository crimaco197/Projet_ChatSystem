package org;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPServer {
    public static void main(String[] args) {
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
                
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

