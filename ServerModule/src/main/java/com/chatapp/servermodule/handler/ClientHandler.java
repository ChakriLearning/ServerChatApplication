package com.chatapp.servermodule.handler;

import java.io.*;
import java.net.Socket;

public class ClientHandler {
    private final Socket socket;
    private BufferedReader reader;
    private BufferedWriter writer;

    public ClientHandler(Socket socket)
    {
        this.socket = socket;
        try {
            this.reader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            this.writer = new BufferedWriter(new OutputStreamWriter(this.socket.getOutputStream()));
        } catch (IOException i) {
            i.printStackTrace();
        }
    }
    public void readAndWriteFromClient() {
        String msgFromClient = null;
        try {
            msgFromClient = reader.readLine();
            if (msgFromClient.equals("bye")) socket.close();
            System.out.println("Message From Client : " + msgFromClient);
            writer.write("server received : " + msgFromClient + "\n");
            writer.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void closeClientConnection(){
        try {
            socket.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }


//    public void writeToClient(String msgFromClient) {
//        try {
//            writer.write("Server Received: " + msgFromClient + "\n");
//            writer.flush();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

}

