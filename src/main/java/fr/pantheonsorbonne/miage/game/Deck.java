package fr.pantheonsorbonne.miage.game;

import java.util.List;
import java.util.Random;
import java.util.ArrayList;


public class Deck {
    
    static List<String> repetition=new ArrayList<>() ;
    protected static final String[] names = {"ACE","ROI",
    "REINE",
    "VALET",
    "10",
    "9",
    "8",
    "7", "6", "5", "4", "3", "2"};
protected static final String[] colors = {"CARREAU", "COEUR", "TREFLE","PIC" };

    public static Card[] newRandomPileOfCard(){
        Random random = new Random();
        int handLength = names.length * colors.length;
        Card[] pile = new Card[handLength];


        for(int i =0; i<handLength; i++){
            int indexCouleur = random.nextInt(4);
            int indexValeur = random.nextInt(13);
            
             
            while(repetition.contains(names[indexValeur]+colors[indexCouleur])){
                indexCouleur = random.nextInt(4);
                indexValeur = random.nextInt(13);
            }
            
            Card carte = new Card(names[indexValeur],colors[indexCouleur]);
            pile[i] = carte;
            repetition.add(names[indexValeur]+colors[indexCouleur]);
            }
       
        return pile;
    }


    
    public static List<Card> getRandomCards(int n){
        Random random = new Random();
        //Card[] hand = new Card[n];
        List<Card> hand = new ArrayList<>();
         
        for(int i =0; i<n; i++){
            int indexCouleur = random.nextInt(4);
            int indexValeur = random.nextInt(13);

            while(repetition.contains(names[indexValeur]+colors[indexCouleur])){
                indexCouleur = random.nextInt(4);
                indexValeur = random.nextInt(13);
            }

            Card newCarte = new Card(names[indexValeur],colors[indexCouleur]);
            hand.add(newCarte);
            //hand[i] = newCarte;
            repetition.add(names[indexValeur]+colors[indexCouleur]);
              
        }

        return hand;
    
    }
    public static String[] getNames() {
        return names;
    }
}