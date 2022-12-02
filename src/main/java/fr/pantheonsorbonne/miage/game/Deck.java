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
    
    
    public static List<Card> getRandomCards(int n){
        Random random = new Random();
        
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
            
            repetition.add(names[indexValeur]+colors[indexCouleur]);
              
        }

        return hand;
    
    }
    public static String[] getNames() {
        return names;
    }
}