/**
   Kevin Gottfried
   CS 110 Final Project
   4/29/15
   Deck class creates a new potDeck that is used to create to half-decks.
*/
import java.util.Random;
public class Deck 
{
   /** 
   *  Number of cards in standard deck {@value #CARDS_IN_DECK}
   **/
   public final static int CARDS_IN_DECK = 52;

   private int top;
   private int deckSize;
   /** The collection of Cards */
   private Card [] deck;
   /** Current number of Cards in Deck */
   private int ct;
   
   /**
    * Constructs a regular 52-card deck.  Initially, the cards
    * are in a sorted order.  The shuffle() method can be called to
    * randomize the order.  
    */
   public Deck()
   {
      freshDeck();
   }
   /**
    * Create a new collection of 52 cards, in sorted order
    */
   public void freshDeck()
   {
      deck = new Card[CARDS_IN_DECK];
      top = 0;
	        int d = 0;
	        deckSize = 52;
	         
	        // p stands for the suit
	        // i for the card in each suit
	        // as cards are assigned, if one suit fills up, it moves on to the next one
	        for(int p=3;p>=0;p--)
	        {
	            for(int i=1; i<=13; i++)
	            {
	               deck[ct] = new Card(p, i);
                  ct = ct +1;
	            }
	        }     
   
   }
   /** 
     * Remove and return the top Card on the Deck
     * @return A reference to a Card that was top on the Deck
     */
   public Card dealCard()
   {
      ct--;
      return deck[ct];
   }
   /** 
     * Return current number of Cards in Deck
     * @return number of Cards in Deck
     */
   public int cardsRemaining()
   {  
      return ct;
   }
   /** 
     * Randomize the order of Cards in Deck
     */
   public void shuffle()
   {
      int randNum;
      Card temp;
      Random r = new Random();
      for (int i = 0; i < ct; i++)
      {
         randNum = r.nextInt(ct);
         temp = deck[i];
         deck[i]=deck[randNum];
         deck[randNum]=temp;
      }
   }
   /** 
     * Determine if Deck is empty
     * @return true if there are no more cards, false otherwise
     */
   public boolean isEmpty()
   {
      return (cardsRemaining() == 0);
   }

   // returns the topcard of the deck
   public Card getTop()
	{
	   System.out.println(deck[top]);
	   return deck[top];
	}
	     
	// returns the deckSize
	public int getSize()
	{
	   System.out.println(deckSize);
	   return deckSize;
	}
}

