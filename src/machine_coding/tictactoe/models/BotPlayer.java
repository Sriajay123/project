package machine_coding.tictactoe.models;

import machine_coding.tictactoe.factories.*;
import machine_coding.tictactoe.strategies.bot_playing.*;


public class BotPlayer extends Player{
    private BotLevel botLevel;
    private BotPlayingStrategy botPlayingStrategy;
    public BotPlayer(String name, Symbol s, BotLevel level) {
        super(name, s);
        this.botLevel=level;
        this.botPlayingStrategy = BotPlayingStrategyFactory.getBotPlayingStrategy(botLevel);
    }

    @Override
    public Pair<Integer, Integer> makeMove(Board board) {
        return botPlayingStrategy.makeMove(board);
    }
}