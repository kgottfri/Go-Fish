// CS_205 Go Fish

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;


/**
 * HumanPlayer class
 * Handles gameplay for human user
 * @author kevingottfried
 */
public class HumanPlayer extends Player {
    UserInterface ui;
    
    public HumanPlayer(UserInterface ui){
        this.ui = ui; 
    }
    
    /**
     * The takeTurn method displays users hand and prompts for a card to ask
     * the opponent for
     * @param gm GameManager
     * @param p1 opponent
     */
    @Override
    public void takeTurn(GameManager gm, Player p1){
      if(hand.isEmpty()){
         ui.print("Empty Hand, Must Draw");
         Card new_card = gm.drawCard();
         addCard(new_card);
         ui.print("You drew a " + new_card);
      }
	   else {
            int card_num;
            LinkedList<String> hand_options = new LinkedList<>();
            for (int i = 0; i < hand.size(); i++) {
                hand_options.add(hand.get(i).toString());
                switch (hand.get(i).toString()){
                        case "ace":   hand_options.add("1");//?????
                                      break;
                        case "jack":  hand_options.add("11");
                                      break;
                        case "queen": hand_options.add("12");
                                      break;
                        case "king":  hand_options.add("13");
                                      break;
                        
                }
            }
            int hand_size = hand.size();
            //ui.print( "You have " + hand_size + " cards" );
            ui.print("Your Hand: " + hand.toString() );
            String card = ui.prompt( "Ask for a card from the computer: ", hand_options );
            
            //Handle cases for string input of face cards
            if (card.toLowerCase().equals("jack")) {
                card_num = 11;
            } else if (card.toLowerCase().equals("queen")) {
                card_num = 12;
            } else if (card.toLowerCase().equals("king")) {
                card_num = 13;
            } else if (card.toLowerCase().equals("ace")) {
                card_num = 1;
            } else {
                card_num = Integer.parseInt(card);
            }
            
            //Create a card instance of the string input
            Card ask_card = new Card(card_num);
            //An array that holds all of the possible cards taken from the opponent
            Card[] opp_hand = p1.askforCards(ask_card);

            if (opp_hand[0] != null) {
                for (int i = 0; i < 3; i++) {
                    if (opp_hand[i] != null) {
                        addCard(opp_hand[i]);
                    }
                }
                successful_request++;
                System.out.println("Congrats! The computer had a " + ask_card);

            } else {
                Card new_card = gm.drawCard();
                addCard(new_card);
                ui.print("Sorry, the computer doesn't have that card");
                ui.print("You drew a " + new_card);

            }
            total_request++;
            if (hand.size() > 1) {
                sort();
            }
            checkForSets();
        }
    }

}
