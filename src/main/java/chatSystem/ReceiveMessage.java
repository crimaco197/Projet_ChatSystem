package chatSystem;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class ReceiveMessage {
    
   private DatagramSocket socket ;
   private DatagramPacket receivePacket ;
   private static ContactDiscovery Activusers ;
   private SendMessage Envoi ;


   public ReceiveMessage(int port) throws SocketException{
    socket = new DatagramSocket(port);
    receivePacket = new DatagramPacket(new byte[1024], 1024);
    Activusers = new ContactDiscovery();
  }

  public void run() throws IOException{
    while (true) {
        socket.receive(receivePacket);
        String message = new String(receivePacket.getData(), 0, receivePacket.getLength());

        String senderAddress = receivePacket.getAddress().getHostAddress();
        int senderPort = receivePacket.getPort();
        System.out.println("Greetings, " + senderAddress + " : " + senderPort + " : "+  message );

        if(message.startsWith("New_User:")){
            if(Activusers.getContacts().contains(message.substring(10))){
                Activusers.adduser(message, senderAddress);
                Envoi.sendmessage(8886 , receivePacket.getAddress());
                

            }

        }

    }
  }

}
