package chess;

import chess.ReturnPiece.PieceType;

public class Queen {
    public static boolean isLegalQueenWhite(String move){
        move = move.trim();
        int initial_rank = Helper.getRank(move);
        int initial_file = Helper.getFile(move);
        int final_file = move.charAt(3) - 'a';
        int final_rank = Integer.parseInt(move.substring(4, 5)) -1;
        if (Helper.isWhitePiece(final_rank, final_file)){
            return false;
        }
        if (initial_file == final_file && initial_rank > final_rank){
            int tempRank = initial_rank;
            while (tempRank>final_rank){
                if (!Helper.isEmptySquare(tempRank, initial_file) && Helper.getSquare(tempRank, initial_file).pieceType != PieceType.WQ){
                    System.out.println(Helper.getSquare(tempRank, initial_file).pieceType);
                                     //   System.out.println("fails here");
                    return false;
                }
                tempRank--;
            }
            return true;
        }
        
        if (Bishop.isLegalBishopWhite(move) || Rook.isLegalRookWhite(move)){
            
            return true;
          
        }
      //  System.out.println("fails here 2");
        return false;
    }
    public static boolean isLegalQueenBlack(String move){
        move = move.trim();
        if (Bishop.isLegalBishopBlack(move) || Rook.isLegalRookBlack(move)){

            return true;
        }
        return false;
    }
}
