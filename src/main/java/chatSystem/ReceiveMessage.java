package chatSystem;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class ReceiveMessage {

    private DatagramSocket socket ;
    private DatagramPacket receivePacket ;
    private static ContactDiscovery ContactList;
    private SendMessage Envoi ;
    private Utilisateur user ;


    public ReceiveMessage(int port , Utilisateur user) throws SocketException{
        socket = new DatagramSocket(port);
        receivePacket = new DatagramPacket(new byte[1024], 1024);
        ContactList = new ContactDiscovery();
        this.user= user;
        this.Envoi = new SendMessage(user);
    }

    public void run() throws IOException{
        while (true) {
            socket.receive(receivePacket);
            String message = new String(receivePacket.getData(), 0, receivePacket.getLength());

            String senderAddress = receivePacket.getAddress().getHostAddress();
            int senderPort = receivePacket.getPort();
            System.out.println("Greetings, " + senderAddress + " : " + senderPort + " : "+  message );
            System.out.println(ContactList.getContacts());

            if(message.startsWith("New_User:")){
                if (!ContactList.getContacts().contains(message.substring(9))) {
                    ContactList.adduser(message.substring(9), senderAddress);
                    System.out.println(ContactList.getContacts());
                }
                Envoi.sendmessage("New_User_Response:"+user.getusername(),8888 , receivePacket.getAddress());
            }
            if(message.startsWith("New_User_Response:")){

                ContactList.adduser(message.substring(18), senderAddress);
                System.out.println(ContactList.getContacts());

            }
            // Envoi.sendmessage("Hello",8888 , receivePacket.getAddress());
        }
    }

}
