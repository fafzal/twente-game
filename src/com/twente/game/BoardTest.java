package com.twente.game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BoardTest {

    Board board = new Board();

    Map <String, List <Integer>> boardIndex[][] = new HashMap[5][5];
    Map <String, List <Integer>> userMap = new HashMap <>();

    @BeforeEach
    void setUp() {
        boardIndex[0][0] = userMap;
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


        String winner = board.play(boardIndex[0][0]);
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

        String draw = board.play(boardIndex[0][0]);
        assertEquals("draw", draw);


    }

    @Test
    public void testPlayer3Winner() {
        List <Integer> player1 = new ArrayList <>();
        List <Integer> player2 = new ArrayList <>();
        List <Integer> player3 = new ArrayList <>();

        player1.add(2);
        player1.add(1);

        player2.add(3);
        player2.add(4);
        player2.add(2);

        player3.add(0);

        userMap.put("player1", player1);
        userMap.put("player2", player2);
        userMap.put("player3", player3);
        userMap.put("player4", new ArrayList <>());

        String winner = board.play(boardIndex[0][0]);
        assertEquals("player2 win", winner);

    }


}







