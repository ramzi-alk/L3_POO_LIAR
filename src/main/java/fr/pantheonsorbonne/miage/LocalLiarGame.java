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

    @Override
    protected Set<String> getInitialPlayers() {
        return this.initialPlayers;
    }

    @Override
    protected void giveCardsToPlayer(String playerName, Card[] hand) {
       
       List<Card> cards = Arrays.asList(hand);
        this.giveCardsToPlayer(cards, playerName);
    }

   

    

    @Override
    protected Card getCardOrGameOver(Collection<Card> leftOverCard, String cardProviderPlayer, String cardProviderPlayerOpponent) {

        if (!this.playerCards.containsKey(cardProviderPlayer) || this.playerCards.get(cardProviderPlayer).isEmpty()) {
            this.playerCards.get(cardProviderPlayerOpponent).addAll(leftOverCard);
            this.playerCards.remove(cardProviderPlayer);
            return null;
        } else {
            return this.playerCards.get(cardProviderPlayer).poll();
        }
    }

    @Override
    protected void giveCardsToPlayer(Collection<Card> roundStack, String winner) {
        List<Card> cards = new ArrayList<>();
        cards.addAll(roundStack);
        Collections.shuffle(cards);
        this.playerCards.get(winner).addAll(cards);
    }

    @Override
    protected Card getCardFromPlayer(String player) throws NoMoreCardException {
        if (this.playerCards.get(player).isEmpty()) {
            throw new NoMoreCardException();
        } else {
            return this.playerCards.get(player).poll();
        }
    }
}
