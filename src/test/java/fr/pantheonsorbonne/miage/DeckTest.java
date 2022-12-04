package fr.pantheonsorbonne.miage;

import fr.pantheonsorbonne.miage.game.Deck;
import fr.pantheonsorbonne.miage.game.Card;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

public class DeckTest {
    
    
    Deck deck = new Deck();

    @Test 
    public void testDeckSize() {
      
       List<Card> cards = deck.getRandomCards(40);
         assert(cards.size() == 40);
        
    }

    @Test
    public void testDeckSizeFalse() {
      
       List<Card> cards = deck.getRandomCards(40);
         assert(cards.size() != 50);
        
    }
    
    @Test
    public void testDeckSizeOver52() {
               
        assertThrows(IllegalArgumentException.class, () -> {
            deck.getRandomCards(59);
        });
        
    }

    @Test 
    public void testDeckSizeUnder0() {
               
        assertThrows(IllegalArgumentException.class, () -> {
            deck.getRandomCards(-1);
        });
        
    }


}
