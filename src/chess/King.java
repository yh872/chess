package chess;


import chess.ReturnPiece.PieceType;

public class King {
    public static boolean isLegalKingWhite(String move){
        move = move.trim();
        int initial_rank = Helper.getRank(move);
        int initial_file = Helper.getFile(move);
        int final_file = move.charAt(3) - 'a';
        int final_rank = Integer.parseInt(move.substring(4, 5)) -1;
        if (Helper.isWhitePiece(final_rank, final_file)){
            return false;
        }
        if (Math.abs(final_rank -initial_rank) <= 1 && Math.abs(final_file - initial_file) <=1){
            return true;
        }
        return false;
    }
    public static boolean isLegalKingBlack(String move){
        move = move.trim();
        int initial_rank = Helper.getRank(move);
        int initial_file = Helper.getFile(move);
        int final_file = move.charAt(3) - 'a';
        int final_rank = Integer.parseInt(move.substring(4, 5)) -1;
        if (Helper.isBlackPiece(final_rank, final_file)){
            return false;

        }
        if (Math.abs(final_rank -initial_rank) <= 1 && Math.abs(final_file - initial_file) <=1){
            return true;
        }

        return false;
    }
    public static boolean WhiteCastleKingsideLegal(String move){
       if (move.equals("e1 g1")){
            if (Helper.curPieceType(move) == PieceType.WK){
                return true;
            }
       }
        return false;
    }
    public static boolean WhiteCastleQueensSide(String move){
        if (move.equals("e1 c1")){
             if (Helper.curPieceType(move) == PieceType.WK){
                 return true;
             }
        }
         return false;
     }
     public static boolean BlackCastleKingSide(String move){
        if (move.equals("e8 g8")){
             if (Helper.curPieceType(move) == PieceType.BK){
                 return true;
             }
        }
         return false;
     }
     public static boolean BlackCastleQueenSide(String move){
        if (move.equals("e8 c8")){
             if (Helper.curPieceType(move) == PieceType.BK){
                 return true;
             }
        }
         return false;
     }
}
