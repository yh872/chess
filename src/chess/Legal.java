package chess;

import chess.ReturnPiece.PieceType;

public class Legal {
    public static int TurnColor = 2;

    public static boolean isWhitePiece(ReturnPiece p){
        PieceType p1 = p.pieceType;
        if (p1 == PieceType.WB || p1 == PieceType.WK || p1 == PieceType.WN || 
        p1 == PieceType.WP || p1 == PieceType.WQ || p1 == PieceType.WR){
            return true;
        }
        return false;
    }
    public static boolean isBlackPiece(ReturnPiece p){
        return !isWhitePiece(p);
    }

    public static boolean isWhiteMove(){
        if (TurnColor % 2 == 0){
            return true;
        }
        return false;
    }
    public static boolean isBlackMove(){
        return !isWhiteMove();
    }
    
    public static void AlternateMoves(){
        TurnColor++;
    }

public static boolean isLegal(String move){
    ReturnPiece.PieceType p = Helper.curPieceType(move);
    switch (p){
        case WP:
        return Pawn.isLegalPawnWhite(move);

        default:
        return false;
    }
} //this method will look for moves that are not possible based on the 

}