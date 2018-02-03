package com.twente.chat.server;

import com.twente.game.core.Board;
import com.twente.game.helper.Color;
import com.twente.game.helper.Player;
import com.twente.game.helper.Winner;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Predicate;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class ChatServer {
    private int port;
    private List <Player> players = new ArrayList <>();
    private Set <PlayerThread> playerThreads = new HashSet <>();
    private Board board;
    private Winner winner;

    public ChatServer(int port) {
        this.port = port;
    }

    public void execute() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {

            System.out.println("Server is listening on port " + port);

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New player connected");

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
            System.out.println("Syntax: java Server <port-number>");
            System.exit(0);
        }

        int port = Integer.parseInt(args[0]);

        ChatServer server = new ChatServer(port);
        server.execute();
    }

    void setPlayers() {
        board = new Board(players);
        winner = new Winner(players);
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

    void player(Player player) {
        players.add(player);
    }

    /**
     * When a client is disconneted, removes the associated username and UserThread
     */
    void removePlayer(Player player, PlayerThread aUser) {
        boolean removed = players.remove(player);
        if (removed) {
            playerThreads.remove(aUser);
            System.out.println("The user " + player.getName() + " quitted");
        }
    }

    List <Player> getPlayers() {
        return this.players;
    }

    /**
     * Returns true if there are other users connected (not count the currently connected user)
     */
    boolean hasUsers() {
        return !this.players.isEmpty();
    }

    public boolean move(Player player, String x, String y, String size, String color) {
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

    public void sendResults(PlayerThread playerThread) {

        if (playerThreads.size() == 1) {
            for (PlayerThread aUser : playerThreads) {
                if (aUser != playerThread) {
                    aUser.sendMessage("Name = " + aUser.getPlayer().getName() + ", PointsMap = " + board.getPlayerPointsMap(aUser.getPlayer()) + ", RingsMap = " + board.getPlayerRingsMap(aUser.getPlayer()));
                }
            }
        }
    }

    public boolean isMoveLeft(PlayerThread playerThread) {
        Player player = playerThread.getPlayer();
        boolean result = board.isMoveLeft(player);
        return result;
    }

    public void identityWinner() {

        int counter = 0;
        for (PlayerThread playerThread : playerThreads) {
            if (isMoveLeft(playerThread)) {
                counter++;
            }
        }

        if (counter == 0) {

            Map <Integer, Player> playerMap = new HashMap <>();
            for (Player player : players) {
                Map <String, Integer> map = board.getPlayerWinnerMap(player);
                counter = board.winnerMapCount(map);
                playerMap.put(counter, player);
            }
            List <Integer> keys = new ArrayList(playerMap.keySet());
            Collections.sort(keys);

            int max = keys.get(keys.size() - 1);
            Player player = playerMap.get(max);

            for (PlayerThread playerThread : playerThreads) {
                if (playerThread.getPlayer().getName().equals(player.getName())) {

                    playerThread.sendMessage("Name = " + playerThread.getPlayer().getName() + ", PointsMap = " + board.getPlayerPointsMap(playerThread.getPlayer()) + ", RingsMap = " + board.getPlayerRingsMap(playerThread.getPlayer()));

                }
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
