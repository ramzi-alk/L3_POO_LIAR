package fr.pantheonsorbonne.miage;

import fr.pantheonsorbonne.miage.exception.NoMoreCardException;
import fr.pantheonsorbonne.miage.game.Card;
import fr.pantheonsorbonne.miage.game.Deck;
import fr.pantheonsorbonne.miage.game.Player;

import java.util.Collection;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Set;

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
     * play a war game wit the provided players
     */
    public void play() {
       
    }

    /**
     * provide the list of the initial players to play the game
     *
     * @return
     */
    protected abstract Set<String> getInitialPlayers();

    /**
     * give some card to a player
     *
     * @param playerName the player that will receive the cards
     * @param hand       the cards as a string (to be converted later)
     */
    protected abstract void giveCardsToPlayer(String playerName, Card[] hand);

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
        boolean isALyer = false;
        Player winner = null;
        Deque<Card> cardsPlayed;
        
        while(menteur == false){
            for (Player player : players) {
                if(player.getHand().isEmpty()){
                    winner = player;
                    players.remove(player);
                
                    return winner;
                    
                    
                }else{
                    cardsPlayed = player.chooseCard(actualCard);
                    roundDeck.addAll(cardsPlayed);
                    for(Player pl : players){
                        if(pl != player){
                           isALyer = pl.isLying(actualCard, player.getName(), cardsPlayed.size());
                            if (isALyer == true){
                               Player theLyer = whosLying(pl, player, cardsPlayed, actualCard);
                               if(theLyer.equals(player)){
                                   player.getHand().addAll(roundDeck);
                                   System.out.println(player.getName() + " is a liar");
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
    
                    
                    indexValeur = (indexValeur + 1) % 12;
                    actualCard = cardValues[indexValeur];
                }
                
            }
        }

        return null;
    }
    /**
     * Play a single round
     *
     * @param players             the queue containing the remaining players
     * @param firstPlayerInRound  the first contestant in this round
     * @param secondPlayerInRound the second contestant in this round
     * @param thirdPlayerInRound  the third contestant in this round
     * @param fourthPlayerInRound the fourth contestant in this round
     * @param roundDeck           possible cards left over from previous rounds
     * @return true if we have a winner for this round, false otherwise
     */
    
    protected boolean playRound(Queue<String> players, String firstPlayerInRound, String secondPlayerInRound,String thirdPlayerInRound,String fourthPlayerInRound, Queue<Card> roundDeck) {
      return true;
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

    /**
     * this method must be called when a winner is identified
     *
     * @param winner the final winner of the same
     */
    protected static void declareWinner(String winner) {
        
    }

    /**
     * get a card from a player. If the player doesn't have a card, it will be declared loser and all the left over cards will be given to his opponent
     *
     * @param leftOverCard               card left over from another round
     * @param cardProviderPlayer         the player that should give a card
     * @param cardProviderPlayerOpponent the Opponent of this player
     * @return a card of null if player cardProviderPlayer is gameover
     */
    protected abstract Card getCardOrGameOver(Collection<Card> leftOverCard, String cardProviderPlayer, String cardProviderPlayerOpponent);



    /**
     * give some card to a player
     *
     * @param playerName the player that will receive the cards
     * @param cards      the cards as a collection of cards
     */
    protected abstract void giveCardsToPlayer(Collection<Card> cards, String playerName);

    /**
     * get a card from a player
     *
     * @param player the player to give card
     * @return the card from the player
     * @throws NoMoreCardException if the player does not have a remaining card
     */
    protected abstract Card getCardFromPlayer(String player) throws NoMoreCardException;
}
