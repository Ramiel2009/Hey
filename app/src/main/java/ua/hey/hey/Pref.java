package ua.hey.hey;

import android.content.SharedPreferences;

/**
 * Created by Maxim on 2/6/16.
 */
public class Pref {
    public SharedPreferences sPref;
    private String messagesList;

    public String getMessagesList() {
        return messagesList;
    }

    public void setMessagesList(String messagesList) {
        this.messagesList = messagesList;
    }

    public String [] parse(){
        ParseResponse parseResponse = new ParseResponse();
        return parseResponse.parse(getMessagesList());
    }

    public void savePreferences(String s){
        if (s=="get_messages") {
            String a[] = parse();
            SharedPreferences.Editor editor = sPref.edit();
            for (int i=0; i<a.length; i++){
                editor.putString(Integer.toString(i), a[i]);
            }
            editor.commit();
        }
    }

}
