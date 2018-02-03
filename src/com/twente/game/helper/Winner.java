package com.twente.game.helper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Winner {

    private List <Player> players;

    public Winner(List <Player> players) {
        this.players = players;
    }

    public Map <String, Integer> getPlayerPointsMap(Player player, Map <String, List <Integer>>[][] board) {

        Map <String, Integer> playerWinnerMap = new HashMap <>();

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                setPlayerWinnerMap(player, board[i][j], playerWinnerMap, i, j);
            }
        }
        return playerWinnerMap;
    }

    public void setPlayerWinnerMap(Player player, Map <String, List <Integer>> board, Map <String, Integer> playerWinnerMap, int i, int j) {
        Map <String, List <Integer>> playerMap = board;
        List <Integer> integers = playerMap.get(player.getName());
        if (integers != null) {
            int length = integers.size();
            String index = "[" + i + "]" + "[" + j + "]";
            playerWinnerMap.put(index, 1);
            for (Player playerTmp : players) {
                if (!playerTmp.getName().equals(player.getName())) {
                    List <Integer> userPointList = playerMap.get(playerTmp.getName());
                    if (userPointList != null && !userPointList.isEmpty() && length <= userPointList.size()) {
                        playerWinnerMap.put(index, 0);
                    }
                }
            }
        }
    }

    public Map <String, Integer> getPlayerRingsMap(Player player) {
        Map <String, Integer> playerPointsMap = new HashMap <>();
        Ring ring = player.getRing();

        playerPointsMap.put("0", ring.getBaseTile());
        playerPointsMap.put("1", ring.getSmall());
        playerPointsMap.put("2", ring.getMedium());
        playerPointsMap.put("3", ring.getBig());
        playerPointsMap.put("4", ring.getHuge());

        return playerPointsMap;
    }
}
