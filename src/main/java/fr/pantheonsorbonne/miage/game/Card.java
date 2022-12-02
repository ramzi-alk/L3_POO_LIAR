package fr.pantheonsorbonne.miage.game;


import java.util.Arrays;
import java.util.stream.Collectors;

public class Card{
    protected static final String[] names = {"ACE","ROI",
        "REINE",
        "VALET",
        "10",
        "9",
        "8",
        "7", "6", "5", "4", "3", "2"};
    protected static final String[] colors = {"CARREAU", "COEUR", "TREFLE","PIC" };
    private String name;
    private String color;

    public String getColor() {
        return color;
    }


    public void setColor(String color) {
        this.color = color;
    }


    public String[] getNames() {
        return names;
    }

   
    public String[] getColors() {
        return colors;
    }

    public static String cardsToString(Card[] cards) {
        return Arrays.stream(cards).map(Card::toString).collect(Collectors.joining(";"));
    }

    public static Card[] stringToCards(String cards) {
        if (cards.isEmpty()) {
            return new Card[0];
        }
        return (Card[]) Arrays.stream(cards.split(";")).map(Card::valueOf).toArray(Card[]::new);
    }

    /**
     * For a String representation of a card, return the card
     *
     * @param str
     * @return the card
     * @throws RuntimeException if the String representation is Invaliid
     */
  

    public static Card valueOf(String str){




        return null;
    }

    public String getName(){
        return this.name;
    }
    public Card(String name, String color ){
        this.name = name;
        this.color = color;
    }
   
    
    
    

}