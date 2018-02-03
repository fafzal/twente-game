package com.twente.chat.server;

import com.twente.game.helper.Color;
import com.twente.game.helper.Player;
import com.twente.game.helper.Ring;

import java.io.*;
import java.net.Socket;

public class PlayerThread extends Thread {
    private Socket socket;
    private ChatServer server;
    private PrintWriter writer;
    private boolean turn = true;
    private Player player;

    public Player getPlayer() {
        return player;
    }

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

            for (Player player : server.getPlayers()) {
                if (player.getName().equals(playerName)) {
                    server.sendError(3, this);
                    return;
                }
            }

            player = new Player(playerName, Color.YELLOW, new Ring());
            server.player(player);

//            String serverMessage = "New player connected: " + playerName;
//            server.sendMessageToPlayers(serverMessage, this);

            String clientMessage;

            do {
                clientMessage = reader.readLine();
//                serverMessage = "[" + playerName + "]: " + clientMessage;
//                server.broadcast(serverMessage, this);

                getCommand(clientMessage);


            } while (!clientMessage.equals("player_left"));

            server.removePlayer(player, this);
            socket.close();

            String serverMessage = playerName + " has left.";
            server.sendToMoveCommandToOtherPlayer(serverMessage, this);
            server.sendResults(this);



        } catch (IOException ex) {
            System.out.println("Error in PlayerThread: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private void getCommand(String clientMessage) throws IOException {

        if (clientMessage.equals("hello")) {
            server.sendMessageToPlayers("hello", this);

        } else if (clientMessage.equals("start")) {
            server.setPlayers();
            server.sendMessageToPlayers("start");

        } else if (clientMessage.contains("move")) {
            server.resetTurns();
            if (isTurn()) {
                extractAndSendNotification(clientMessage);
                setTurn(false);
            } else {
                server.sendError(2, this);
            }
        } else if (!clientMessage.contains("player_left")) {
            server.sendError(4, this);
        }

    }

    private void extractAndSendNotification(String clientMessage) {
        String[] arr = clientMessage.split(",");
        String x = arr[1];
        String y = arr[2];
        Integer intX = Integer.valueOf(x);
        Integer intY = Integer.valueOf(y);
        if (intX >= 0 && intX < 5 && intY >= 0 && intY < 5) {
            boolean isMoveDone = server.move(player, x, y, arr[3], arr[4]);
            if (isMoveDone) {
                server.sendMessageToPlayers("move_done", this);
                server.sendToMoveCommandToOtherPlayer("do_move", this);
                server.sendDoneMoveCommandToCurrentPlayer("done_move", this);
            } else {
                server.sendMessageToPlayers("errorcode = 0", this);
            }
        } else {
            server.sendMessageToPlayers("errorcode = 1", this);
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

    void sendError(String message) {
        writer.println(message);
    }

    public boolean isTurn() {
        return turn;
    }

    public void setTurn(boolean turn) {
        this.turn = turn;
    }
}
