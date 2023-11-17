package chatSystem;

import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class ChatsystemTest {
    public static void main(String[] args) throws SocketException, UnknownHostException {
        // Création de deux utilisateurs
        //User me = new User("luz", "192.168.1.1");
        User me = new User("cristian", InetAddress.getLocalHost().toString());
      //  Utilisateur me = new Utilisateur("cristian", InetAddress.getLocalHost().toString());
       // User me2 = new User("cristian", "192.168.1.2");

        //Utilisateur user2 = new Utilisateur("User2", "192.168.1.2");

        try {
            // Instanciation des objets SendMessage et ReceiveMessage pour chaque utilisateur
          //  SendMessage sendMessage1 = new SendMessage(me);
         //   ReceiveMessage receiveMessage1 = new ReceiveMessage(8888, me);

            /*SendMessage sendMessage2 = new SendMessage(user2);
            ReceiveMessage receiveMessage2 = new ReceiveMessage(8888);*/

            // Démarrage des threads de ReceiveMessage pour chaque utilisateur
            new Thread(() -> {
                try {
                    me.ReceiveMessages();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
/*
            new Thread(() -> {
                try {
                    receiveMessage1.run();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
*/
            // Simulation de la connexion de l'utilisateur 1
            me.Connect();
           // sendMessage1.connect();

            // Attente pour permettre à ReceiveMessage d'afficher les résultats
            Thread.sleep(2000);
           // me.CloseSocket();
            // Fermeture des sockets après le test
         //   sendMessage1.close();
            //sendMessage2.close();
            //receiveMessage1.close();
            //receiveMessage2.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    
}
