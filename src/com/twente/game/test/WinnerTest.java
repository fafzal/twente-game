package com.twente.game.test;

import com.twente.game.helper.Color;
import com.twente.game.helper.Player;
import com.twente.game.helper.Ring;
import com.twente.game.helper.Winner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WinnerTest {

    Winner winner;

    Map <String, List <Integer>> boardIndex[][] = new HashMap[5][5];
    Map <String, List <Integer>> userMap = new HashMap <>();
    Player player;
    Player player2;


    @BeforeEach
    void setUp() {
        initialize();
        initializePlayer();

    }

    private void initializePlayer() {
        List <Color> colors = new ArrayList <>();
        colors.add(Color.YELLOW);
        player = new Player("player1", colors, new Ring());
        player2 = new Player("player2", colors, new Ring());
    }

    private void initialize() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                Map <String, List <Integer>> map = new HashMap <>();
                List <Integer> list = new ArrayList <>();
                map.put("player1", list);
                boardIndex[i][j] = map;
            }
        }
    }


    @Test
    void testPlayer1Winner() {

        List <Player> players = new ArrayList <>();
        players.add(player);

        winner = new Winner(players);

        List <Integer> list = new ArrayList <>();
        list.add(1);
        list.add(2);
        boardIndex[0][0].put("player1", list);
        Map <String, Integer> playerIsWinnerMap = winner.getPlayerPointsMap(player, boardIndex);
        assertEquals(Integer.valueOf(1), playerIsWinnerMap.get("[0][0]"));
    }


    @Test
    void testPlayer2Winner() {

        List <Player> players = new ArrayList <>();
        players.add(player);
        players.add(player2);

        winner = new Winner(players);

        List <Integer> list = new ArrayList <>();
        list.add(1);
        boardIndex[0][0].put("player1", list);

        list = new ArrayList <>();
        list.add(3);
        list.add(4);
        boardIndex[0][0].put("player2", list);

        Map <String, Integer> playerIsWinnerMap = winner.getPlayerPointsMap(player2, boardIndex);
        assertEquals(Integer.valueOf(1), playerIsWinnerMap.get("[0][0]"));
    }

}







