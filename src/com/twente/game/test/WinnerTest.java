package com.twente.game.test;

import com.twente.game.helper.Winner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WinnerTest {

    Winner winner = new Winner();

    Map <String, List <Integer>> boardIndex[][] = new HashMap[5][5];
    Map <String, List <Integer>> userMap = new HashMap <>();


    @BeforeEach
    void setUp() {
        initialize();
    }

    private void initialize() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                boardIndex[i][j] = new HashMap <>();
            }
        }
    }

    @Test
    public void testPlayer2Winner() {

        List <Integer> player1 = new ArrayList <>();
        List <Integer> player2 = new ArrayList <>();

        player1.add(2);
        player1.add(2);

        player2.add(2);
        player2.add(2);
        player2.add(2);

        userMap.put("player1", player1);
        userMap.put("player2", player2);
        userMap.put("player3", new ArrayList <>());
        userMap.put("player4", new ArrayList <>());

        boardIndex[0][0] = userMap;


        String winner = this.winner.decideWinner(boardIndex[0][0]);
        assertEquals("player2 win", winner);
    }

    @Test
    public void testPlayer4Winner() {


        List <Integer> player1 = new ArrayList <>();
        List <Integer> player2 = new ArrayList <>();
        List <Integer> player3 = new ArrayList <>();
        List <Integer> player4 = new ArrayList <>();

        player1.add(2);
        player2.add(3);
        player3.add(1);
        player4.add(0);

        userMap.put("player1", player1);
        userMap.put("player2", player2);
        userMap.put("player3", player3);
        userMap.put("player4", player4);

        boardIndex[0][0]= userMap;

        String draw = winner.decideWinner(boardIndex[0][0]);
        assertEquals("draw", draw);


    }

    @Test
    public void testPlayer3Winner() {
        List <Integer> player1 = new ArrayList <>();
        List <Integer> player2 = new ArrayList <>();
        List <Integer> player3 = new ArrayList <>();

        player1.add(2);
        
        player2.add(3);
        player2.add(4);

        player3.add(0);

        userMap.put("player1", player1);
        userMap.put("player2", player2);
        userMap.put("player3", player3);
        userMap.put("player4", new ArrayList <>());

        boardIndex[0][0]= userMap;

        String winner = this.winner.decideWinner(boardIndex[0][0]);
        assertEquals("player2 win", winner);

    }


    @Test
    void scanBoard2Player() {
        boolean actual = winner.isValidMove("player", 3, 4, 2, boardIndex);
        assertTrue(actual);
    }
    @Test
    void scanBoardStartBase() {
        boolean result = winner.isValidMoveForBase("player", 7,6,2, boardIndex);
        assertFalse(result);
    }
}







