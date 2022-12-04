package fr.pantheonsorbonne.miage;

import org.junit.jupiter.api.Test;

import fr.pantheonsorbonne.miage.game.Card;

public class CardTest {
    
    @Test
    public void testCard() {
        Card card = new Card("ACE", "CARREAU");
        assert(card.getName().equals("ACE"));
        assert(card.getColor().equals("CARREAU"));
    }
    
    @Test
    public void testCardNameFalse() {
        Card card = new Card("A", "CARREAU");
        assert(!card.getName().equals("ACE"));
        assert(card.getColor().equals("CARREAU"));
    }
    @Test
    public void testCardColorFalse() {
        Card card = new Card("ACE", "C");
        assert(card.getName().equals("ACE"));
        assert(!card.getColor().equals("CARREAU"));
    }


}
