package com.twente.game;


import java.util.List;
import java.util.Map;

public class Board {


    public String play(Map <String, List <Integer>> userMoveMap) {

        List <Integer> player1 = userMoveMap.get("player1");
        List <Integer> player2 = userMoveMap.get("player2");
        List <Integer> player3 = userMoveMap.get("player3");
        List <Integer> player4 = userMoveMap.get("player4");

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


}
