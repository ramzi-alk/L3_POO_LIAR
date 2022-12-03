package fr.pantheonsorbonne.miage;

import fr.pantheonsorbonne.miage.game.Player;



/**
 * this class implements the war game locally
 */
public class LocalLiarGame extends LiarGameEngine {
    
    public static void main(String... args) {
       

        Player p1 = new Player("Ramzi");
        Player p2 = new Player("Adam");
        Player p3 = new Player("Chadi");
        Player p4 = new Player("Wassim");

        LiarGameEngine.game(p1,p2,p3,p4);
    }

}
