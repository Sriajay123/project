package machine_coding.tictactoe.controllers;

import machine_coding.tictactoe.exceptions.*;
import machine_coding.tictactoe.models.*;


import java.util.*;

public class GameController {
    public Game createGame(List<Player> players, int undoLimitPerPlayer) throws BotCountExceededException {
        Game game=Game.getBuilder()
                .setPlayer(players)
                .setUndoLimit(undoLimitPerPlayer)
                .build();
        return game;
    }

    public GameStatus getGameStatus(Game game){
        return game.getGameStatus();
    }

    public void printBoard(Game game){
        game.printBoard();
    }

    public void makeMove(Game game){
        game.makeMove();
    }

    public Player getCurrentPlayer(Game game){
        return game.getCurrentPlayer();
    }

    public void undo(Game game){
        game.undo();
    }

    public void replay(Game game) throws InvalidGameStateException {
        game.replay();
    }
}
