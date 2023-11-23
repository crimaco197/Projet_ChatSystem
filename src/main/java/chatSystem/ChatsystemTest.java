package chatSystem;

import java.io.IOException;
import java.util.Scanner;

public class ChatsystemTest {

    // Variable de control para el estado de ejecución del programa
    static volatile boolean running = true;

    public static void main(String[] args)  {

        // User object instance
        Scanner scan = new Scanner(System.in);
        System.out.println("Please choose an username and press enter");
        String username = scan.nextLine();

        Utilisateur user = new Utilisateur(username);


        try {
            // SendMessage and ReceiveMessage objects creation for using them in different threads
            MessageSender sender = new MessageSender(3333, user);
            MessageReceiver receiver = new MessageReceiver(3333 , user);


            // Starting thread to receive messages
            Thread t = new Thread(() -> {
                try {
                    receiver.run();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            t.start();

            // Sending connection initiation message
            sender.connect();


            // Scanner to sending a message to contact
            Scanner scanner = new Scanner(System.in);
            System.out.println("For sending a message to contact type: s-username-Content of the message");

            while (running) {
                String commandContent = scanner.nextLine();
                String[] command = commandContent.split("-");

                if (command.length == 3 && commandContent.startsWith("s")) {

                    if (user.contactList.getContacts().contains(command[1])) {
                        sender.messageToContact(user.getusername() + " : "+ command[2], 2222, command[1]);
                    } else {
                        System.out.println("User not found in contact list\n");
                    }
                } else if(commandContent.equals("goodbye")) {
                    sender.sendGoodbye();
                    running = false;
                    sender.close();
                    receiver.close();
                }
                else {
                    System.out.println("Command not found or mispelled");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
