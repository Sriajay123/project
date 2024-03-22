package machine_coding.tictactoe.strategies.bot_playing;

import machine_coding.tictactoe.models.*;


public interface BotPlayingStrategy {
    public Pair<Integer, Integer> makeMove(Board board);
}
