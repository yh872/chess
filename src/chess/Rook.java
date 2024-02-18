package chess;

public class Rook {
    public static boolean isLegalRookWhite(String move){
        move = move.trim();
        int initial_rank = Helper.getRank(move);
        int initial_file = Helper.getFile(move);
        int final_file = move.charAt(3) - 'a';
        int final_rank = Integer.parseInt(move.substring(4, 5)) -1;
        if (Helper.isWhitePiece(final_rank, final_file)){
            System.out.println("is white piece");
            return false;
        }
        if (initial_rank == final_rank || initial_file == final_file){
            int tempFile = initial_file;
            int tempRank = initial_rank;
            if (initial_rank == final_rank && initial_file > final_file){
                tempFile--;
                while (tempFile != final_file){
                    if (!Helper.isEmptySquare(tempRank, tempFile)){
                        return false;
                    }
                    tempFile--;

                }
                return true;
            }
            else if (initial_rank == final_rank && initial_file < final_file){
                tempFile++;
                while (tempFile != final_file){
                    if (!Helper.isEmptySquare(tempRank, tempFile)){
                        return false;
                    }
                    tempFile++;
                }
                return true;
            }
            else if (initial_file == final_file && initial_rank > final_rank){
                tempRank--;
                while (tempFile != final_file){
                    if (!Helper.isEmptySquare(tempRank, tempFile)){
                        return false;
                    }
                    tempRank--;
                }
                return true;
            }
            else if (initial_file == final_file && initial_rank < final_rank){
                tempRank++;
                while (tempFile != final_file){
                    if (!Helper.isEmptySquare(tempRank, tempFile)){
                        return false;
                    }
                    tempRank++;
                }
                return true;
            }
        }
        return false;
    }
    public static boolean isLegalRookBlack(String move){
        move = move.trim();
        int initial_rank = Helper.getRank(move);
        int initial_file = Helper.getFile(move);
        int final_file = move.charAt(3) - 'a';
        int final_rank = Integer.parseInt(move.substring(4, 5)) -1;
        if (Helper.isWhitePiece(final_rank, final_file)){
            System.out.println("is white piece");
            return false;
        }
        if (initial_rank == final_rank || initial_file == final_file){
            int tempFile = initial_file;
            int tempRank = initial_rank;
            if (initial_rank == final_rank && initial_file > final_file){
                tempFile--;
                while (tempFile != final_file){
                    if (!Helper.isEmptySquare(tempRank, tempFile)){
                        return false;
                    }
                    tempFile--;

                }
                return true;
            }
            else if (initial_rank == final_rank && initial_file < final_file){
                tempFile++;
                while (tempFile != final_file){
                    if (!Helper.isEmptySquare(tempRank, tempFile)){
                        return false;
                    }
                    tempFile++;
                }
                return true;
            }
            else if (initial_file == final_file && initial_rank > final_rank){
                tempRank--;
                while (tempFile != final_file){
                    if (!Helper.isEmptySquare(tempRank, tempFile)){
                        return false;
                    }
                    tempRank--;
                }
                return true;
            }
            else if (initial_file == final_file && initial_rank < final_rank){
                tempRank++;
                while (tempFile != final_file){
                    if (!Helper.isEmptySquare(tempRank, tempFile)){
                        return false;
                    }
                    tempRank++;
                }
                return true;
            }
        }
        return false;
    }

}
