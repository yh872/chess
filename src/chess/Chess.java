package chess;

import java.util.ArrayList;


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
	/**
	 * Plays the next move for whichever player has the turn.
	 * 
	 * @param move String for next move, e.g. "a2 a3"
	 * 
	 * @return A ReturnPlay instance that contains the result of the move.
	 *         See the section "The Chess class" in the assignment description for details of
	 *         the contents of the returned ReturnPlay instance.
	 */
	public static ReturnPlay play(String move) {

		/* FILL IN THIS METHOD */
		
		/* FOLLOWING LINE IS A PLACEHOLDER TO MAKE COMPILER HAPPY */
		/* WHEN YOU FILL IN THIS METHOD, YOU NEED TO RETURN A ReturnPlay OBJECT */
		return null;
	}
	
	
	/**
	 * This method should reset the game, and start from scratch.
	 */
	public static void start() {
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

}
