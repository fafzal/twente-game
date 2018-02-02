package com.twente.game.helper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Winner {

    private List <String> players;

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

    public Map <String, Boolean> getPlayerWinnerMap(Player player, Map <String, List <Integer>>[][] board) {

        Map <String, Boolean> playerWinnerMap = new HashMap <>();

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                Map <String, List <Integer>> playerMap = board[i][j];
                List <Integer> integers = playerMap.get(player.getName());
                if (integers != null) {
                    int length = integers.size();
                    String index = "[" + i + "]" + "[" + j + "]";
                    playerWinnerMap.put(index, true);
                    for (String playerTmp : players) {
                        if (!playerTmp.equals(player.getName())) {
                            List <Integer> userPointList = playerMap.get(playerTmp);
                            if (!userPointList.isEmpty() && length <= userPointList.size()) {
                                playerWinnerMap.put(index, false);
                            }
                        }
                    }
                }
            }
        }
        return playerWinnerMap;
    }
}
