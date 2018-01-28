package com.twente.game.test;

import com.twente.game.core.Board;
import com.twente.game.helper.Color;
import com.twente.game.helper.Coordinate;
import com.twente.game.helper.SingleMove;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BoardTest {

    private Board board;
    private SingleMove singleMove;
    private Coordinate coordinate;

    List <String> players = new ArrayList <>();

    @BeforeEach
    void setUp() {
        initializePlayers();
        singleMove = new SingleMove(players);
        coordinate = new Coordinate(players);
        board = new Board(players);
    }

    private void initializePlayers() {
        players.add("player1");
        players.add("player2");
    }

    @Test
    void testSingleMovePlayer1() {

        boolean isMove1 = board.applySingleMove("player1", 2, 3, 2, Color.YELLOW);
        boolean isMove2 = board.applySingleMove("player1", 2, 3, 2, Color.YELLOW);

        assertTrue(isMove1);
        assertFalse(isMove2);

    }

    @Test
    void testMultipleMovePlayer1() {

        boolean isMove1 = board.applySingleMove("player1", 2, 3, 2, Color.YELLOW);
        boolean isMove2 = board.applySingleMove("player1", 2, 1, 2, Color.YELLOW);
        boolean isMove3 = board.applySingleMove("player1", 2, 2, 2, Color.YELLOW);

        assertTrue(isMove1);
        assertTrue(isMove2);
        assertTrue(isMove3);

    }

    @Test
    void testSingleMovePlayer1AndPlayer2() {

        boolean isMove1 = board.applySingleMove("player1", 2, 3, 2, Color.YELLOW);
        boolean isMove2 = board.applySingleMove("player2", 2, 3, 3, Color.GREEN);

        assertTrue(isMove1);
        assertTrue(isMove2);

    }

}
