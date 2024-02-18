package chess;

import java.util.ArrayList;

import chess.ReturnPlay.Message;



class ReturnPiece {
	static enum PieceType {WP, WR, WN, WB, WQ, WK, 
		            BP, BR, BN, BB, BK, BQ};
	static enum PieceFile {a, b, c, d, e, f, g, h};
	
	PieceType pieceType;
	PieceFile pieceFile;
	int pieceRank;  // 1..8
	public String toString() {
		return ""+pieceFile+pieceRank+":"+pieceType;
	}
	public boolean equals(Object other) {
		if (other == null || !(other instanceof ReturnPiece)) {
			return false;
		}
		ReturnPiece otherPiece = (ReturnPiece)other;
		return pieceType == otherPiece.pieceType &&
				pieceFile == otherPiece.pieceFile &&
				pieceRank == otherPiece.pieceRank;
	}
}

class ReturnPlay {
	enum Message {ILLEGAL_MOVE, DRAW, 
				  RESIGN_BLACK_WINS, RESIGN_WHITE_WINS, 
				  CHECK, CHECKMATE_BLACK_WINS,	CHECKMATE_WHITE_WINS, 
				  STALEMATE};
	
	ArrayList<ReturnPiece> piecesOnBoard;
	Message message;
}

public class Chess {
	enum Player { white, black }
	public static ReturnPiece[][] board = new ReturnPiece[8][8];
	public static ReturnPiece[][] previous_board = new ReturnPiece[8][8];
	/**
	 * Plays the next move for whichever player has the turn.
	 * 
	 * @param move String for next move, e.g. "a2 a3"
	 * 
	 * @return A ReturnPlay instance that contains the result of the move.
	 *         See the section "The Chess class" in the assignment description for details of
	 *         the contents of the returned ReturnPlay instance.
	 */
	public static boolean white = true;
	public static ReturnPlay play(String move) {
		ReturnPiece[][] old_board = new ReturnPiece[8][8];
		for (int i = 0; i <8; i++){
			for (int j = 0; j <8; j++){
				old_board[i][j] = new ReturnPiece();
				old_board[i][j].pieceType = board[i][j].pieceType;
				old_board[i][j].pieceFile = board[i][j].pieceFile;
				old_board[i][j].pieceRank = board[i][j].pieceRank;
			}
		}
		move = move.trim();
		boolean drawRequested = move.endsWith("draw?"); 
		if (move.equals("resign")){
			ReturnPlay temp = generateReturnPlay();
			if (white){
			temp.message = ReturnPlay.Message.RESIGN_BLACK_WINS;
			}
			else{
				temp.message = ReturnPlay.Message.RESIGN_WHITE_WINS;
			}
			return temp;
		}
		/* FILL IN THIS METHOD */
		if (white){ //checks that the moving move piece is white
			if (!Helper.isWhitePiece(Helper.getRank(move), Helper.getFile(move))){
				ReturnPlay temp = generateReturnPlay();
			temp.message = ReturnPlay.Message.ILLEGAL_MOVE;
			 return temp;
			}
		}
		else{ //checks that the moving piece is black
			if (!Helper.isBlackPiece(Helper.getRank(move), Helper.getFile(move))){
				ReturnPlay temp = generateReturnPlay();
			temp.message = ReturnPlay.Message.ILLEGAL_MOVE;
			return temp;
		}
	}
	if (Helper.getSquare(Helper.getRank(move), Helper.getFile(move)).pieceType == null){ //checks if there is a piece on the initial square
		ReturnPlay temp = generateReturnPlay();
			temp.message = ReturnPlay.Message.ILLEGAL_MOVE;
			return temp;
	}
		if (!Legal.isLegal(move)){	//checks if the move is legal according to the rules of chess
			ReturnPlay temp = generateReturnPlay();
			temp.message = ReturnPlay.Message.ILLEGAL_MOVE;
			return temp;
		}
		
		movePiece(move); //completes move
		ReturnPlay temp = generateReturnPlay();
		if (white && Check.isCheckWhite(temp.piecesOnBoard)){
			ReturnPlay t1 = new ReturnPlay();
			t1.message = Message.ILLEGAL_MOVE;
			
			t1.piecesOnBoard = new ArrayList<>();
			for (int i = 0; i < 8; i++){
				for (int j = 0; j <8; j++){
					board[i][j].pieceType = old_board[i][j].pieceType;
					if (old_board[i][j].pieceType != null){
				t1.piecesOnBoard.add(old_board[i][j]);
					}
				}
			}
			return t1;

		}
		if (!white && Check.isCheckBlack(temp.piecesOnBoard)){
			ReturnPlay t2 = new ReturnPlay();
			t2.piecesOnBoard = new ArrayList<>();
			t2.message = Message.ILLEGAL_MOVE;
			for (int i = 0; i < 8; i++){
				for (int j = 0; j <8; j++){
					board[i][j].pieceType = old_board[i][j].pieceType;
					if (old_board[i][j].pieceType != null){
				t2.piecesOnBoard.add(old_board[i][j]);
					}
				}
			}
			return t2;
			
	}
	if (white && Check.isCheckBlack(temp.piecesOnBoard)){
		temp.message = Message.CHECK;
	}
	if (!white && Check.isCheckWhite(temp.piecesOnBoard)){
		temp.message = Message.CHECK;
	}
		white = !white;
		if (drawRequested) temp.message = ReturnPlay.Message.DRAW;

		/* FOLLOWING LINE IS A PLACEHOLDER TO MAKE COMPILER HAPPY */
		/* WHEN YOU FILL IN THIS METHOD, YOU NEED TO RETURN A ReturnPlay OBJECT */


		return temp;
	}
	public static ReturnPlay generateReturnPlay(){ //copies the board into the ReturnPlay object that will be returned by play method
		ReturnPlay temp = new ReturnPlay();
		temp.message = null;
		temp.piecesOnBoard = new ArrayList<>();
		for (int i = 0; i < 8; i++){
			for (int j = 0; j <8; j++){
				if (board[i][j].pieceType != null){
			temp.piecesOnBoard.add(board[i][j]);
				}
			}
		}
		return temp;
	}
	
