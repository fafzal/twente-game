package com.twente.game;

import java.util.Map;

public interface BasicServer {

	//                 Error codes                 
	public static final int GENERAL = 0;
	public static final int INVALID_MOVE = 1;
	public static final int NOT_YOUR_TURN = 2;
	public static final int NAME_IN_USE = 3;
	public static final int INVALID_COMMAND = 4;
	
	//              Extension names                
	// Every extension has a seperate NAME constant that corresponds to their
	// extension name.
	
	//              Command keywords               
	//				Server keywords
	public static final String ERROR = "error";
	public static final String DO_MOVE = "do_move";
	public static final String VALID = "valid";
	public static final String DONE_MOVE = "done_move";
	public static final String PLAYER_LEFT = "player_left";
	public static final String RESULTS = "results";
	//				Client keywords
	public static final String MOVE = "move";
	//				shared keywords (both client and server)
	public static final String HELLO = "hello";
	public static final String START = "start";
	
	//              Commands               
	// sending Commands (outgoing)
	public void sendHello(int numberOfPlayers, String extensions);
	public void sendStart(String names);
	public void sendError(int errorcode, String optionalMessage);
	public void sendDoMove(String name);
	public void sendDoneMove(String name, int x, int y, int size, int colour);
	public void sendPlayerLeft(String name);
	public void sendResults(Map<String, Integer> playerPointsMap, 
						Map<String, Integer> playerRingsMap, 
						Map<String, Boolean> playerIsWinnerMap);
	// receiving Commands (incoming)
	public void receiveHello(String helloCommand);
	public void receiveStart(String startCommand);
	public void receiveMove(String moveCommand);
	
}
