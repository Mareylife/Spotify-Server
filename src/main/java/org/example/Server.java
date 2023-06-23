package org.example;

import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(1234);
        System.out.println("Server listening on port 1234");

        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println("Client connected: " + socket.getInetAddress());

            // Get the audio file
            FileInputStream fis = new FileInputStream(
                    new File(
                            Server.class.getResource("/music/music.mp3").toURI()
                    ));
            BufferedInputStream bis = new BufferedInputStream(fis);

            // Send the audio file to the client
            OutputStream os = socket.getOutputStream();
            byte[] buffer = new byte[1024];
            int bytesRead = 0;
            while ((bytesRead = bis.read(buffer)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.flush();

            System.out.println("Audio file sent to client");

            bis.close();
            socket.close();
        }
    }
}
