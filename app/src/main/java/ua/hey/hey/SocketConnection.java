package ua.hey.hey;

import android.content.Context;
import android.os.AsyncTask;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by mmaloshtan2 on 26.01.2016.
 */
public class SocketConnection extends AsyncTask<Void, Void, Void> {


    public void setContext(Context context) {
        this.context = context;
    }

    private Context context;

    private String recPhone;

    public String getRecPhone() {
        return recPhone;
    }

    public void setRecPhone(String recPhone) {
        this.recPhone = recPhone;
    }

    private String action;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    private String message;

    @Override
    protected Void doInBackground(Void... params) {
        Socket socket = null;
        String address = "192.168.0.52";
        int port = 8082;
        try {
            System.out.println("try\n");
            socket = new Socket(address, port);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            Variables var = new Variables();


            switch (action){
                case "send_message" :
                PhoneNumber pn = new PhoneNumber();
                    message = "action:" + action + ";" + pn.getPhoneNumber(context) + ";"
                            + this.getRecPhone()+";"+"00001"; //send message
                    oos.writeObject(message);
                    ObjectInputStream objectInputStream;
                    objectInputStream = new ObjectInputStream(socket.getInputStream());
                    var.setResponse((String) objectInputStream.readObject());
                    System.out.println(var.getResponse());
            break;
                case "get_messages":        //get message templates from server
                    message = "action:get_messages";
                    oos.writeObject(message);
                    objectInputStream = new ObjectInputStream(socket.getInputStream());
                    var.setResponse((String) objectInputStream.readObject());
                    Pref pref = new Pref();
                    pref.setMessagesList(var.getResponse());
                    pref.savePreferences("get_messages");
                    System.out.println(var.getResponse());

            break;
                case "get_message_log":     //get message history
                    message = "action:get_message_log";
                    oos.writeObject(message);
                    objectInputStream = new ObjectInputStream(socket.getInputStream());
                    var.setResponse((String) objectInputStream.readObject());
                    System.out.println(var.getResponse());
            break;
                    case "get_new_messages":   //get new messages from server
                    message = "action:get_new_messages;" + new PhoneNumber().getPhoneNumber(context);
                        while (true) {
                            oos.writeObject(message);
                            objectInputStream = new ObjectInputStream(socket.getInputStream());
                            var.setResponse((String) objectInputStream.readObject());
                            System.out.println(var.getResponse());
                            if (var.getResponse() != null){
                                //TODO show notification
                            }
                            Thread.sleep(100);
                        }
            }



        }
        catch (Exception e){
            System.out.println("Douch!");
            e.printStackTrace();
        }
        finally {
            if(socket != null){
                try {
                    socket.close();
                    System.out.println("connection closed");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
    @Override
    protected void onPostExecute (Void result){
        System.out.println("\ndone!");
    }
}