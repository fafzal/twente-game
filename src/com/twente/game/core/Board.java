package com.twente.game.core;

import com.twente.game.helper.Color;
import com.twente.game.helper.SingleMove;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {

    SingleMove singleMove;

    private List <String> players;
    private Map <String, List <Integer>> boardArray[][] = new HashMap[5][5];

    public Board(List <String> players) {
        this.players = players;
        initialize();
        singleMove = new SingleMove(players);
    }

    public boolean applySingleMove(String player, int x, int y, int size, Color yellow) {
        return singleMove.move(player, size, boardArray[x][y]);
    }


    private void initialize() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                boardArray[i][j] = new HashMap <>();
            }
        }
    }
}
