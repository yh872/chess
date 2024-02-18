package chess;

public class Bishop {
    public static boolean isLegalBishopWhite(String move){
        move = move.trim();
        int initial_rank = Helper.getRank(move);
        int initial_file = Helper.getFile(move);
        int final_file = move.charAt(3) - 'a';
        int final_rank = Integer.parseInt(move.substring(4, 5)) -1;
        if (Helper.isWhitePiece(final_rank, final_file)){
            return false;
        }
        if (Math.abs(initial_file - final_file) == Math.abs(initial_rank - final_rank) ){
            int tempFile = initial_file;
            int tempRank = initial_rank;
            if (initial_file > final_file && initial_rank < final_rank ){
                tempFile--;
                tempRank++;
                while (tempFile != final_file && tempRank != final_rank){
                    if (!Helper.isEmptySquare(tempRank, tempFile)){
                        return false;
                    }
                    tempFile--;
                    tempRank++;
                }
                return true;
            }
            else if (initial_file > final_file && initial_rank > final_rank ){
                tempFile--;
                tempRank--;
                while (tempFile != final_file && tempRank != final_rank){
                    if (!Helper.isEmptySquare(tempRank, tempFile)){

                        return false;
                    }
                    tempFile--;
                    tempRank--;
                }
                return true;
            }
               else if (initial_file < final_file && initial_rank < final_rank ){
                tempFile++;
                tempRank++;
                    while (tempFile != final_file && tempRank != final_rank){
                        if (!Helper.isEmptySquare(tempRank, tempFile)){
            
                            return false;
                        }
                        tempFile++;
                        tempRank++;
                    }
                    return true;
                }
                else if (initial_file < final_file && initial_rank > final_rank ){
                    tempFile++;
                tempRank--;
                    while (tempFile != final_file && tempRank != final_rank){
                        if (!Helper.isEmptySquare(tempRank, tempFile)){
                  
                            return false;
                        }
                        tempFile++;
                        tempRank--;
                    }
                    return true;
                }
            }
            return false;
        }

    
    
    public static boolean isLegalBishopBlack(String move){
        move = move.trim();
        int initial_rank = Helper.getRank(move);
        int initial_file = Helper.getFile(move);
        int final_file = move.charAt(3) - 'a';
        int final_rank = Integer.parseInt(move.substring(4, 5)) -1;
        if (Helper.isBlackPiece(final_rank, final_file)){
            return false;
        }
        if (Math.abs(initial_file - final_file) == Math.abs(initial_rank - final_rank) ){
            int tempFile = initial_file;
            int tempRank = initial_rank;
            if (initial_file > final_file && initial_rank < final_rank ){
                tempFile--;
                tempRank++;
                while (tempFile != final_file && tempRank != final_rank){
                    if (!Helper.isEmptySquare(tempRank, tempFile)){
                        return false;
                    }
                    tempFile--;
                    tempRank++;
                }
                return true;
            }
            else if (initial_file > final_file && initial_rank > final_rank ){
                tempFile--;
                tempRank--;
                while (tempFile != final_file && tempRank != final_rank){
                    if (!Helper.isEmptySquare(tempRank, tempFile)){
                        return false;
                    }
                    tempFile--;
                    tempRank--;
                }
                return true;
            }
               else if (initial_file < final_file && initial_rank < final_rank ){
                tempFile++;
                tempRank++;
                    while (tempFile != final_file && tempRank != final_rank){
                        if (!Helper.isEmptySquare(tempRank, tempFile)){
                            return false;
                        }
                        tempFile++;
                        tempRank++;
                    }
                    return true;
                }
                else if (initial_file < final_file && initial_rank > final_rank ){
                    tempFile++;
                tempRank--;
                    while (tempFile != final_file && tempRank != final_rank){
                        if (!Helper.isEmptySquare(tempRank, tempFile)){
                            return false;
                        }
                        tempFile++;
                        tempRank--;
                    }
                    return true;
                }
            }
            return false;
    }

}
