package fr.pantheonsorbonne.miage;

import fr.pantheonsorbonne.miage.exception.NoMoreCardException;
import fr.pantheonsorbonne.miage.game.Card;
import fr.pantheonsorbonne.miage.game.Player;
import java.util.*;


/**
 * this class implements the war game locally
 */
public class LocalLiarGame extends LiarGameEngine {

    private final Set<String> initialPlayers;
    private final Map<String, Queue<Card>> playerCards = new HashMap<>();

    public LocalLiarGame(Set<String> initialPlayers) {
        this.initialPlayers = initialPlayers;
        for (String player : initialPlayers) {
            playerCards.put(player, new LinkedList<>());
        }
    }

    public static void main(String... args) {
       

        Player p1 = new Player("Ramzi");
        Player p2 = new Player("Adam");
        Player p3 = new Player("Chadi");
        Player p4 = new Player("Wassim");

        LiarGameEngine.game(p1,p2,p3,p4);
    }

   

   

    
   

    

   
}
