package chess;

public class Helper {
    public static boolean isEmptySquare(int rank, int file){
        return Chess.board[rank][file].pieceType == null;
    }
    public static ReturnPiece getSquare(int rank, int file){
        return Chess.board[rank][file];
    }
    
    public static boolean isBlackPiece(int rank, int file) {
    ReturnPiece piece = getSquare(rank, file);
    if (piece.pieceType == ReturnPiece.PieceType.BB || piece.pieceType == ReturnPiece.PieceType.BK || 
    piece.pieceType == ReturnPiece.PieceType.BN || piece.pieceType == ReturnPiece.PieceType.BP ||
    piece.pieceType == ReturnPiece.PieceType.BQ || piece.pieceType == ReturnPiece.PieceType.BR){
        return true;
    } 
return false;
    }

    public static boolean isWhitePiece(int rank, int file){
        return !isBlackPiece(rank, file);
    }
    public static int getRank(String move){
        return Integer.parseInt(move.substring(1,2))-1;

    }
public static int getFile(String move){
    return move.charAt(0) - 'a';
}
    public static ReturnPiece.PieceType curPieceType(String move){
        int square_File = move.charAt(0) - 'a';
        int square_Rank = Integer.parseInt(move.substring(1,2))-1;
    
        ReturnPiece.PieceType piece = Chess.board[square_Rank][square_File].pieceType;
        return piece;
    
    }
}
