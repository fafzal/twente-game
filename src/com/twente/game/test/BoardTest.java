package com.twente.game.test;

import com.twente.game.core.Board;
import com.twente.game.helper.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {

    private Board board;
    private SingleMove singleMove;
    private Coordinate coordinate;
    private Winner winner;
    private List <Player> players = new ArrayList <>();
    private Player player;

    @BeforeEach
    void setUp() {
        initializePlayers();
        singleMove = new SingleMove(players);
        coordinate = new Coordinate();
        board = new Board(players);
    }

    private void initializePlayers() {
        List <Color> colors = new ArrayList <>();
        colors.add(Color.YELLOW);
        colors.add(Color.GREEN);
        player = new Player("player1", colors, new Ring());
        players.add(player);

    }

    @Test
    void testSingleMovePlayer1() {

        boolean isMove1 = board.applySingleMove(player, 2, 3, 2, Color.YELLOW);
        boolean isMove2 = board.applySingleMove(player, 2, 3, 2, Color.YELLOW);

        assertTrue(isMove1);
        assertFalse(isMove2);

    }

    @Test
    void testMultipleMovePlayer1() {

        boolean isMove1 = board.applySingleMove(player, 2, 3, 2, Color.YELLOW);
        boolean isMove2 = board.applySingleMove(player, 2, 1, 2, Color.YELLOW);
        boolean isMove3 = board.applySingleMove(player, 2, 2, 2, Color.YELLOW);

        assertTrue(isMove1);
        assertTrue(isMove2);
        assertTrue(isMove3);

    }

    @Test
    void testSingleMovePlayer1AndPlayer2() {

        boolean isMove1 = board.applySingleMove(player, 2, 3, 2, Color.YELLOW);
        boolean isMove2 = board.applySingleMove(player, 2, 3, 3, Color.GREEN);

        assertTrue(isMove1);
        assertTrue(isMove2);

    }

    @Test
    void testSingleMovePlayer1WhenPlayerCrossALimit() {

        boolean isMove1 = board.applySingleMove(player, 1, 3, 2, Color.YELLOW);
        boolean isMove2 = board.applySingleMove(player, 2, 3, 2, Color.YELLOW);
        boolean isMove3 = board.applySingleMove(player, 3, 3, 2, Color.YELLOW);
        boolean isMove4 = board.applySingleMove(player, 4, 3, 2, Color.YELLOW);

        assertTrue(isMove1);
        assertTrue(isMove2);
        assertTrue(isMove3);
        assertFalse(isMove4);

    }

    @Test
    void testPlayer2RingWinner() {

        winner = new Winner(players);
        boolean isMove1 = board.applySingleMove(player, 1, 3, 2, Color.YELLOW);
        boolean isMove2 = board.applySingleMove(player, 2, 3, 1, Color.YELLOW);

        Map <String, Integer> playerIsWinnerMap = winner.getPlayerRingsMap(player);

        assertTrue(isMove1);
        assertTrue(isMove2);
        assertEquals(Integer.valueOf(2), playerIsWinnerMap.get("2"));
        assertEquals(Integer.valueOf(2), playerIsWinnerMap.get("1"));

    }

}
