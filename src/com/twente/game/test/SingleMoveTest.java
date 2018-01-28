package com.twente.game.test;

import com.twente.game.helper.SingleMove;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SingleMoveTest {

    SingleMove board;

    Map <String, List <Integer>> boardIndex[][] = new HashMap[5][5];
    Map <String, List <Integer>> userMap = new HashMap <>();

    @BeforeEach
    void setUp() {
        boardIndex[0][0] = userMap;
    }

    @Test
    public void testPlayer1InsertSameMove() {

        List <String> players = new ArrayList <>();
        players.add("player1");

        board = new SingleMove(players);

        boolean result;
        result = board.move("player1", 2, boardIndex[0][0]);
        assertEquals(true, result);
    }

    @Test
    public void testPlayer1FailToInsertSameMove() {


        List <String> players = new ArrayList <>();
        players.add("player1");
        players.add("player2");

        board = new SingleMove(players);

        boolean result, result2;

        result = board.move("player1", 2, boardIndex[0][0]);
        result2 = board.move("player2", 2, boardIndex[0][0]);

        assertEquals(true, result);
        assertEquals(false, result2);
    }

    @Test
    public void testPlayer1ToInsertDifferentAndSameMove() {

        List <String> players = new ArrayList <>();
        players.add("player1");
        players.add("player2");
        players.add("player3");

        board = new SingleMove(players);

        boolean result, result2, result3;

        result = board.move("player1", 2, boardIndex[0][0]);
        result2 = board.move("player2", 3, boardIndex[0][0]);
        result3 = board.move("player3", 3, boardIndex[0][0]);


        assertEquals(true, result);
        assertEquals(true, result2);
        assertEquals(false, result3);


    }
}







