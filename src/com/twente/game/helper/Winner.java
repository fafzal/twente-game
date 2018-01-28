package com.twente.game.helper;


import java.util.List;
import java.util.Map;

public class Winner {
    private List <String> players;
    private int move1;

    public Winner(List <String> players) {
        this.players = players;
    }

    public String decideWinner(Map <String, List <Integer>> userMoveMap) {

        List <Integer> player1 = userMoveMap.get(players.get(0));
        List <Integer> player2 = userMoveMap.get(players.get(1));
        List <Integer> player3 = userMoveMap.get(players.get(2));
        List <Integer> player4 = userMoveMap.get(players.get(3));

        return winner(player1, player2, player3, player4);

    }

    private String winner(List <Integer> player1, List <Integer> player2, List <Integer> player3, List <Integer> player4) {

        if (player1.size() > player2.size() && player1.size() > player3.size() && player1.size() > player4.size()) {
            return "player1 win";
        } else if (player2.size() > player1.size() && player2.size() > player3.size() && player2.size() > player4.size()) {
            return "player2 win";
        } else if (player3.size() > player1.size() && player3.size() > player2.size() && player3.size() > player4.size()) {
            return "player3 win";
        } else if (player4.size() > player1.size() && player4.size() > player2.size() && player4.size() > player3.size()) {
            return "player4 win";
        }
        return "draw";
    }


    public boolean isValidCoordinates(String player, int x, int y, int move, Map <String, List <Integer>>[][] board) {

        boolean isEmpty =true;

        if (!(x >= 0 && x <= 5 && y >= 0 && y <= 5)) {
            return false;
        }

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                Map <String, List <Integer>> element = board[i][j];
                if (element != null && element.containsKey(player)){
                    isEmpty = false;
                }

            }
        }

        if(isEmpty){
            return true;
        }

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                Map <String, List <Integer>> element = board[i][j];
                if (element != null && element.containsKey(player)) {
                    if ((i - 1 == x || i + 1 == x) && (j - 1 == y || j + 1 == y)) {
                        return true;
                    }

                }
            }
        }

        return false;

    }

    public boolean isValidMoveForBase(String player, int x, int y, int move, Map <String, List <Integer>>[][] board1) {
        boolean isEmpty = true;
        if (!(x >= 1 && x <= 4 && y >= 1 && y <= 4)) {
            return false;
        }
        for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= 3; j++) {
                Map <String, List <Integer>> element = board1[i][j];
                if (element.size() != 0) {
                    isEmpty = false;

                }
            }
        }
        return isEmpty;
    }

    
}
