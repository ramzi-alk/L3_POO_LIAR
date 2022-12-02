package fr.pantheonsorbonne.miage;

import fr.pantheonsorbonne.miage.game.Card;
import fr.pantheonsorbonne.miage.game.Player;
import fr.pantheonsorbonne.miage.model.Game;
import fr.pantheonsorbonne.miage.model.GameCommand;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Random;

/**
 * this is the player part of the network version of the liar game
 */
public class LiarGameNetorkPlayer {

    static final String playerId = "Player-" + new Random().nextInt();
    static final Deque<Card> hand = new LinkedList<>();
    static final PlayerFacade playerFacade = Facade.getFacade();
    static Game liar;

    public static void main(String[] args) {

        playerFacade.waitReady();

        playerFacade.createNewPlayer(playerId);
        new Player(playerId);

        liar = playerFacade.autoJoinGame("LIAR");

        while (true) {

            GameCommand command = playerFacade.receiveGameCommand(liar);
            switch (command.name()) {
                case "cardsForYou":
                    handleCardsForYou(command);
                    break;
                case "playACard":
                   // System.out.println("I have " + hand.stream().map(Card::toFancyString).collect(Collectors.joining(" ")));
                    handlePlayACard(command);
                    break;
                case "gameOver":
                    handleGameOverCommand(command);
                    break;
                
            }
        }
    }

    private static void handleCardsForYou(GameCommand command) {

       // for (Card card : Card.stringToCards(command.body())) {
      //      hand.offerLast(card);
      //  }

    }

    private static void handlePlayACard(GameCommand command) {
        if (command.params().get("playerId").equals(playerId)) {
            if (!hand.isEmpty()) {
                playerFacade.sendGameCommandToAll(liar, new GameCommand("card", hand.pollFirst().toString()));
            } else {
                playerFacade.sendGameCommandToAll(liar, new GameCommand("outOfCard", playerId));
            }
        }
    }

    private static void handleGameOverCommand(GameCommand command) {
        if (command.body().equals("win")) {
            System.out.println("I've won!");
        } else {
            System.out.println("I've lost :-(");
        }
        System.exit(0);
    }
}
