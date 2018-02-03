package com.twente.game.helper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Winner {

    private List <String> players;

    public Winner(List <String> players) {
        this.players = players;
    }

    public Map <String, Integer> getPlayerPointsMap(Player player, Map <String, List <Integer>>[][] board) {

        Map <String, Integer> playerWinnerMap = new HashMap <>();

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                Map <String, List <Integer>> playerMap = board[i][j];
                List <Integer> integers = playerMap.get(player.getName());
                if (integers != null) {
                    int length = integers.size();
                    String index = "[" + i + "]" + "[" + j + "]";
                    playerWinnerMap.put(index, 1);
                    for (String playerTmp : players) {
                        if (!playerTmp.equals(player.getName())) {
                            List <Integer> userPointList = playerMap.get(playerTmp);
                            if (!userPointList.isEmpty() && length <= userPointList.size()) {
                                playerWinnerMap.put(index, 0);
                            }
                        }
                    }
                }
            }
        }
        return playerWinnerMap;
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
