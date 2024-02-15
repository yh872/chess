package chess;

public class Helper {
    public static boolean isEmptySquare(int rank, int file){ //returns if a square is empty or not
        return Chess.board[rank][file].pieceType == null;
    }
    public static ReturnPiece getSquare(int rank, int file){ //returns the ReturnPiece object in a specified file and rank
        return Chess.board[rank][file];
    }
    
    public static boolean isBlackPiece(int rank, int file) { //returns if a piece in a certain file or square is black
    ReturnPiece piece = getSquare(rank, file);
    if (piece.pieceType == ReturnPiece.PieceType.BB || piece.pieceType == ReturnPiece.PieceType.BK || 
    piece.pieceType == ReturnPiece.PieceType.BN || piece.pieceType == ReturnPiece.PieceType.BP ||
    piece.pieceType == ReturnPiece.PieceType.BQ || piece.pieceType == ReturnPiece.PieceType.BR){
        return true;
    } 
return false;
    }

    public static boolean isWhitePiece(int rank, int file){ //returns if a piece in a certain file or square is black
        return !isBlackPiece(rank, file); 
    }
    public static int getRank(String move){ //gets initial rank based on users input string
        move = move.trim();
        return Integer.parseInt(move.substring(1,2))-1;

    }
public static int getFile(String move){ //gets initial file based on users input string
    move = move.trim();
    return move.charAt(0) - 'a';
}
    public static ReturnPiece.PieceType curPieceType(String move){ //returns the current piece the user is trying to move
        move = move.trim();
        int square_File = move.charAt(0) - 'a';
        int square_Rank = Integer.parseInt(move.substring(1,2))-1;
    
        ReturnPiece.PieceType piece = Chess.board[square_Rank][square_File].pieceType;
        return piece;
    
    }
}
