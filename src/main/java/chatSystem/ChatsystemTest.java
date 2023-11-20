package chatSystem;

import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ChatsystemTest {

    // Variable de control para el estado de ejecución del programa
    static volatile boolean running = true;

    public static void main(String[] args) throws UnknownHostException {
        // Création de deux utilisateurs
        Utilisateur user1 = new Utilisateur("Luz",InetAddress.getLocalHost().toString());
      //  Utilisateur user1 = new Utilisateur("Cristian", "192.168.1.2");

        try {
            // Instanciation des objets SendMessage et ReceiveMessage pour chaque utilisateur
            SendMessage sendMessage1 = new SendMessage(user1);
            ReceiveMessage receiveMessage1 = new ReceiveMessage(2222 , user1);


            // Démarrage des threads de ReceiveMessage pour chaque utilisateur
            Thread t = new Thread(() -> {
                try {
                    receiveMessage1.run();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            t.start();

            // Simulation de la connexion de l'utilisateur 1
            sendMessage1.connect();

            // Attente pour permettre à ReceiveMessage d'afficher les résultats
            //t.sleep(2000);
            //me.CloseSocket();

            // Fermeture des sockets après le test
            //sendMessage1.close();

            // Scanner to sending a message to contact
            Scanner scanner = new Scanner(System.in);
            System.out.println("For sending a message to contact type: send-username-Content of the message");

            while (running) {
                String commandContent = scanner.nextLine();
                String[] command = commandContent.split("-");

                if (command.length == 3 && commandContent.startsWith("send")) {

                    if (user1.contactList.getContacts().contains(command[1])) {
                        sendMessage1.messageToContact(command[2], 2222, command[1]);
                    } else {
                        System.out.println("User not found in contact list\n");
                    }
                } else if(commandContent.equals("goodbye")) {
                    sendMessage1.sendGoodbye();
                    running = false;
                    sendMessage1.close();
                    receiveMessage1.close();
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
