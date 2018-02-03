package com.twente.game.test;

import com.twente.game.core.Board;
import com.twente.game.helper.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
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
    private Player player2;

    @BeforeEach
    void setUp() {
        initializePlayers();
        singleMove = new SingleMove(players);
        coordinate = new Coordinate();
        winner = new Winner(players);
        board = new Board(players);
    }

    private void initializePlayers() {
        List <Color> colors = new ArrayList <>();
        colors.add(Color.YELLOW);
        colors.add(Color.GREEN);

        player = new Player("player1", colors, new Ring());

        colors = new ArrayList <>();
        colors.add(Color.BLUE);

        player2 = new Player("player2", colors, new Ring());

        players.add(player);
        players.add(player2);

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

        boolean isMove1 = board.applySingleMove(player, 1, 3, 2, Color.YELLOW);
        boolean isMove2 = board.applySingleMove(player, 2, 3, 1, Color.YELLOW);

        Map <String, Integer> playerIsWinnerMap = winner.getPlayerRingsMap(player);

        assertTrue(isMove1);
        assertTrue(isMove2);
        assertEquals(Integer.valueOf(2), playerIsWinnerMap.get("2"));
        assertEquals(Integer.valueOf(2), playerIsWinnerMap.get("1"));

    }

    @Test
    void testPlayerWinnerPlayer() {
        boolean isMove1 = board.applySingleMove(player, 1, 3, 2, Color.YELLOW);
        boolean isMove2 = board.applySingleMove(player2, 1, 3, 3, Color.BLUE);
        boolean isMove3 = board.applySingleMove(player2, 1, 3, 4, Color.BLUE);

        Map <String, Integer> player1WinnerMap = board.getPlayerWinnerMap(player);
        Map <String, Integer> player2WinnerMap = board.getPlayerWinnerMap(player2);

        assertTrue(isMove1);
        assertTrue(isMove2);
        assertTrue(isMove3);

        assertEquals(new Integer(0), player1WinnerMap.get("[1][3]"));
        assertEquals(new Integer(1), player2WinnerMap.get("[1][3]"));
    }


    @Test
    void testCountPlayerWinnerMap() {
        Map <String, Integer> player1WinnerMap = new HashMap <>();
        player1WinnerMap.put("[1][1]", new Integer(1));
        player1WinnerMap.put("[1][2]", new Integer(1));

        int counter = board.winnerMapCount(player1WinnerMap);

        assertEquals(2, counter);
    }

    @Test
    void testCountPlayerLoserMap() {
        Map <String, Integer> player1WinnerMap = new HashMap <>();
        player1WinnerMap.put("[1][1]", new Integer(0));
        player1WinnerMap.put("[1][2]", new Integer(0));

        int counter = board.winnerMapCount(player1WinnerMap);

        assertEquals(0, counter);
    }

}
