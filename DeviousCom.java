// CS_205 Go Fish

import java.util.LinkedList;
import java.util.Random;

/**
 * DeviousCom class
 * Handles devious computer decisions; performs strategy and lies every 3rd turn
 * @author kevingottfried
 */
public class DeviousCom extends Player {

    UserInterface ui;
    int count_deviate = 0;


    public DeviousCom() {

    }
    /**
     * Constructor instantiates local UI object
     */
    DeviousCom(UserInterface ui) {
        this.ui = ui;
    }

    /**
     * The takeTurn method generates a random card choice to ask opponent for.
     * @param gm GameManager
     * @param p1 Opponent
     */
    public void takeTurn(GameManager gm, Player p1) {
        if (hand.isEmpty()) {
            Card new_card = gm.drawCard();
            addCard(new_card);
            ui.print("End Of The Computers Turn");
        } else {
            Random num = new Random();
            int card;
            card = num.nextInt(hand.size());
            Card rand_card = hand.get(card);
            ui.print("The computer asked for a " + rand_card);
            Card[] opp_hand = p1.askforCards(rand_card);
            if (opp_hand[0] != null) {
                for (int i = 0; i < 3; i++) {
                    if (opp_hand[i] != null) {
                        addCard(opp_hand[i]);
                    }
                }
                successful_request++;

            } else {
                Card new_card = gm.drawCard();
                addCard(new_card);

            }
            total_request++;

            if (hand.size() > 1) {
                sort();
            }
            checkForSets();
            ui.print("End Of The Computers Turn");
        }
    }

    /**
     * The askforCards method searches through players hand for cards matching
     * the one sought after by opponent. Every 3rd time it lies.
     * @param c Card sought after by opponent
     * @return cards An array of Card objects
     */
    @Override
    public Card[] askforCards(Card c) {
        Card[] cards = new Card[3];
        boolean flag = false;
        int j = 0;
        if (count_deviate < 2) {
            for (int i = hand.size() - 1; i >= 0; i--) {
                if (hand.get(i).equals(c)) {
                    hand.remove(i);
                    cards[j] = c;
                    j++;
                    cards_gotten++;
                    flag = true;
                }
            }
        } else {
            count_deviate = 0;
        }
        if(flag){
            count_deviate++;
        }
        return cards;

    }
}
