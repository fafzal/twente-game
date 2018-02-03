package com.twente.game.core;

import com.twente.game.helper.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {

    SingleMove singleMove;
    Coordinate coordinate;
    Winner winner;
    boolean boardArrayEmpty = false;

    private Map <String, List <Integer>> boardArray[][] = new HashMap[5][5];

    public Board(List <Player> players) {
        initialize();
        singleMove = new SingleMove(players);
        coordinate = new Coordinate();
        winner = new Winner(players);
    }

    public boolean applySingleMove(Player player, int x, int y, int size, Color color) {

        if (boardArrayEmpty) {
            boardArrayEmpty = false;
            boolean isValidCoordinate = coordinate.isValidMoveForBase(x, y, size, boardArray);
            if (isValidCoordinate) {
                Ring ring = player.getRing();
                if (coordinate.isValidCoordinates(player, x, y, boardArray) && ring.getRoundLeft(size) != 0) {
                    ring.subtract(size);
                    coordinate.setAllPossibleCoordinates(player, x, y, color);
                    return singleMove.move(player, size, boardArray[x][y]);
                }
            }
        } else {
            Ring ring = player.getRing();
            if (coordinate.isValidCoordinates(player, x, y, boardArray) && ring.getRoundLeft(size) != 0) {
                ring.subtract(size);
                coordinate.setAllPossibleCoordinates(player, x, y, color);
                return singleMove.move(player, size, boardArray[x][y]);
            }
        }

        return false;
    }

    public Map <String, Integer> getPlayerPointsMap(Player player) {
        return winner.getPlayerPointsMap(player, boardArray);
    }

    public Map <String, Integer> getPlayerRingsMap(Player player) {
        return winner.getPlayerRingsMap(player);
    }

    private void initialize() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                boardArray[i][j] = new HashMap <>();
            }
        }
        boardArrayEmpty = true;
    }


    public boolean isMoveLeft(Player player) {

        Map <String, Integer> playerWinnerMap = new HashMap <>();

        for (Color color : player.getColors()) {
            List <String> possibleMoves = player.getPossibleMoves(color);
            if (possibleMoves.isEmpty()) {
                return true;
            }
            for (String str : possibleMoves) {
                String indexArr[] = str.split(",");
                int x = Integer.valueOf(indexArr[0]);
                int y = Integer.valueOf(indexArr[1]);

                Map <String, List <Integer>> userMap = boardArray[x][y];
                List <Integer> integers = userMap.get(player.getName());

                int b = player.getRing().getBig();
                int s = player.getRing().getSmall();
                int h = player.getRing().getHuge();
                int m = player.getRing().getMedium();

                if (b == 0 && s == 0 && h == 0 && m == 0) {
                    return false;
                }

                if (integers == null) {
                    return true;
                }

                if (b > 0 && !integers.contains(b)) {
                    return true;
                } else if (s > 0 && !integers.contains(s)) {
                    return true;
                } else if (h > 0 && !integers.contains(h)) {
                    return true;
                } else if (m > 0 && !integers.contains(m)) {
                    return true;
                }
            }

        }

        return false;
    }

    public Map <String, Integer> getPlayerWinnerMap(Player player) {

        Map <String, Integer> playerWinnerMap = new HashMap <>();

        for (Color color : player.getColors()) {
            List <String> possibleMoves = player.getPossibleMoves(color);
            for (String str : possibleMoves) {
                String indexArr[] = str.split(",");
                int x = Integer.valueOf(indexArr[0]);
                int y = Integer.valueOf(indexArr[1]);

                winner.setPlayerWinnerMap(player, boardArray[x][y], playerWinnerMap, x, y);

            }

        }

        return playerWinnerMap;
    }

    public int winnerMapCount(Map <String, Integer> player1WinnerMap) {
        int counter = 0;

        for (String entry : player1WinnerMap.keySet()) {
            Integer integer = player1WinnerMap.get(entry);
            if (integer.intValue() == 1) {
                counter++;
            }
        }
        return counter;
    }
}
