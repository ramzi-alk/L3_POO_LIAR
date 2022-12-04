package fr.pantheonsorbonne.miage;

import fr.pantheonsorbonne.miage.game.Card;
import fr.pantheonsorbonne.miage.game.Deck;
import fr.pantheonsorbonne.miage.game.Player;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/**
 * this class is a abstract version of the engine, to be used locally on through the network
 */
public abstract class LiarGameEngine {
 
 
    public static void game(Player p1, Player p2, Player p3, Player p4){
         // make a queue with all the players
         final Queue<Player> players = new LinkedList<>();
         
         players.add(p1);
         players.add(p2);
         players.add(p3);
         players.add(p4);

         

         Player winner = null;

         while(players.size() > 3){
           winner = gameRound(players);
                
         }
           
            
            System.out.println(winner.getName() + " won! bye");
            System.exit(0);
    }

    

    /**
     * this method is the core of the game, it is a round of the game
     * @param roundDeck the deck of the round
     * @param players the players of the round
     * @param currentPlayer the current player who is playing
     * @param actualCard the actual card that is played
     * @return true if someone said liar, false otherwise
     */
    protected static boolean lieGame(Deque<Card> roundDeck,Queue<Player> players, Player currentPlayer, String actualCard){
        boolean menteur = false;
        // the player choose cards to play
        Deque<Card> cardsPlayed = currentPlayer.chooseCard(actualCard);
        roundDeck.addAll(cardsPlayed);

        for(Player pl : players){
            if(pl != currentPlayer){
                // after each player plays, the other players can say liar or not
               boolean isAlier = pl.isLying(actualCard, currentPlayer.getName(), cardsPlayed.size());
                if (isAlier){
                    System.out.println(pl.getName() + " said liar! to the player " + currentPlayer.getName());
                    // check whos lying
                   Player theLiar = whosLying(pl, currentPlayer, cardsPlayed, actualCard);
                
                   //if the liar is the current player so he takes all the cards otherwise the accuser takes the cards
                   if(theLiar.equals(currentPlayer)){
                     currentPlayer.getHand().addAll(roundDeck);
                       System.out.println(currentPlayer.getName() + " is a liar");
                       for (Player pl2 : players) {
                        pl2.getLastPlay().clear();
                        }
                       roundDeck.clear();
                       menteur = true;
                    }else{
                        pl.getHand().addAll(roundDeck);
                        
                        System.out.println(pl.getName() + " is a liar");

                        for (Player pl1 : players) {
                            pl1.getLastPlay().clear();
                        }
                    
                        roundDeck.clear();
                        menteur = true;
                        }
                        
                    
                }
            }
            
        }

        
        return menteur;
    }


    /**
     * play until one player runs out of cards
     * @param players the players to play the game must be 4
     * @return Player the winner
     */
    protected static Player gameRound(Queue<Player> players){
        Deque<Card> roundDeck = new LinkedList<>();
        Random random = new Random();

        int indexValeur = random.nextInt(13);
        
        String[] cardValues = Deck.getNames();
        String actualCard = cardValues[indexValeur];
        boolean menteur = false;
        
        Player winner = null;
        
        
        while(!menteur){
            for (Player player : players) {
                // check if the player has cards
                if(player.getHand().isEmpty()){
                    winner = player;
                    players.remove(player);
                
                    return winner;
                    
                    
                }else{
                    
                    lieGame(roundDeck, players, player, actualCard);
    
                    // take the next card to play
                    indexValeur = (indexValeur + 1) % 12;
                    actualCard = cardValues[indexValeur];
                }
                
            }
        }

        return null;
    }
   
    
   

    /**
     * determine who is lying
     * @param accuserPlayer the player who accuse
     * @param accusedPlayer the player who is accused
     * @param cardOfAccusedPlayer the cards of the accused player
     * @param actualCard the card to play during the round
     * @return
     */
    protected static Player whosLying(Player accuserPlayer ,Player accusedPlayer, Deque<Card> cardOfAccusedPlayer, String actualCard){
        
       Player response = accuserPlayer;
        for(int i = 0; i < cardOfAccusedPlayer.size(); i++){
            if(cardOfAccusedPlayer.peek().getName().equals(actualCard)){
                response = accuserPlayer;
                cardOfAccusedPlayer.pop();
            }else{
                response = accusedPlayer;
            }
        }

        return response;
        
            
    }

    

    
}
