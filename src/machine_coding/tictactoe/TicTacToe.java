package machine_coding.tictactoe;

import machine_coding.tictactoe.controllers.*;
import machine_coding.tictactoe.models.*;
import machine_coding.tictactoe.controllers.*;
import machine_coding.tictactoe.models.*;

import java.util.*;

public class TicTacToe {

    public static void main(String[] args) {
        GameController gameController=new GameController();

        Scanner scanner=new Scanner(System.in);
        System.out.println("How many human players?");
        int numOfHumanPlayers = scanner.nextInt();

        List<Player> players=new ArrayList<>();
        Set<Character> symbolSet=new HashSet<>();
        for(int i=0; i<numOfHumanPlayers; i++){
            System.out.println("Enter name and symbol for player");
            String name= scanner.next();
            String symbolStr= scanner.next();
            char symbol=symbolStr.charAt(0);
            while(symbolSet.contains(symbol)){
                System.out.println("Enter a new symbol");
                symbolStr= scanner.next();
                symbol=symbolStr.charAt(0);
            }
            symbolSet.add(symbol);
            players.add(new HumanPlayer(name, new Symbol(symbol)));
        }

        System.out.println("How many bots?");
        int numOfBotPlayers= scanner.nextInt();

        String s="abcdefghijklmnopqrstuvwxyz1234567890";
        Random random=new Random();
        for(int i=0;i< numOfBotPlayers;i++){
            System.out.println(("Choose bot difficulty level: E/M/D"));
            String diffLevelString= scanner.next();
            BotLevel botLevel;
            switch (diffLevelString.charAt(0)){
                case 'E':
                    botLevel=BotLevel.EASY;
                    break;
                case 'M':
                    botLevel=BotLevel.MEDIUM;
                    break;
                case 'D':
                    botLevel=BotLevel.DIFFICULT;
                    break;
                default:
                    System.out.println("Invalid input, choosing easy!");
                    botLevel=BotLevel.EASY;
                    break;
            }
            int idx = random.nextInt(s.length());
            char symbol=s.charAt(idx);
            while(symbolSet.contains(symbol)){
                idx = random.nextInt(s.length());
                symbol=s.charAt(idx);
            }
            symbolSet.add(symbol);
            players.add(new BotPlayer("Bot" + (i+1), new Symbol(symbol), botLevel));
        }

        System.out.println("Enter # of undos a player can do?");
        int numOfUndos = scanner.nextInt();
        Game game;
        try{
            game = gameController.createGame(players, numOfUndos);
        }catch (Exception e){
            System.out.println("Error while creating the game" + e.getMessage());
            return;
        }

        //Start playing the game
        //while game is in progress
        //1. print the current board
        //2. Make a move

        //2.1 after moving we should check if the player has won or not
        //2.1.1 - If the player wins,  set the gameStatus to ENDED
        //2.1.2 - Else if drawn, set the gameStatus to DRAWN
        //2.1.3 - Else, pass the chance to next player and capture the move

        while(gameController.getGameStatus(game) == GameStatus.IN_PROGRESS){
            gameController.printBoard(game);

            gameController.makeMove(game);
            gameController.undo(game);
        }
        GameStatus gameStatus = gameController.getGameStatus(game);
        if(gameStatus.equals(GameStatus.ENDED)){
            Player winningPlayer = gameController.getCurrentPlayer(game);
            System.out.println(winningPlayer.getName() + " has won with symbol " + winningPlayer.getSymbol().getSymbol());
        }
        else{
            System.out.println("This game has drawn");
        }
        gameController.printBoard(game);

        System.out.println("Do you want to replay the entire game? (y/n)");
        String replayReply = scanner.next();
        if(replayReply.charAt(0) == 'y' || replayReply.charAt(0) == 'Y'){
            gameController.replay(game);
        }
    }
}
