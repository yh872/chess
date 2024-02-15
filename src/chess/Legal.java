package chess;


public class Legal {


public static boolean isLegal(String move){ //main legal method to check if a move follows the laws of chess
    ReturnPiece.PieceType p = Helper.curPieceType(move);
    switch (p){
        case WP:
        return Pawn.isLegalPawnWhite(move);
        case BP:
        return Pawn.isLegalPawnBlack(move);

        default:
        return false;
    }
} //this method will look for moves that are not possible based on the 

}