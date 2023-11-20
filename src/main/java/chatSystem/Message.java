package chatSystem;

public class Message {
    private final String message;
    private final Utilisateur user;

    public Message( Utilisateur user,String message)
    {
        this.user = user;
        this.message = message;


    }

    public String getMessage()
    {
        return this.message;

    }

    public Utilisateur getEmetteur() {
        return this.user;
    }

}
