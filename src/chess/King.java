package chess;

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
    public static boolean CanCastleWhite(ReturnPiece[][] board){
        return false;
    }
    public static boolean CanCastleBlack(ReturnPiece[][] board){
        return false;
    }
}
