package org.example;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URISyntaxException;

public class Main {
    public static void main(String[] args) throws IOException, URISyntaxException {
        ServerSocket serverSocket = new ServerSocket(5555); // listen on port number 5555
        Socket socket = serverSocket.accept(); // accept client connection
        OutputStream outputStream = socket.getOutputStream(); // get output stream of the socket

        File file = new File(
                Server.class.getResource("/music/music.mp3").toURI()
        );
        FileInputStream fileInputStream = new FileInputStream(file);

        byte[] buffer = new byte[4096]; // 4 KB buffer
        int bytesRead;

        while ((bytesRead = fileInputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead); // write bytes to output stream
        }

        fileInputStream.close();
        outputStream.close();
        socket.close();
        serverSocket.close();
    }
}