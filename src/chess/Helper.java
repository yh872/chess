package chess;

import java.util.ArrayList;

import chess.ReturnPiece.PieceFile;
import chess.ReturnPiece.PieceType;

public class Helper {
    public static boolean isEmptySquare(int rank, int file){ //returns if a square is empty or not
        return Chess.board[rank][file].pieceType == null;
    }
    public static ReturnPiece getSquare(int rank, int file){ //returns the ReturnPiece object in a specified file and rank
        return Chess.board[rank][file];
    }
    
    public static boolean isBlackPiece(int rank, int file) { //returns if a piece in a certain file or square is black
    ReturnPiece piece = getSquare(rank, file);
    if (piece.pieceType == null){
        return false;
    }
    if (piece.pieceType == ReturnPiece.PieceType.BB || piece.pieceType == ReturnPiece.PieceType.BK || 
    piece.pieceType == ReturnPiece.PieceType.BN || piece.pieceType == ReturnPiece.PieceType.BP ||
    piece.pieceType == ReturnPiece.PieceType.BQ || piece.pieceType == ReturnPiece.PieceType.BR){
        return true;
    } 
return false;
    }

    public static boolean isWhitePiece(int rank, int file){ //returns if a piece in a certain file or square is black
        ReturnPiece piece = getSquare(rank, file);
        if (piece.pieceType == null){
            return false;
        }
        return !isBlackPiece(rank, file); 
    }
    public static int getRank(String move){ //gets initial rank based on users input string
        move = move.trim();
        return Integer.parseInt(move.substring(1,2))-1;

    }
    public static int getFinalRank(String move){
        move = move.trim();
        return Integer.parseInt(move.substring(4, 5)) -1;
    }
public static int getFile(String move){ //gets initial file based on users input string
    move = move.trim();
    return move.charAt(0) - 'a';
}
public static int getFinalFile(String move){ //gets initial file based on users input string
    move = move.trim();
    return move.charAt(3) - 'a';
}
    public static ReturnPiece.PieceType curPieceType(String move){ //returns the current piece the user is trying to move
        move = move.trim();
        int square_File = move.charAt(0) - 'a';
        int square_Rank = Integer.parseInt(move.substring(1,2))-1;
    
        ReturnPiece.PieceType piece = Chess.board[square_Rank][square_File].pieceType;
        return piece;
    
    }

    public static ReturnPiece getWhiteKingSquare(ArrayList<ReturnPiece> returnPlay){
        for (int i = 0; i < returnPlay.size(); i++){
            if (returnPlay.get(i).pieceType == PieceType.WK){
                return returnPlay.get(i);
            }
        }
       return null;
    }
    public static ReturnPiece getBlackKingSquare(ArrayList<ReturnPiece> returnPlay){
        for (int i = 0; i < returnPlay.size(); i++){
            if (returnPlay.get(i).pieceType == PieceType.BK){
                return returnPlay.get(i);
            }
        }
       return null;
    }
    public static String generateMoveString(int initial_rank, PieceFile initial_file, int final_rank, PieceFile final_file){
        char initialFileChar = (char) ('a' + initial_file.ordinal());
        char finalFileChar = (char) ('a' + final_file.ordinal());
        return "" + initialFileChar + (initial_rank) + " " + finalFileChar + (final_rank);
    }
    public static boolean isBlack(ReturnPiece piece){
        if (piece.pieceType.equals(null)){
            return false;
        }
        if (piece.pieceType == ReturnPiece.PieceType.BB || piece.pieceType == ReturnPiece.PieceType.BK || 
        piece.pieceType == ReturnPiece.PieceType.BN || piece.pieceType == ReturnPiece.PieceType.BP ||
        piece.pieceType == ReturnPiece.PieceType.BQ || piece.pieceType == ReturnPiece.PieceType.BR){
            return true;    

        }
        return false;
        
    }
    public static boolean isWhite(ReturnPiece piece){
        if (piece.pieceType == null){
            return false;
        }
        if (piece.pieceType == ReturnPiece.PieceType.WB || piece.pieceType == ReturnPiece.PieceType.WK || 
        piece.pieceType == ReturnPiece.PieceType.WN || piece.pieceType == ReturnPiece.PieceType.WP ||
        piece.pieceType == ReturnPiece.PieceType.WQ || piece.pieceType == ReturnPiece.PieceType.WR){
            return true;    

        }
        return false;
    }
}
