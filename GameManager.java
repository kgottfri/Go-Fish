

public class GameManager
{
	private UserInterface ui;
	private Player user,computer;
	private Deck deck;
	
	public GameManager()
	{
		ui = new UserInterface();
		deck = new Deck();
		
		user = new HumanPlayer(ui);
		
		String [] com_type_options = {"simple","devious","advanced"};
		
		String type_of_com = ui.prompt("What type of computer do you wish to play against? (simple, advanced, or devious)",com_type_options);
		
		if(type_of_com.equals(com_type_options[0]))
		{
			computer = new SimpleCom(this,ui);
		}
		else if(type_of_com.equals(com_type_options[1]))
		{
			computer = new DeviousCom(this,ui);
		}
		else if(type_of_com.equals(com_type_options[2]))
		{
			computer = new AdvancedCom(this,ui);
		}
		
		for(int i=0;i<num_starting_cards;i++)
		{
			user.addCard(deck.deal());
			computer.addCard(deck.deal());
		}
		
		user.checkForSets();
		computer.checkForSets();
		
		boolean player_turn = true;
		boolean succeful_turn;
		
		while(true)
		{
			if(player_turn)
			{
				succeful_turn = user.takeTurn();
				
				if(!succeful_turn)
				{
					player_turn = false;
				}
			}
			else
			{
				succeful_turn = computer.takeTurn();
				
				if(!succeful_turn)
				{
					player_turn = true;
				}
			}
			
			if(deck.isEmpty() && user.isHandEmpty() && computer.isHandEmpty())
			{
				if(user.getScore()>computer.getScore())
				{
					ui.print("Congradulations Player, you win!");
				}
				else
				{
					ui.print("Sorry Player, you lost.");
				}
				
				break;
			}
		}
	}
	
	public Card drawCard()
	{
		return deck.deal();
	}
}