package org.example.View;

import Models.Request;
import org.example.Controller.Connection;
import org.example.Controller.Exchange;
import org.example.Controller.Processor;
import org.example.Models.RequestType;

import java.io.IOException;
import java.net.Socket;

public class Server {

    private static Server server;
    public static Server getServer()
    {
        if (server == null) {

            server = new Server();
        }
        return server;
    }

    public void run() {
        System.out.println("server is up! (" + Connection.getConnection().getServerSocket() + ")");
        listenMode();
    }

    private void listenMode() {
        try {
            Socket client;
            while ((client = Connection.getConnection().getServerSocket().accept()) != null) {
                System.out.println("connected a client! (" + client + ")");
                clientHandler(client);
            }
        }catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void clientHandler(Socket client) {
        new Thread(() -> {
            while (true)
            {
                Request request = Exchange.getInstance().read(client);
                if (request.getAction() == RequestType.Logout) {
                    System.out.println("(" + client + ") is out!");
                    break;
                }
                Exchange.getInstance().send(Processor.getInstance().process(request), client);
            }
        }).start();
    }
}