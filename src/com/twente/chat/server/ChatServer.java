package com.twente.chat.server;

import com.twente.game.core.Board;
import com.twente.game.helper.Color;
import com.twente.game.helper.Player;
import com.twente.game.helper.Rank;
import com.twente.game.helper.Ring;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Predicate;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class ChatServer {
    private int port;
    private List <String> players = new ArrayList <String>();
    private Set <PlayerThread> playerThreads = new HashSet <>();
    private Board board;
    private Rank rank;

    public ChatServer(int port) {
        this.port = port;
    }

    public void execute() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {

            System.out.println("Chat Server is listening on port " + port);

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New user connected");

                PlayerThread newUser = new PlayerThread(socket, this);
                playerThreads.add(newUser);
                newUser.start();

            }

        } catch (IOException ex) {
            System.out.println("Error in the server: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Syntax: java ChatServer <port-number>");
            System.exit(0);
        }

        int port = Integer.parseInt(args[0]);

        ChatServer server = new ChatServer(port);
        server.execute();
    }

    void setPlayers() {
        board = new Board(players);
    }

    /**
     * Delivers a message from one user to others (broadcasting)
     */
    void sendMessageToPlayers(String message, PlayerThread player) {
        for (PlayerThread aUser : playerThreads) {
            if (aUser == player) {
                aUser.sendMessage(message);
            }
        }
    }

    void sendError(int errorCode, PlayerThread player) {
        for (PlayerThread aUser : playerThreads) {
            if (aUser == player) {
                aUser.sendError("error = " + errorCode);
            }
        }
    }

    void sendToMoveCommandToOtherPlayer(String message, PlayerThread player) {
        for (PlayerThread aUser : playerThreads) {
            if (aUser != player) {
                aUser.sendMessage(message);
            }
        }
    }

    void sendDoneMoveCommandToCurrentPlayer(String message, PlayerThread player) {
        for (PlayerThread aUser : playerThreads) {
            if (aUser == player) {
                aUser.sendMessage(message);

            }
        }
    }

    void sendMessageToPlayers(String message) {
        for (PlayerThread player : playerThreads) {
            player.sendMessage(message);
        }
    }

    /**
     * Stores username of the newly connected client.
     */
    void playerName(String userName) {
        players.add(userName);
    }

    /**
     * When a client is disconneted, removes the associated username and UserThread
     */
    void removePlayer(String userName, PlayerThread aUser) {
        boolean removed = players.remove(userName);
        if (removed) {
            playerThreads.remove(aUser);
            System.out.println("The user " + userName + " quitted");
        }
    }

    List <String> getPlayers() {
        return this.players;
    }

    /**
     * Returns true if there are other users connected (not count the currently connected user)
     */
    boolean hasUsers() {
        return !this.players.isEmpty();
    }

    public boolean move(String name, String x, String y, String size, String color) {
        Player player = new Player(name, Color.YELLOW, new Ring());
        return board.applySingleMove(player, Integer.valueOf(x), Integer.valueOf(y), Integer.valueOf(size), Color.YELLOW);
    }

    public void resetTurns() {
        Collection <PlayerThread> select = CollectionUtils.select(playerThreads, new PlayerTurnPredicate(false));
        if (select.size() == playerThreads.size()) {
            for (PlayerThread playerThread : playerThreads) {
                playerThread.setTurn(true);
            }
        }

    }

    private class PlayerTurnPredicate implements Predicate <PlayerThread> {

        private boolean isTurn = false;

        public PlayerTurnPredicate(boolean isTurn) {
            this.isTurn = isTurn;
        }

        @Override
        public boolean evaluate(PlayerThread playerThread) {
            return isTurn == playerThread.isTurn();
        }
    }
}
