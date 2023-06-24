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
        File song = new File(
                Tools.class.getResource("/musics/" + name).toURI()
        );
        FileTransferProcessor f = new FileTransferProcessor(socket);
        f.sendFile(song);
    }
}