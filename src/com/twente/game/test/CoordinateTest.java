package com.twente.game.test;

import com.twente.game.helper.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CoordinateTest {

    private Coordinate coordinate;
    private Map <String, List <Integer>> boardIndex[][] = new HashMap[5][5];
    private Map <String, List <Integer>> userMap = new HashMap <>();
    private SingleMove s;
    private Player player;
    private Player player2;


    @BeforeEach
    void setUp() {
        coordinate = new Coordinate();
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
                boardIndex[i][j] = new HashMap <>();
            }
        }
    }

    @Test
    void scanBoard2Player() {
        List <Player> players = new ArrayList <>();
        players.add(player);
        players.add(player2);

        coordinate = new Coordinate();
        s = new SingleMove(players);

        boolean actual = coordinate.isValidCoordinates(player, 2, 3, boardIndex);
        boolean actualMove1 = s.move(player, 2, boardIndex[2][3]);
        boolean actual2 = coordinate.isValidCoordinates(player, 2, 3, boardIndex);
        boolean actualMove2 =  s.move(player, 2, boardIndex[2][3]);
        assertTrue(actual);
        assertTrue(actualMove1);
        assertTrue(actual2);
        assertFalse(actualMove2);
    }

    @Test
    void scanBoardStartBase() {

        List <String> players = new ArrayList <>();
        players.add("player1");
        players.add("player2");

        coordinate = new Coordinate();

        boolean result = coordinate.isValidMoveForBase("player", 7, 6, 2, boardIndex);
        assertFalse(result);


    }

    @Test
    void scanBoard2Player_() {
        List <Player> players = new ArrayList <>();
        players.add(player);
        players.add(player2);

        coordinate = new Coordinate();
        s = new SingleMove(players);
        boolean actual1 = s.move(player, 2, boardIndex[2][3]);
        boolean actual = coordinate.isValidCoordinates(player, -2, 3, boardIndex);
        assertFalse(actual);
        assertTrue(actual1);

    }

    @Test
    void testAllPossibleCoordinates() {

        int x = 1;
        int y = 1;

        coordinate.setAllPossibleCoordinates(player, x, y, Color.YELLOW);

        assertTrue(player.getPossibleMoves(Color.YELLOW).contains("1,1"));
        assertTrue(player.getPossibleMoves(Color.YELLOW).contains("2,1"));
        assertTrue(player.getPossibleMoves(Color.YELLOW).contains("3,1"));

        assertTrue(player.getPossibleMoves(Color.YELLOW).contains("1,1"));
        assertTrue(player.getPossibleMoves(Color.YELLOW).contains("1,2"));
        assertTrue(player.getPossibleMoves(Color.YELLOW).contains("1,3"));

        assertFalse(player.getPossibleMoves(Color.YELLOW).contains("2,2"));
    }
}







