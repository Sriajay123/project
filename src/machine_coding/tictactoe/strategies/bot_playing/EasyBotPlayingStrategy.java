package machine_coding.tictactoe.strategies.bot_playing;

import machine_coding.tictactoe.exceptions.*;
import machine_coding.tictactoe.models.*;


import java.util.*;

public class EasyBotPlayingStrategy implements BotPlayingStrategy{
    @Override
    public Pair<Integer, Integer> makeMove(Board board) {
        for (List<Cell> row : board.getCells()) {
            for (Cell cell : row) {
                if(cell.getCellStatus().equals(CellStatus.UNOCCUPIED)){
                    return new Pair<>(cell.getRow(), cell.getCol());
                }
            }
        }

        throw new InvalidGameStateException("No place for bot to make a move");
    }
}
