package machine_coding.tictactoe.factories;

import machine_coding.tictactoe.models.*;
import machine_coding.tictactoe.strategies.bot_playing.*;


public class BotPlayingStrategyFactory {
    public static BotPlayingStrategy getBotPlayingStrategy(BotLevel botLevel){
        switch (botLevel){
            case EASY:
            case MEDIUM:
            case DIFFICULT:
                return new EasyBotPlayingStrategy();
            default:
                throw new UnsupportedOperationException("The given bot level is not supported");
        }
    }
}
