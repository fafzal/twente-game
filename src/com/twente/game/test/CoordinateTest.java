package com.twente.game.test;

import com.twente.game.helper.Coordinate;
import com.twente.game.helper.SingleMove;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CoordinateTest {

    Coordinate coordinate;

    Map <String, List <Integer>> boardIndex[][] = new HashMap[5][5];
    Map <String, List <Integer>> userMap = new HashMap <>();
    private SingleMove s;


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
    void scanBoard2Player() {
        List <String> players = new ArrayList <>();
        players.add("player1");
        players.add("player2");

        coordinate = new Coordinate(players);
        s = new SingleMove(players);
        boolean actual1 = s.move("player1", 2, boardIndex[2][3]);
        boolean actual = coordinate.isValidCoordinates("player1", 2, 3, boardIndex);
        boolean actual2 = coordinate.isValidCoordinates("player1", 2, 3, boardIndex);
        assertFalse(actual2);
        assertFalse(actual);
        assertTrue(actual1);
    }

    @Test
    void scanBoardStartBase() {

        List <String> players = new ArrayList <>();
        players.add("player1");
        players.add("player2");

        coordinate = new Coordinate(players);

        boolean result = coordinate.isValidMoveForBase("player", 7, 6, 2, boardIndex);
        assertFalse(result);


    }

    @Test
    void scanBoard2Player_() {
        List <String> players = new ArrayList <>();
        players.add("player1");
        players.add("player2");

        coordinate = new Coordinate(players);
        s = new SingleMove(players);
        boolean actual1 = s.move("player1", 2, boardIndex[2][3]);
        boolean actual = coordinate.isValidCoordinates("player1", -2, 3, boardIndex);
        assertFalse(actual);
        assertTrue(actual1);

    }
}







