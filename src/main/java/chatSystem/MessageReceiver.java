package chatSystem;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class MessageReceiver {
    private Utilisateur user;
    private DatagramSocket socket ;
    private DatagramPacket receivePacket ;
    //private static ContactDiscovery ContactList;
    private MessageSender sender;


    private boolean flagClose = true;

    public MessageReceiver(int port , Utilisateur user) throws SocketException{
        socket = new DatagramSocket(port);
        receivePacket = new DatagramPacket(new byte[1024], 1024);
        this.user= user;
        this.sender = new MessageSender(port, user);
    }

    public void run() throws IOException{

        while (flagClose) {
            socket.receive(receivePacket);
            String message = new String(receivePacket.getData(), 0, receivePacket.getLength());

            String senderAddress = receivePacket.getAddress().getHostAddress();
            int senderPort = receivePacket.getPort();
            //System.out.println("Greetings, " + senderAddress + " : " + senderPort + " : "+  message );
            System.out.println(message );
        //    System.out.println(user.contactList.getContacts());

            if(message.startsWith("New_User:")){
                if (!user.contactList.getContacts().contains(message.substring(9))) {
                    user.contactList.adduser(message.substring(9), senderAddress);
               //     System.out.println(user.contactList.getContacts());
                }
                sender.sendMessage("New_User_Response:"+user.getusername(),3333 , receivePacket.getAddress());
            }
            if(message.startsWith("New_User_Response:")){
                user.contactList.adduser(message.substring(18), senderAddress);
                System.out.println(user.contactList.getContacts());

            }
            
            if (message.startsWith("Goodbye:")) {
                String username = message.substring(8);
                user.contactList.removeUser(username);
                System.out.println("User '" + username + "' has left the network.");
             //   System.out.println(user.contactList.getContacts());
            }
        }


    }
    public void close(){
        flagClose = false;
    }
}
