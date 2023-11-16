package chatSystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ContactDiscovery {
    
    HashMap<String,String> Contacts ;

    public ContactDiscovery (){
         Contacts = new HashMap<> ();
    }

     public ArrayList<String> getContacts() {
        ArrayList<String> listenames = new ArrayList<>();
        for (Map.Entry<String, String> entry : Contacts.entrySet()) {
            String username = entry.getKey();
            listenames.add(username);
        }
        return listenames;
    }

    public void adduser(String username , String IPadresse){
        Contacts.put(username, IPadresse);
    }
}
