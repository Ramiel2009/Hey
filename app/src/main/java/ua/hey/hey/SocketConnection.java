package ua.hey.hey;

import android.os.AsyncTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * Created by mmaloshtan2 on 26.01.2016.
 */
public class SocketConnection extends AsyncTask<Void, Void, Void> {

    @Override
    protected Void doInBackground(Void... params) {
        Socket socket = null;
        String address = "94.244.34.33";
        String response = "";
        int port = 8082;
        try {
            socket = new Socket(address, port);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(1024);
            byte[] buffer = new byte[1024];
            int bytesRead;

            InputStream inputStream = socket.getInputStream();

            while ((bytesRead = inputStream.read(buffer))!=-1){
                byteArrayOutputStream.write(buffer, 0, bytesRead);
                response += byteArrayOutputStream.toString("UTF-8");
                System.out.println(response);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            if(socket != null){
                try {
                    socket.close();
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

