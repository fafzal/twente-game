package com.twente.game.socket;

public interface BasicClient {

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
	// shared keywords (both client and server)
	public static final String HELLO = "hello";
	public static final String START = "start";
	// sent by server only
	public static final String ERROR = "error";
	public static final String DO_MOVE = "do_move";
	public static final String VALID = "valid";
	public static final String DONE_MOVE = "done_move";
	public static final String PLAYER_LEFT = "player_left";
	public static final String RESULTS = "results";
	// sent by client only
	public static final String MOVE = "move";

	
	//               Commands                   
	// sending Commands (outgoing)
	public void sendHello(String name, String extensions);
	public void sendStart(int numberOfPlayers);
	public void sendMove(int x, int y, int size, int colour);

	// receiving Commands (incoming)
	public void receiveHello(String helloCommand);
	public void receiveStart(String startCommand);
	public void receiveError(String errorCommand);
	public void receiveDoMove(String doMoveCommand);
	public void receiveDoneMove(String doneMoveCommand);
	public void receivePlayerLeft(String playerLeftCommand);
	public void receiveResults(String resultsCommand);
	
	
	
	
}



