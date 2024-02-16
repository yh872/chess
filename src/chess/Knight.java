package chess;

public class Knight {
    public static boolean isLegalKnightWhite(String move){
        move = move.trim();
        int initial_rank = Helper.getRank(move);
        int initial_file = Helper.getFile(move);
        int final_file = move.charAt(3) - 'a';
        int final_rank = Integer.parseInt(move.substring(4, 5)) -1;
        if (final_rank == initial_rank + 1 && final_file == initial_file -2){
            return true;
        }
        if (final_rank == initial_rank + 2 && final_file == initial_file -1){
            return true;
        }
        if (final_rank == initial_rank + 2 && final_file == initial_file +1){
            return true;
        }
        if (final_rank == initial_rank + 1 && final_file == initial_file +2){
            return true;
        }
        if (final_rank == initial_rank -1 && final_file == initial_file +2){
            return true;
        }
        if (final_rank == initial_rank -2 && final_file == initial_file +1){
            return true;
        }
        if (final_rank == initial_rank -2 && final_file == initial_file -1){
            return true;
        }
        if (final_rank == initial_rank -1 && final_file == initial_file -2){
            return true;
        }

        return false;
    }
    public static boolean isLegalKnightBlack(String move){
        move = move.trim();
        int initial_rank = Helper.getRank(move);
        int initial_file = Helper.getFile(move);
        int final_file = move.charAt(3) - 'a';
        int final_rank = Integer.parseInt(move.substring(4, 5)) -1;
        if (final_rank == initial_rank + 1 && final_file == initial_file -2){
            return true;
        }
        if (final_rank == initial_rank + 2 && final_file == initial_file -1){
            return true;
        }
        if (final_rank == initial_rank + 2 && final_file == initial_file +1){
            return true;
        }
        if (final_rank == initial_rank + 1 && final_file == initial_file +2){
            return true;
        }
        if (final_rank == initial_rank -1 && final_file == initial_file +2){
            return true;
        }
        if (final_rank == initial_rank -2 && final_file == initial_file +1){
            return true;
        }
        if (final_rank == initial_rank -2 && final_file == initial_file -1){
            return true;
        }
        if (final_rank == initial_rank -1 && final_file == initial_file -2){
            return true;
        }

        return false;
    }
    //making 2 seperate methods for each color to make the check/checkmate methods easier to implement
}
