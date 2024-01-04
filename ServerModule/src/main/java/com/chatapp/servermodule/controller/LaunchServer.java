package com.chatapp.servermodule.controller;
import com.chatapp.servermodule.server.SampleServer;
import com.chatapp.servermodule.handler.ClientHandler;
import java.net.Socket;

public class LaunchServer {
    public static void main(String[] args) {
        int port = 5000;
        SampleServer server = new SampleServer(port);
        server.startServer();  //starting the server

        while (true) {
            Thread thread = new Thread( ()-> {
                Socket socket = server.acceptClientConnection();
                System.out.println("A New client has joined the Chat");
                ClientHandler clientHandler = new ClientHandler(socket);
                do {
                    clientHandler.readAndWriteFromClient();

                } while (!socket.isClosed());
            });
            thread.start();
        }
//        clientHandler.closeClientConnection();
//        server.stopServer();
    }
}
