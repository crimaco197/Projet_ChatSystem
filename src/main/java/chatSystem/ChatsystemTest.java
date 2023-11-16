package chatSystem;

import java.io.IOException;

public class ChatsystemTest {
    public static void main(String[] args) {
        // Création de deux utilisateurs
        Utilisateur user1 = new Utilisateur("User1", "192.168.1.1");
        //Utilisateur user2 = new Utilisateur("User2", "192.168.1.2");

        try {
            // Instanciation des objets SendMessage et ReceiveMessage pour chaque utilisateur
            SendMessage sendMessage1 = new SendMessage(user1);
            ReceiveMessage receiveMessage1 = new ReceiveMessage(8888);

            /*SendMessage sendMessage2 = new SendMessage(user2);
            ReceiveMessage receiveMessage2 = new ReceiveMessage(8888);*/

            // Démarrage des threads de ReceiveMessage pour chaque utilisateur
            new Thread(() -> {
                try {
                    receiveMessage1.run();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();

            /*new Thread(() -> {
                try {
                    receiveMessage2.run();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();*/

            // Simulation de la connexion de l'utilisateur 1
            sendMessage1.connect();

            // Attente pour permettre à ReceiveMessage d'afficher les résultats
            Thread.sleep(2000);

            // Fermeture des sockets après le test
            sendMessage1.close();
            //sendMessage2.close();
            //receiveMessage1.close();
            //receiveMessage2.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    
}
