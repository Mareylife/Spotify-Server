package org.example.View;


import org.example.Controller.Connection;
import org.example.Controller.Exchange;
import org.example.Controller.Processor;
import org.example.Models.Request;
import org.example.Models.RequestType;


import java.io.IOException;
import java.net.Socket;
import java.net.URISyntaxException;

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
                if (request.getAction() == RequestType.Logout)
                {
                    System.out.println("(" + client + ") is out!");
                    break;
                }
                else if (request.getAction() == RequestType.Play)
                {
                    try {
                        Processor.getInstance().Play(request, client);
                    } catch (URISyntaxException e) {
                        throw new RuntimeException(e);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                else if (request.getAction() == RequestType.ReFresh)
                {
                    try {
                        Exchange.getInstance().send(Processor.getInstance().Refresh(request), client);
                    } catch (URISyntaxException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }).start();
    }
}