	/**
	 * This method should reset the game, and start from scratch.
	 */
	public static void start() { 	// initializes the chess board
		white = true;
		for (int i = 0; i < 8; i++){
			for (int j = 0; j < 8; j++){
				board[i][j] = new ReturnPiece();
			}
			
		}
		for (int i = 0; i <8; i++){
			for (int j = 0; j < 8; j++){
				board[i][j].pieceRank = i +1;
				switch (j) {
					case 0: board[i][j].pieceFile = ReturnPiece.PieceFile.a; break;
					case 1: board[i][j].pieceFile = ReturnPiece.PieceFile.b; break;
					case 2: board[i][j].pieceFile = ReturnPiece.PieceFile.c; break;
					case 3: board[i][j].pieceFile = ReturnPiece.PieceFile.d; break;
					case 4: board[i][j].pieceFile = ReturnPiece.PieceFile.e; break;
					case 5: board[i][j].pieceFile = ReturnPiece.PieceFile.f; break;
					case 6: board[i][j].pieceFile = ReturnPiece.PieceFile.g; break;
					case 7: board[i][j].pieceFile = ReturnPiece.PieceFile.h; break;
					default:
			}
		
		}
	}
	board[0][0].pieceType = ReturnPiece.PieceType.WR; board[0][1].pieceType = ReturnPiece.PieceType.WN;
	board[0][2].pieceType = ReturnPiece.PieceType.WB; board[0][3].pieceType = ReturnPiece.PieceType.WQ;
	board[0][4].pieceType = ReturnPiece.PieceType.WK; board[0][5].pieceType = ReturnPiece.PieceType.WB; 
	board[0][6].pieceType = ReturnPiece.PieceType.WN; board[0][7].pieceType = ReturnPiece.PieceType.WR;
	
	for (int i = 0; i <8; i++){
		board[1][i].pieceType = ReturnPiece.PieceType.WP;
	}
	
	board[7][0].pieceType = ReturnPiece.PieceType.BR; board[7][1].pieceType = ReturnPiece.PieceType.BN;
	board[7][2].pieceType = ReturnPiece.PieceType.BB; board[7][3].pieceType = ReturnPiece.PieceType.BQ;
	board[7][4].pieceType = ReturnPiece.PieceType.BK; board[7][5].pieceType = ReturnPiece.PieceType.BB; 
	board[7][6].pieceType = ReturnPiece.PieceType.BN; board[7][7].pieceType = ReturnPiece.PieceType.BR;
	
	for (int i = 0; i <8; i++){
		board[6][i].pieceType = ReturnPiece.PieceType.BP;
	}
		/* FILL IN THIS METHOD */
}



public static void movePiece( String move ){ //moves a piece from one square to another
	move = move.trim();
	ReturnPiece.PieceType piece = Helper.curPieceType(move);
	int initial_file = move.charAt(0) - 'a';
	int initial_rank = Integer.parseInt(move.substring(1,2))-1;

	int final_file = move.charAt(3) - 'a';
	int final_rank = Integer.parseInt(move.substring(4, 5)) -1;


	board[initial_rank][initial_file].pieceType = null;
	board[final_rank][final_file].pieceType = piece;

}

public static void undomove( String move ){ //moves a piece from one square to another
	move = move.trim();
	int initial_file = move.charAt(0) - 'a';
	int initial_rank = Integer.parseInt(move.substring(1,2))-1;

	int final_file = move.charAt(3) - 'a';
	int final_rank = Integer.parseInt(move.substring(4, 5)) -1;
	ReturnPiece p = Helper.getSquare(final_rank, final_file);

	board[initial_rank][initial_file].pieceType = p.pieceType;
	board[final_rank][final_file].pieceType = null;
}
public static void setPreviousBoard(ReturnPiece[][] b){

	for (int i = 0; i <8; i++){
		for (int j = 0; j <8; j++){
			previous_board[i][j] = b[i][j];
		}
	}
}
 
}