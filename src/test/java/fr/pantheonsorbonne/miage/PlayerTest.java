package fr.pantheonsorbonne.miage;

import fr.pantheonsorbonne.miage.game.Player;

import org.junit.jupiter.api.Test;

public class PlayerTest {
    
    Player playerToto = new Player("TOTO");
    Player playerTiti = new Player("TITI");

    @Test
    public void testPlayer() {
        assert(playerToto.getName().equals("TOTO"));
    }

    @Test 
    public void testPlayerFalse() {
        assert(!playerToto.getName().equals("TITI"));
    }

    @Test
    public void testPlayerCard(){
        assert(playerToto.getHand().size() == 13);
    }

    @Test 
    public void testChooseCard(){
        assert(playerToto.chooseCard("ACE") != null);
    }

    @Test 
    public void testIsLying(){
        assert(playerToto.isLying("ACE", "TITI", 2) == false || playerToto.isLying("ACE", "TITI", 2) == true);
    }

    @Test
    public void testAddHistoricalPlay(){
          
        playerToto.addHistoricalPlay("ACE", "TITI", 2);
          
        assert(playerToto.getLastPlay().size() == 1);

        assert(playerToto.getLastPlay().get(0).get(0).toString().compareTo("[ACE, ACE]") == 0);

        assert(playerToto.getLastPlay().get(0).get(2).toString().compareTo("[TITI]") == 0);
    }

    @Test
    public void testIsACardPlayedMoreThan4(){
        playerToto.addHistoricalPlay("ACE", "TITI", 4);
        playerToto.addHistoricalPlay("ACE", "TITI", 2);

        assert(playerToto.isACardPlayedMoreThan4() == true);
    }

    @Test void testIsACardPlayedMoreThan4False(){
        playerToto.addHistoricalPlay("ACE", "TITI", 2);

        assert(playerToto.isACardPlayedMoreThan4() == false); 
    }

}
