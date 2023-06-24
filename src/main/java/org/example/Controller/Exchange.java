package org.example.Controller;

import com.google.gson.Gson;
import org.example.Models.Request;
import org.example.Models.Response;
import org.example.Models.Tools;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Exchange {
    private static Exchange exchange;
    public static Exchange getInstance()
    {
        if (exchange == null) {
            exchange = new Exchange();
        }
        return exchange;
    }

    public Request read(Socket socket) {
        try {
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            String gson = dataInputStream.readUTF();
            return Tools.getInstance().gsonToRequest(gson);
        } catch (IOException e) {
            System.out.println("some error in read a request!");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void send (Response response, Socket socket) {
        try {
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            dataOutputStream.writeUTF(new Gson().toJson(response));
            dataOutputStream.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}