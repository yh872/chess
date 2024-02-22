package chess;

import chess.ReturnPiece.PieceType;

public class Promotion {
    public static boolean PromotionWhite(String move){
        move = move.trim();
        if (Helper.getRank(move) == 6 && Helper.curPieceType(move) == PieceType.WP  ){
            return true;
        }
        
        return false;
    }
    public static boolean PromotionBlack(String move){
        move = move.trim();
        if (Helper.getRank(move) == 1 && Helper.curPieceType(move) == PieceType.BP  ){
            return true;
        }
        
        return false;
    }
}

