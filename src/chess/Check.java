package chess;

import java.util.ArrayList;

import chess.ReturnPiece.PieceFile;
import chess.ReturnPiece.PieceType;

public class Check {
    public static boolean isCheckWhite(ArrayList<ReturnPiece> returnPlay){
        ReturnPiece king = Helper.getWhiteKingSquare(returnPlay);
        PieceFile kingFile = king.pieceFile;
        int Kingrank = king.pieceRank;
        for (int i = 0; i <returnPlay.size(); i++){
            if (returnPlay.get(i).pieceType == PieceType.BB || returnPlay.get(i).pieceType == PieceType.BN ||
            returnPlay.get(i).pieceType == PieceType.BP ||returnPlay.get(i).pieceType == PieceType.BQ || 
            returnPlay.get(i).pieceType == PieceType.BR){       
                String move = Helper.generateMoveString(returnPlay.get(i).pieceRank, returnPlay.get(i).pieceFile, Kingrank, kingFile);
                if (Legal.isLegal(move)){
                    return true;
                }
            } 
        }
        return false;
        
    }

    public static boolean isCheckBlack(ArrayList<ReturnPiece> returnPlay){
        ReturnPiece king = Helper.getBlackKingSquare(returnPlay);
        PieceFile kingFile = king.pieceFile;
        int Kingrank = king.pieceRank;
        for (int i = 0; i <returnPlay.size(); i++){
            if (returnPlay.get(i).pieceType == PieceType.WB || returnPlay.get(i).pieceType == PieceType.WN ||
            returnPlay.get(i).pieceType == PieceType.WP ||returnPlay.get(i).pieceType == PieceType.WQ || 
            returnPlay.get(i).pieceType == PieceType.WR){       
                String move = Helper.generateMoveString(returnPlay.get(i).pieceRank, returnPlay.get(i).pieceFile, Kingrank, kingFile);
                if (Legal.isLegal(move)){
                    return true;
                }
            } 
        }
        return false;
        
    }
}
