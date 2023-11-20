package chatSystem;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class ReceiveMessage {

    private DatagramSocket socket ;
    private DatagramPacket receivePacket ;
    //private static ContactDiscovery ContactList;
    private SendMessage Envoi ;
    private Utilisateur user ;


    public ReceiveMessage(int port , Utilisateur user) throws SocketException{
        socket = new DatagramSocket(port);
        receivePacket = new DatagramPacket(new byte[1024], 1024);
        //ContactList = new ContactDiscovery();
        this.user= user;
        this.Envoi = new SendMessage(user);
    }

    /**
     * @throws IOException
     */
    public void run() throws IOException{
        //while (true) {
        while (ChatsystemTest.running) {
            socket.receive(receivePacket);
            String message = new String(receivePacket.getData(), 0, receivePacket.getLength());

            String senderAddress = receivePacket.getAddress().getHostAddress();
            int senderPort = receivePacket.getPort();
            System.out.println("Greetings, " + senderAddress + " : " + senderPort + " : "+  message );
            System.out.println(user.contactList.getContacts());

            if(message.startsWith("New_User:")){
                if (!user.contactList.getContacts().contains(message.substring(9))) {
                    user.contactList.adduser(message.substring(9), senderAddress);
                    System.out.println(user.contactList.getContacts());
                }
                Envoi.sendmessage("New_User_Response:"+user.getusername(),8888 , receivePacket.getAddress());
            }
            if(message.startsWith("New_User_Response:")){
                user.contactList.adduser(message.substring(18), senderAddress);
                System.out.println(user.contactList.getContacts());

            }
            // Envoi.sendmessage("Hello",8888 , receivePacket.getAddress());
            
            if (message.startsWith("Goodbye:")) {
                String username = message.substring(8);
                user.contactList.removeUser(username);
                System.out.println("User '" + username + "' has left the network.");
                System.out.println(user.contactList.getContacts());
                ChatsystemTest.running = false;
            }
        }
        socket.close();
    }
}
