package com.twente.chat.server;

import java.io.*;
import java.net.Socket;

public class PlayerThread extends Thread {
    private Socket socket;
    private ChatServer server;
    private PrintWriter writer;

    public String getPlayer() {
        return player;
    }

    private String player;

    public PlayerThread(Socket socket, ChatServer server) {
        this.socket = socket;
        this.server = server;
    }

    public void run() {
        try {
            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));

            OutputStream output = socket.getOutputStream();
            writer = new PrintWriter(output, true);

            printUsers();

            String playerName = reader.readLine();
            player = playerName;
            server.playerName(playerName);

            String serverMessage = "New player connected: " + playerName;
            server.broadcast(serverMessage, this);

            String clientMessage;

            do {
                clientMessage = reader.readLine();
//                serverMessage = "[" + playerName + "]: " + clientMessage;
//                server.broadcast(serverMessage, this);

                getCommand(playerName, clientMessage);


            } while (!clientMessage.equals("bye"));

            server.removePlayer(playerName, this);
            socket.close();

            serverMessage = playerName + " has quitted.";
            server.broadcast(serverMessage, this);

        } catch (IOException ex) {
            System.out.println("Error in UserThread: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private void getCommand(String playerName, String clientMessage) throws IOException {

        if (clientMessage.equals("hello")) {
            server.broadcast("hello", this);

        } else if (clientMessage.equals("start")) {
            server.setPlayers();
            server.startCommand(this);

        } else if (clientMessage.contains("move")) {
            String[] arr = clientMessage.split(",");
            boolean isMoveDone = server.move(playerName, arr[1], arr[2], arr[3], arr[4]);
            if(isMoveDone){
                server.broadcast("move_done", this);
                server.broadcastDoMove("do_move",this);
                server.broadResultOfMove("results", this);
            } else{
                server.broadcast("error",this);
            }
        }

    }

    /**
     * Sends a list of online users to the newly connected user.
     */
    void printUsers() {
        if (server.hasUsers()) {
            writer.println("Connected users: " + server.getPlayers());
        } else {
            writer.println("No other users connected");
        }
    }

    /**
     * Sends a message to the client.
     */
    void sendMessage(String message) {
        writer.println(message);
    }
}
