package chatSystem;

public class Utilisateur {

    private String username ;
    private String IPadresse ;

    public ContactDiscovery contactList;

    public Utilisateur (String username , String IPadresse){
        this.username = username ;
        this.IPadresse = IPadresse ;
        this.contactList = new ContactDiscovery();
    }

    public void setusername(String username ){
        this.username = username ;
    }

    public String getusername(){
        return this.username ;
    }

    public String getIPadresse(){
        return this.IPadresse ;
    }

}
