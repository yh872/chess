package chess;

public class Queen {
    public static boolean isLegalQueenWhite(String move){
        if (Bishop.isLegalBishopWhite(move) || Rook.isLegalRookWhite(move)){
            return true;
        }
        return false;
    }
    public static boolean isLegalQueenBlack(String move){
        if (Bishop.isLegalBishopBlack(move) || Rook.isLegalRookBlack(move)){
            return true;
        }
        return false;
    }
}
