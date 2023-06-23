package org.example.Controller;

import java.io.IOException;
import java.net.ServerSocket;

public class Connection {
    private ServerSocket serverSocket;
    public static final int SERVER_PORT = 7778;

    public Connection()  {
        try {
            this.serverSocket = new ServerSocket(SERVER_PORT);
        }catch (IOException ex) {
            System.out.println("server socket can not be created!");
            ex.printStackTrace();
        }
    }

    private static Connection connection;
    public static Connection getConnection() {
        if (connection == null) {
            connection = new Connection();
        }
        return connection;
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }
}