package com.twente.game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BoardFillTest {

    BoardFill board = new BoardFill();

    Map <String, List <Integer>> boardIndex[][] = new HashMap[5][5];
    Map <String, List <Integer>> userMap = new HashMap <>();

    @BeforeEach
    void setUp() {
        boardIndex[0][0] = userMap;
    }

    @Test
    public void testPlayer1InsertSameMove() {


        String result;
        result = board.fill("player", 2, boardIndex[0][0]);
        assertEquals("player added with move", result);
    }

    @Test
    public void testPlayer1FailToInsertSameMove() {

        String result, result2;

        result = board.fill("player", 2, boardIndex[0][0]);
        result2 = board.fill("player", 2, boardIndex[0][0]);

        assertEquals("player added with move", result);
        assertEquals("move already added", result2);
    }

    @Test
    public void testPlayer1ToInsertDifferentAndSameMove() {

        String result, result2, result3;

        result = board.fill("player", 2, boardIndex[0][0]);
        result2 = board.fill("player", 3, boardIndex[0][0]);
        result3 = board.fill("player", 3, boardIndex[0][0]);


        assertEquals("player added with move", result);
        assertEquals("player added with move", result2);
        assertEquals("move already added", result3);


    }
}







