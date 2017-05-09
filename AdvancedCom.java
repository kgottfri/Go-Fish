// CS_205 Go Fish

import java.util.LinkedList;
import java.util.Random;

/**
 * AdvancedCom class
 * Handles advanced computer decisions; preforms strategy
 * @author kevingottfried
 */
public class AdvancedCom extends Player {

    private Card last_card;
    UserInterface ui;

    
    public AdvancedCom() {

    }
    /**
     * Constructor instantiates local UI object
     */
    AdvancedCom(UserInterface ui) {

        this.ui = ui;
    }

    /**
     * The takeTurn method executes a simple strategy by always asking
     * for the last card the computer drew from the deck.
     * @param gm GameManager
     * @param p1 Opponent
     */
    public void takeTurn(GameManager gm, Player p1) {
        if (hand.isEmpty()) {
            Card new_card = gm.drawCard();
            addCard(new_card);
            ui.print("End Of The Computers Turn");

        } else {
            Card rand_card;
            Card[] opp_hand;
            //ui.print(hand.toString());
            if (last_card == null) {
                rand_card = randCard();
                ui.print("The computer asked for a " + rand_card);
                opp_hand = p1.askforCards(rand_card);
            } else {
                rand_card = last_card;
                ui.print("The computer asked for a " + rand_card);
                opp_hand = p1.askforCards(rand_card);
            }
            
            if (opp_hand[0] != null) {
                for(int i = 0; i < 3; i++){
                    if (opp_hand[i] !=null){
                        addCard(opp_hand[i]);
                    }
		}
				successful_request++;
            } else {
                Card new_card = gm.drawCard();
                addCard(new_card);
                checkForSets();
                last_card = new_card;
            }
            if (hand.size() > 1) {
                sort();
            }
            checkForSets();
            ui.print("End Of The Computers Turn");
        }
    }
    
    /**
     * randCard return a random card from the computers hand
     * @return card A Card object
     */
    public Card randCard(){
        Random num = new Random();
        int index;
        int hand_size = hand.size();
        index = num.nextInt(hand_size);
        Card card = hand.get(index);
        return card;
    }
    
    /**
     * The askforCards method searches through players hand for cards matching
     * the one sought after by opponent
     * @param c Card sought after by opponent
     * @return cards An array of Card objects
     */
    public Card[] askforCards(Card c){
        Card[] cards = new Card[3];
        int j = 0;
        for(int i = hand.size()-1; i >= 0;i--){
            if(hand.get(i).equals(c)){
                hand.remove(i);
                cards[j] = c;
                j++;
                cards_gotten++;
                if(last_card != null && c.equals(last_card) ){
                    last_card = randCard();
                }
            }
        }
        return cards;
    }
    
    /**
     * The checkForSets method determines if players hand contains 4 of a kind,
     * in which case those cards are removed and the players score is increased
     */
    public void checkForSets() {
        int count = 0;
        boolean flag = false;
        int i = 1;
        LinkedList<Card> edit_hand = new LinkedList<>();
        for (Card c : hand) {      
            for (int j = i; j < hand.size();j++) {
                if (c.equals(hand.get(j))) {count++;}
            }
            i++;
            if (count == 3) {
                flag = true;
                edit_hand.add(c);
                System.out.println("A set of " + c + "s were removed from the hand.");
                score++;
            }
            count = 0;
        }
        if(flag){
            for (Card c : edit_hand) {
                for(int k = 0; k < 4;k++){
                    if(c.equals(last_card) && last_card != null)
                        last_card = randCard();
                    hand.remove(c);
                }  
            }
        }
        
    }
}
