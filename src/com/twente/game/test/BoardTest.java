package com.twente.game.test;

import com.twente.game.core.Board;
import com.twente.game.helper.Color;
import com.twente.game.helper.SingleMove;
import com.twente.game.helper.Winner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BoardTest {

    private Board board;
    private SingleMove singleMove;
    private Winner winner;

    private Map <String, List <Integer>> boardArray[][] = new HashMap[5][5];
    private Map <String, List <Integer>> moveMap = new HashMap <>();
    List <String> players = new ArrayList <>();


    @BeforeEach
    void setUp() {
        initializePlayers();
        singleMove = new SingleMove(players);
        winner = new Winner(players);
        board = new Board(players);
    }

    private void initializePlayers() {
        players.add("player1");
        players.add("player2");
    }

    @Test
    void testSingleMove() {
        boolean isMove1 = board.applySingleMove("player1", 0, 3, 1, Color.YELLOW);
        boolean isMove2 = board.applySingleMove("player2", 0, 3, 2, Color.GREEN);

        assertTrue(isMove1);
        assertTrue(isMove2);


    }

}
