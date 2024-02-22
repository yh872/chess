package chess;


public class Legal {


public static boolean isLegal(String move){ //main legal method to check if a move follows the laws of chess
    move = move.trim();
    ReturnPiece.PieceType p = Helper.curPieceType(move);
    switch (p){
        case WQ:
        return Queen.isLegalQueenWhite(move);
        case WP:
        return Pawn.isLegalPawnWhite(move);
        case BP:
        return Pawn.isLegalPawnBlack(move);
        case WN:
        return Knight.isLegalKnightWhite(move);
        case BN:
        return Knight.isLegalKnightBlack(move);
        case WK:
        return King.isLegalKingWhite(move);
        case BK:
        return King.isLegalKingBlack(move);
        case WB:
        return Bishop.isLegalBishopWhite(move);
        case BB:
        return Bishop.isLegalBishopBlack(move);
        case WR: 
        return Rook.isLegalRookWhite(move);
        case BR:
        return Rook.isLegalRookBlack(move);
        case BQ:
        return Queen.isLegalQueenBlack(move);

        default:
        return false;
        

    }
    

} //this method will look for moves that are not possible based on the 

}