package org.example.Models;

import com.google.gson.Gson;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URISyntaxException;
import java.util.Base64;

public class Tools {
    private static Tools tools;
    public static Tools getInstance() {
        if (tools == null) {
            tools = new Tools();
        }
        return tools;
    }

    public Request gsonToRequest(String gson) {
        Request request = new Gson().fromJson(gson, Request.class);
        return request;
    }

    public synchronized void sendMusic(Socket socket, String name) throws IOException, URISyntaxException {
        DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
        File file = new File(
                Tools.class.getResource("/musics/" + name).toURI()
        );
        FileInputStream fileInputStream = new FileInputStream(file);

        byte[] buffer = new byte[4096];
        int bytesRead;

        while ((bytesRead = fileInputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }
    }
}