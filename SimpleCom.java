// CS_205 Go Fish



import java.util.*;

/**
 * SimpleCom class
 * Handles simple computer decisions
 * @author kevingottfried
 */
public class SimpleCom extends Player{
    UserInterface ui;
    
    
    public SimpleCom() {

    }
    /**
     * Constructor instantiates local UI object
     */
    SimpleCom(UserInterface ui) {
        this.ui = ui;
    }
    
    /**
     * The takeTurn method generates a random card choice to ask opponent for
     * @param gm GameManager
     * @param p1 Opponent
     */
    public void takeTurn(GameManager gm, Player p1){
		if(hand.isEmpty())
		{
			Card new_card = gm.drawCard();
            addCard(new_card);
		}
		else
		{
			
			//ui.print(hand.toString());
			String [] new_hand = new String[hand.size()];
			Random num = new Random();
			int card;
			
			int hand_size = hand.size();
			card = num.nextInt(hand_size);
			Card rand_card = hand.get(card);
         ui.print("The computer asked for a " + rand_card);
			Card [] opp_hand = p1.askforCards(rand_card);
			if(opp_hand[0] != null){
				for(int i = 0; i < 3; i++){
					if (opp_hand[i] !=null){
						addCard(opp_hand[i]);
					}
				}
				successful_request++;
				
			}
			else{
				Card new_card = gm.drawCard();
				addCard(new_card);
				
			}
			total_request++;
			if(hand.size() > 1)
				sort();
			checkForSets();
			ui.print("End Of The Computers Turn");
		}
    }
    
    
}
