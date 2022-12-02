package fr.pantheonsorbonne.miage.game;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Player {
    private String name;
    private List<Card> hand = new ArrayList<>();
    private List<List> lastPlay = new ArrayList<>();
    private Random random = new Random();

    public List<List> getLastPlay() {
        return lastPlay;
    }
    public void setLastPlay(List<List> lastPlay) {
        this.lastPlay = lastPlay;
    }

    public Player(String name) {
        this.name = name;
        this.hand =  Deck.getRandomCards(4*13/4);
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public List<Card> getHand() {
        return hand;
    }
    public void setHand(List<Card> hand) {
        this.hand = hand;
    }
    
    /**
     * Save the last round played for strategy
     * @param currentCardPlaying the card that is currently played
     * @param playerName the name of the player that is playing
     * @param nbCardPlaying the number of card that the player is playing
     */
    public void addHistoricalPlay(String currentCardPlaying,String playerName, int nbCardPlaying){
        List<String> myLastPlayers = new ArrayList<>();
        List<Integer> myNbCardPlaying = new ArrayList<>();
        List<String> myCurrentCardPlaying = new ArrayList<>();
        List<List> myLastPlay = new ArrayList<>();
        
        for (int i = 0; i < nbCardPlaying; i++) {

            myCurrentCardPlaying.add(currentCardPlaying);
                    
        }
        myLastPlay.add(myCurrentCardPlaying);

        myNbCardPlaying.add(nbCardPlaying);

        myLastPlay.add(myNbCardPlaying);

        myLastPlayers.add(playerName);
        myLastPlay.add(myLastPlayers);

        this.lastPlay.add(myLastPlay);

    }

    /**
     * determine if the player want to say liar or not
     * @param currentCardPlaying the card that is currently played
     * @param playerName the name of the player that is playing
     * @param nbCardPlaying the number of card that the player is playing
     * @return boolean of the response of the player
     */
    public boolean isLying(String currentCardPlaying,String playerName, int nbCardPlaying){

        addHistoricalPlay(currentCardPlaying, playerName, nbCardPlaying);

        // if the player plays a cards that he doesn't have
        int opt = 0;

        for(int i = 0; i< this.hand.size(); i++){
            if(this.hand.get(i).getName().equals(currentCardPlaying)){
                opt++;
            }
        }

        isCardAlreadyPlay();
       
        String[] cardNames = Deck.getNames();

        for(String cardName : cardNames){
            int opt3 = 0;
            for(int i = 0; i<lastPlay.size(); i++){
                
               ArrayList<String> cardPlayedName = (ArrayList<String>) lastPlay.get(i).get(0);
               opt3 += Collections.frequency(cardPlayedName, cardName);
            }
            if(opt3 > 4){
                return true;
            }
        }
        if(opt == 4 || opt - 4 > nbCardPlaying ){
            return true;
        }

        return false;
    }

    /**
     * determine if the player played cards that are already played
     * 
     * @return true if the player plays a cards that are already played false otherwise
     */
    public boolean isCardAlreadyPlay(){
        // 
        for(int j = 0; j< this.lastPlay.size(); j++){
            int opt2 = 0;
            for(int i = 0; i< this.hand.size(); i++){
                if(this.hand.get(i).getName().equals(this.lastPlay.get(j).get(0))){
                    opt2++;
                }
                
            }
            ArrayList<Integer> nbCard = (ArrayList<Integer>) this.lastPlay.get(j).get(1);
            int d = nbCard.get(0).intValue();

            if(opt2 == 4 || opt2 - 4 > d){
                return true;
            }
        }
        return false;
    }

    /**
     * the player plays a card or cards from his hand
     * @param String the card to play
     * @return List the cards played
     */
    public Deque<Card> chooseCard(String card){
        Deque<Card> cards = new LinkedList<>();

        for (Card myCard : hand){
            if(myCard.getName().equals(card)){
                cards.add(myCard);
                
            }
        }
        if(cards.isEmpty()){
            

            int nb = this.random.nextInt(1,3);

            for(int i = 0; i < nb; i++){
                
                int index = random.nextInt(hand.size());
                cards.add(hand.get(index));
                
            }
        }

        addHistoricalPlay(card, this.name, cards.size());
    
        
        hand.removeAll(cards);
        return cards;
    }

}
