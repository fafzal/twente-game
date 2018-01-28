package com.twente.game.helper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class SingleMove {


    private List <String> players;

    public SingleMove(List <String> players) {
        this.players = players;

    }

    public boolean move(String player, int move, Map <String, List <Integer>> userMap) {

        if (!isSameMoveAlreadyExits(userMap, move)) {
            List <Integer> moveList = userMap.get(player);
            if (moveList == null) {
                moveList = new ArrayList <>();
                moveList.add(move);
                userMap.put(player, moveList);
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

        for (String player : players) {
            List <Integer> list = userMap.get(player);
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
