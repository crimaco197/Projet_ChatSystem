package chatSystem;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ContactsList {

    HashMap<String,String> Contacts ;

    public ContactsList(){
        Contacts = new HashMap<> ();
    }

    public ArrayList<String> getContacts() {
        ArrayList<String> listNames = new ArrayList<>();
        for (Map.Entry<String, String> entry : Contacts.entrySet()) {
            String username = entry.getKey();
            listNames.add(username);
        }
        return listNames;
    }

    public void adduser(String username , String IPAddress){
        Contacts.put(username, IPAddress);
    }

    public InetAddress getIPAddress(String username) throws UnknownHostException {
        return InetAddress.getByName(Contacts.get(username));
    }

    public void removeUser(String username) {
        Contacts.remove(username);
    }

    
    }

