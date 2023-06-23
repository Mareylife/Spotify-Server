package org.example.Models;

import com.google.gson.Gson;
import org.example.Server;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URISyntaxException;

public class Tools {
    private static Tools tools;
    public static Tools getInstance() {
        if (tools == null) {
            tools = new Tools();
        }
        return tools;
    }

    public Models.Request gsonToRequest(String gson) {
        Models.Request request = new Gson().fromJson(gson, Models.Request.class);
        return request;
    }

    public void sendMusic(ServerSocket serverSocket, Socket socket) throws IOException, URISyntaxException {
        OutputStream outputStream = socket.getOutputStream();
        File file = new File(
                Server.class.getResource("/music/music.mp3").toURI()
        );
        FileInputStream fileInputStream = new FileInputStream(file);

        byte[] buffer = new byte[4096]; // 4 KB buffer
        int bytesRead;

        while ((bytesRead = fileInputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }

        fileInputStream.close();
        outputStream.close();
        socket.close();
        serverSocket.close();
    }
}