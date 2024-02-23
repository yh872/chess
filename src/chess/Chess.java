//Youssef Hanna and Victoria Permakoff
package chess;

import java.util.ArrayList;

import chess.ReturnPiece.PieceFile;
import chess.ReturnPiece.PieceType;
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
	public static boolean White_Kinghasmoved = false;
	public static boolean White_KingRookhasmoved = false;
	public static boolean White_QueenRookhasmoved = false;
	public static boolean Black_Kinghasmoved = false;
	public static boolean Black_KingRookhasmoved = false;
	public static boolean Black_QueenRookhasmoved = false;
	public static ReturnPiece EnPessantPawn = null;
	public static int EnPessantmoveTracker = 0;

	public static ReturnPlay play(String move) {
		if (EnPessantmoveTracker >1){
			EnPessantPawn = null;
			EnPessantmoveTracker = 0;
		}
		ReturnPiece[][] old_board = setPreviousBoard();
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
	//castling implemented here
	
	ReturnPlay c1 = generateReturnPlay();
	if (move.equals("e1 g1") && !White_Kinghasmoved && !White_KingRookhasmoved && !Check.isCheckWhite(c1.piecesOnBoard)){
			if (Helper.isEmptySquare(0, 5) && Helper.isEmptySquare(0, 6)){
				movePiece("e1 f1");
				ReturnPlay t1 = generateReturnPlay();
				if (Check.isCheckWhite(t1.piecesOnBoard)){
					undomove(old_board);
					t1 = generateReturnPlay();
					t1.message = Message.ILLEGAL_MOVE;
					return t1;

				}
				board[0][4].pieceType = null;
				board[0][7].pieceType = null;
				board[0][6].pieceType = PieceType.WK;
				board[0][5].pieceType = PieceType.WR;
				ReturnPlay temp = generateReturnPlay();
				if (Check.isCheckWhite(temp.piecesOnBoard)){
					board[0][6].pieceType = null;
				board[0][5].pieceType = null;
				board[0][4].pieceType = PieceType.WK;
				board[0][7].pieceType = PieceType.WR;
					temp = generateReturnPlay();
					temp.message = Message.ILLEGAL_MOVE;
					return temp;

				}
				
				if (Check.isCheckBlack(temp.piecesOnBoard)){
					old_board = setPreviousBoard();
		for (ReturnPiece piece: temp.piecesOnBoard){
			if (Helper.isBlack(piece)){
			for (int i = 1; i <=8; i++){
				for (PieceFile file : PieceFile.values()){
					if (Legal.isLegal(Helper.generateMoveString(piece.pieceRank, piece.pieceFile, i, file))){
						movePiece(Helper.generateMoveString(piece.pieceRank, piece.pieceFile, i, file));
						temp = generateReturnPlay();
						if (!Check.isCheckBlack(temp.piecesOnBoard)){
							undomove(old_board);
							ReturnPlay z1 = generateReturnPlay();
							if (!drawRequested){
								z1.message = Message.CHECK;
								}
								else{
									z1.message = Message.DRAW;
								}
							white = !white;
							return z1;

						}
						undomove(old_board);
					}
				}
			}
		}
		}
		temp = generateReturnPlay();
		temp.message = Message.CHECKMATE_WHITE_WINS;
				}
				if (drawRequested){
					temp.message = Message.DRAW;
				}
				White_Kinghasmoved = true;
       			 White_KingRookhasmoved = true;
				white = !white;
				return temp;

			}
			else{
				ReturnPlay p1 = generateReturnPlay();
				p1.message = Message.ILLEGAL_MOVE;
				return p1;
			}
	}
	if (move.equals("e1 c1") && !White_Kinghasmoved && !White_QueenRookhasmoved && !Check.isCheckWhite(c1.piecesOnBoard)){
		if (Helper.isEmptySquare(0, 1) && Helper.isEmptySquare(0, 2) && Helper.isEmptySquare(0, 3)){
			movePiece("e1 d1");
			ReturnPlay t1 = generateReturnPlay();
				if (Check.isCheckWhite(t1.piecesOnBoard)){
					undomove(old_board);
					t1 = generateReturnPlay();
					t1.message = Message.ILLEGAL_MOVE;
					return t1;

				}
			board[0][3].pieceType = null;
			board[0][2].pieceType = PieceType.WK;
			 t1 = generateReturnPlay();
			if (Check.isCheckWhite(t1.piecesOnBoard)){
				board[0][2].pieceType = null;
				board[0][4].pieceType = PieceType.WK;
				t1 = generateReturnPlay();

				t1.message = Message.ILLEGAL_MOVE;
				return t1;

			}
			board[0][3].pieceType = PieceType.WR;
			board[0][0].pieceType = null;
			White_Kinghasmoved = true;
			White_QueenRookhasmoved = true;
			t1 = generateReturnPlay();
			if (Check.isCheckBlack(t1.piecesOnBoard)){
				old_board = setPreviousBoard();
		for (ReturnPiece piece: t1.piecesOnBoard){
			if (Helper.isBlack(piece)){
			for (int i = 1; i <=8; i++){
				for (PieceFile file : PieceFile.values()){
					if (Legal.isLegal(Helper.generateMoveString(piece.pieceRank, piece.pieceFile, i, file))){
						movePiece(Helper.generateMoveString(piece.pieceRank, piece.pieceFile, i, file));
						t1 = generateReturnPlay();
						if (!Check.isCheckBlack(t1.piecesOnBoard)){
							undomove(old_board);
							ReturnPlay d1 = generateReturnPlay();
							if (!drawRequested){
								d1.message = Message.CHECK;
								}
								else{
									d1.message = Message.DRAW;
								}
							white = !white;
							return d1;

						}
						undomove(old_board);
					}
				}
			}
		}
		}

		t1 = generateReturnPlay();
		t1.message = Message.CHECKMATE_WHITE_WINS;
		
			}
			if (drawRequested){
				t1.message = Message.DRAW;
			}
			White_Kinghasmoved = true;
			White_QueenRookhasmoved = true;
			white = !white;
			return t1;


		}
		else{
			ReturnPlay p1 = generateReturnPlay();
				p1.message = Message.ILLEGAL_MOVE;
				return p1;

		}
	}
	if (move.equals("e8 g8") && !Black_Kinghasmoved && !Black_KingRookhasmoved && !Check.isCheckBlack(c1.piecesOnBoard)){
		if (Helper.isEmptySquare(7, 5) && Helper.isEmptySquare(7, 6)){
			movePiece("e8 f8");
			ReturnPlay t1 = generateReturnPlay();
			if (Check.isCheckBlack(t1.piecesOnBoard)){
				undomove(old_board);
				t1 = generateReturnPlay();
				t1.message = Message.ILLEGAL_MOVE;
				return t1;

			}
			board[7][4].pieceType = null;
			board[7][7].pieceType = null;
			board[7][6].pieceType = PieceType.BK;
			board[7][5].pieceType = PieceType.BR;
			ReturnPlay temp = generateReturnPlay();
			if (Check.isCheckBlack(temp.piecesOnBoard)){
				board[7][6].pieceType = null;
			board[7][5].pieceType = null;
			board[7][4].pieceType = PieceType.BK;
			board[7][7].pieceType = PieceType.BR;
				temp = generateReturnPlay();
				temp.message = Message.ILLEGAL_MOVE;
				return temp;

			}
			ReturnPlay a = generateReturnPlay();
			if (Check.isCheckWhite(a.piecesOnBoard)){
				old_board = setPreviousBoard();
		for (ReturnPiece piece: a.piecesOnBoard){
			if (Helper.isWhite(piece)){
				for (int i = 1; i <=8; i++){
					for (PieceFile file : PieceFile.values()){
						if (Legal.isLegal(Helper.generateMoveString(piece.pieceRank, piece.pieceFile, i, file))){
							movePiece(Helper.generateMoveString(piece.pieceRank, piece.pieceFile, i, file));
							a = generateReturnPlay();
							if (!Check.isCheckWhite(a.piecesOnBoard)){
								undomove(old_board);
								ReturnPlay m = generateReturnPlay();
								if (!drawRequested){
								m.message = Message.CHECK;
								}
								else{
									m.message = Message.DRAW;
								}
								white = !white;
								return m;
	
							}
							undomove(old_board);
						}
					}
				}
			}
			}
	
			 a = generateReturnPlay();
			a.message = Message.CHECKMATE_BLACK_WINS;
			}
			if (drawRequested){
				a.message = Message.DRAW;
			}
			Black_Kinghasmoved = true;
				Black_KingRookhasmoved = true;
			white = !white;
			return a;

		}
		else{
			ReturnPlay p1 = generateReturnPlay();
			p1.message = Message.ILLEGAL_MOVE;
			return p1;
		}
}
if (move.equals("e8 c8") && !Black_Kinghasmoved && !Black_QueenRookhasmoved && !Check.isCheckBlack(c1.piecesOnBoard)){
	if (Helper.isEmptySquare(7, 1) && Helper.isEmptySquare(7, 2) && Helper.isEmptySquare(7, 3)){
		movePiece("e8 d8");
		ReturnPlay t1 = generateReturnPlay();
			if (Check.isCheckWhite(t1.piecesOnBoard)){
				undomove(old_board);
				t1 = generateReturnPlay();
				t1.message = Message.ILLEGAL_MOVE;
				return t1;

			}
		board[7][3].pieceType = null;
		board[7][2].pieceType = PieceType.BK;
		 t1 = generateReturnPlay();
		if (Check.isCheckBlack(t1.piecesOnBoard)){
			board[7][2].pieceType = null;
			board[7][4].pieceType = PieceType.BK;
			t1 = generateReturnPlay();

			t1.message = Message.ILLEGAL_MOVE;
	
			return t1;

		}
		board[7][3].pieceType = PieceType.BR;
		board[7][0].pieceType = null;
		Black_Kinghasmoved = true;
		Black_QueenRookhasmoved = true;
		ReturnPlay a = generateReturnPlay();
			if (Check.isCheckWhite(a.piecesOnBoard)){
				old_board = setPreviousBoard();
		for (ReturnPiece piece: a.piecesOnBoard){
			if (Helper.isWhite(piece)){
				for (int i = 1; i <=8; i++){
					for (PieceFile file : PieceFile.values()){
						if (Legal.isLegal(Helper.generateMoveString(piece.pieceRank, piece.pieceFile, i, file))){
							movePiece(Helper.generateMoveString(piece.pieceRank, piece.pieceFile, i, file));
							a = generateReturnPlay();
							if (!Check.isCheckWhite(a.piecesOnBoard)){
								undomove(old_board);
								ReturnPlay m = generateReturnPlay();
								if (!drawRequested){
								m.message = Message.CHECK;
								}
								else{
									m.message = Message.DRAW;
								}
								white = !white;
								return m;
	
							}
							undomove(old_board);
						}
					}
				}
			}
			}
	
			 a = generateReturnPlay();
			a.message = Message.CHECKMATE_BLACK_WINS;
			}
			if (drawRequested){
				a.message = Message.DRAW;
			}
			Black_Kinghasmoved = true;
				Black_KingRookhasmoved = true;
			white = !white;
			return a;

	}
	else{
		ReturnPlay p1 = generateReturnPlay();
			p1.message = Message.ILLEGAL_MOVE;
			return p1;

	}
}

		if (!Legal.isLegal(move)){
			ReturnPlay temp = generateReturnPlay();
			temp.message = ReturnPlay.Message.ILLEGAL_MOVE;
			return temp;
		}
		ReturnPiece.PieceType cur = Helper.curPieceType(move);
		
		if (Promotion.PromotionWhite(move)){
			if (move.length() == 5){
				board[Helper.getRank(move)][Helper.getFile(move)].pieceType = PieceType.WQ;
			}
			else{
				if (move.charAt(6) == 'N'){
					board[Helper.getRank(move)][Helper.getFile(move)].pieceType = PieceType.WN;
				}
				if (move.charAt(6) == 'B'){
					board[Helper.getRank(move)][Helper.getFile(move)].pieceType = PieceType.WB;
				}
				if (move.charAt(6) == 'R'){
					board[Helper.getRank(move)][Helper.getFile(move)].pieceType = PieceType.WR;
				}
				if (move.charAt(6) == 'Q'){
					board[Helper.getRank(move)][Helper.getFile(move)].pieceType = PieceType.WQ;
				}

			}
		}
		if (Promotion.PromotionBlack(move)){
			if (move.length() == 5){
				board[Helper.getRank(move)][Helper.getFile(move)].pieceType = PieceType.BQ;
			}
			else{
				if (move.charAt(6) == 'N'){
					board[Helper.getRank(move)][Helper.getFile(move)].pieceType = PieceType.BN;
				}
				if (move.charAt(6) == 'B'){
					board[Helper.getRank(move)][Helper.getFile(move)].pieceType = PieceType.BB;
				}
				if (move.charAt(6) == 'R'){
					board[Helper.getRank(move)][Helper.getFile(move)].pieceType = PieceType.BR;
				}
				if (move.charAt(6) == 'Q'){
					board[Helper.getRank(move)][Helper.getFile(move)].pieceType = PieceType.BQ;
				}

			}
		}
		
		movePiece(move); //completes move
		ReturnPlay temp = generateReturnPlay();
		
		if (white && Check.isCheckWhite(temp.piecesOnBoard)){
			ReturnPlay t1 = new ReturnPlay();
			t1.piecesOnBoard = new ArrayList<>();
			for (int i = 0; i < 8; i++){
				for (int j = 0; j <8; j++){
					if (old_board[i][j].pieceType != null){
				t1.piecesOnBoard.add(old_board[i][j]);
					}
				}
			}
			undomove(old_board);
			t1.message = Message.ILLEGAL_MOVE;
			return t1;

		}
		if (!white && Check.isCheckBlack(temp.piecesOnBoard)){
			ReturnPlay t2 = new ReturnPlay();
			t2.piecesOnBoard = new ArrayList<>();
			t2.message = Message.ILLEGAL_MOVE;
			for (int i = 0; i < 8; i++){
				for (int j = 0; j <8; j++){
					if (old_board[i][j].pieceType != null){
				t2.piecesOnBoard.add(old_board[i][j]);
					}
				}
			}
			undomove(old_board);
			return t2;
			
	} 

	
	
	if (cur == PieceType.WK){
		White_Kinghasmoved = true;
	}
	if (cur == PieceType.WR && move.substring(0,2).equals("a1")){
		White_QueenRookhasmoved = true;
	}
	if (cur == PieceType.WR && move.substring(0,2).equals("h1")){
		White_KingRookhasmoved = true;
	}
	if (cur == PieceType.BK){
		Black_Kinghasmoved = true;
	}
	if (cur == PieceType.WR && move.substring(0,2).equals("a8")){
		Black_QueenRookhasmoved = true;
	}
	if (cur == PieceType.WR && move.substring(0,2).equals("h8")){
		Black_KingRookhasmoved = true;
	}
		//implements check/checkmate for white
		if (Helper.getRank(move) == 1 && Helper.getFinalRank(move) == 3 && Helper.getSquare(3, Helper.getFile(move) ).pieceType == PieceType.WP){
			int file = Helper.getFinalFile(move);
			EnPessantPawn = board[3][file];
			EnPessantmoveTracker = 0;
		}
	

		if (Helper.getRank(move) == 6 && Helper.getFinalRank(move) == 4 && Helper.getSquare(4, Helper.getFile(move) ).pieceType == PieceType.BP){
			int file = Helper.getFinalFile(move);
			EnPessantPawn = board[4][file];
			EnPessantmoveTracker = 0;
		}
		if (EnPessantPawn !=null){
		if (EnPessantPawn.pieceType != null){
			EnPessantmoveTracker++;
		}
	}
		if (EnPessantPawn != null){
			if (Helper.getFinalRank(move) == 5 && Helper.getFinalFile(move) == Helper.FileToInt(EnPessantPawn.pieceFile) 
			&& board[Helper.getFinalRank(move)][Helper.getFinalFile(move)].pieceType == PieceType.WP){
				board[4][Helper.getFinalFile(move)].pieceType = null;
				temp = generateReturnPlay();
			}
			if (Helper.getFinalRank(move) == 2 && Helper.getFinalFile(move) == Helper.FileToInt(EnPessantPawn.pieceFile) 
			&& board[Helper.getFinalRank(move)][Helper.getFinalFile(move)].pieceType == PieceType.BP){
				board[3][Helper.getFinalFile(move)].pieceType = null;
				temp = generateReturnPlay();
			}
		}
	
	if (white && Check.isCheckBlack(temp.piecesOnBoard)){
		old_board = setPreviousBoard();
		for (ReturnPiece piece: temp.piecesOnBoard){
			if (Helper.isBlack(piece)){
			for (int i = 1; i <=8; i++){
				for (PieceFile file : PieceFile.values()){
					if (Legal.isLegal(Helper.generateMoveString(piece.pieceRank, piece.pieceFile, i, file))){
						movePiece(Helper.generateMoveString(piece.pieceRank, piece.pieceFile, i, file));
						temp = generateReturnPlay();
						if (!Check.isCheckBlack(temp.piecesOnBoard)){
							undomove(old_board);
							ReturnPlay t1 = generateReturnPlay();
							if (!drawRequested){
								t1.message = Message.CHECK;
								}
								else{
									t1.message = Message.DRAW;
								}
							white = !white;
							return t1;

						}
						undomove(old_board);
					}
				}
			}
		}
		}

		temp = generateReturnPlay();
		temp.message = Message.CHECKMATE_WHITE_WINS;
		
		
	}
	if (!white && Check.isCheckWhite(temp.piecesOnBoard)){
		old_board = setPreviousBoard();
		for (ReturnPiece piece: temp.piecesOnBoard){
			if (Helper.isWhite(piece)){
				for (int i = 1; i <=8; i++){
					for (PieceFile file : PieceFile.values()){
						if (Legal.isLegal(Helper.generateMoveString(piece.pieceRank, piece.pieceFile, i, file))){
							movePiece(Helper.generateMoveString(piece.pieceRank, piece.pieceFile, i, file));
							temp = generateReturnPlay();
							if (!Check.isCheckWhite(temp.piecesOnBoard)){
								undomove(old_board);
								ReturnPlay t1 = generateReturnPlay();
								if (!drawRequested){
								t1.message = Message.CHECK;
								}
								else{
									t1.message = Message.DRAW;
								}
								white = !white;
								return t1;
	
							}
							undomove(old_board);
						}
					}
				}
			}
			}
	
			temp = generateReturnPlay();
			temp.message = Message.CHECKMATE_BLACK_WINS;
	}
	
		white = !white;
		if (drawRequested) temp.message = ReturnPlay.Message.DRAW;
		
		
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
	 White_Kinghasmoved = false;
	 White_KingRookhasmoved = false;
	 White_QueenRookhasmoved = false;
	 Black_Kinghasmoved = false;
	Black_KingRookhasmoved = false;
	 Black_QueenRookhasmoved = false;
	 EnPessantPawn = null;
	 EnPessantmoveTracker = 0;
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

public static void undomove( ReturnPiece[][] old_board ){ //moves a piece from one square to another
	for (int i = 0; i < 8; i++){
		for (int j = 0; j <8; j++){
			board[i][j].pieceType = old_board[i][j].pieceType;
		}
	}
}
public static ReturnPiece[][] setPreviousBoard(){

	ReturnPiece[][] old_board = new ReturnPiece[8][8];
		for (int i = 0; i <8; i++){
			for (int j = 0; j <8; j++){
				old_board[i][j] = new ReturnPiece();
				old_board[i][j].pieceType = board[i][j].pieceType;
				old_board[i][j].pieceFile = board[i][j].pieceFile;
				old_board[i][j].pieceRank = board[i][j].pieceRank;
			}
		}
		return old_board;
}

 
}