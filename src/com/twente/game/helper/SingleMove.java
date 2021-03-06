package com.twente.game.helper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class SingleMove {


    private List <Player> players;

    public SingleMove(List <Player> players) {
        this.players = players;

    }

    public boolean move(Player player, int move, Map <String, List <Integer>> userMap) {

        if (!isSameMoveAlreadyExits(userMap, move)) {
            List <Integer> moveList = userMap.get(player.getName());
            if (moveList == null) {
                moveList = new ArrayList <>();
                moveList.add(move);
                userMap.put(player.getName(), moveList);
            } else {
                moveList.add(move);
            }
        } else {
            return false;
        }
        return true;

    }

    private boolean isSameMoveAlreadyExits(Map <String, List <Integer>> userMap, int move) {

        boolean flag = true;

        for (Player player : players) {
            List <Integer> list = userMap.get(player.getName());
            if (list != null && list.contains(move)) {
                flag = true;
                break;
            } else {
                flag = false;
            }
        }

        if (flag) {
            return true;
        } else {
            return false;
        }

    }
}
