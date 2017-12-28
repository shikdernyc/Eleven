/*
 * AP Java
 * Lab 4.2 Board Game
 * Darren Kong && Asif Shikder
 * February 12th, 2017
*/
import java.util.List;
import java.util.Arrays;
/**
 * The ElevensBoard class represents the board in a game of Elevens.
 */
public class ELevin extends Board {

	/**
	 * The size (number of cards) on the board.
	 */
	private static final int BOARD_SIZE = 9;

	/**
	 * The ranks of the cards for this game to be sent to the deck.
	 */
	private static final String[] RANKS =
		{"ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "jack", "queen", "king"};

	/**
	 * The suits of the cards for this game to be sent to the deck.
	 */
	private static final String[] SUITS =
		{"spades" , "hearts", "diamonds", "clubs"};

	/**
	 * The values of the cards for this game to be sent to the deck.
	 */
	private static final int[] POINT_VALUES =
		{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 0, 0, 0};

	/**
	 * Flag used to control debugging print statements.
	 */
	private static final boolean I_AM_DEBUGGING = true;


	/**
	 * Creates a new <code>ElevensBoard</code> instance.
	 */
	 public ELevin() {
	 	super(BOARD_SIZE, RANKS, SUITS, POINT_VALUES);
	 }

	/**
	 * Determines if the selected cards form a valid group for removal.
	 * In Elevens, the legal groups are (1) a pair of non-face cards
	 * whose values add to 11, and (2) a group of three cards consisting of
	 * a jack, a queen, and a king in some order.
	 * @param selectedCards the list of the indices of the selected cards.
	 * @return true if the selected cards form a valid group for removal;
	 *         false otherwise.
	 */
	@Override
	public boolean isLegal(List<Integer> selectedCards) {
		if (!(selectedCards.size() <4))
		{
			return false;
		}
		else
		{
			if(selectedCards.size()==3)
			{
				return containsJQK(selectedCards);
			}
			else
			{
				return containsPairSum11(selectedCards);
			}
		}
		/* *** IMPLEMENTED*/
	}

	/**
	 * Determine if there are any legal plays left on the board.
	 * In Elevens, there is a legal play if the board contains
	 * (1) a pair of non-face cards whose values add to 11, or (2) a group
	 * of three cards consisting of a jack, a queen, and a king in some order.
	 * @return true if there is a legal play left on the board;
	 *         false otherwise.
	 */
	@Override
	public boolean anotherPlayIsPossible() {
		boolean j = false, k = false, q = false;
		for(int i = 0 ; i < size() ; i++)
		{
			if(cardAt(i)==(null))
			//Use "==" b/c ".equals" cannot be used on null
			{
				//Skips the over the item
			}
			else if(cardAt(i).rank().equals("jack"))
			{
				j = true;
			}
			else if(cardAt(i).rank().equals("queen"))
			{
				q = true;
			}
			else if(cardAt(i).rank().equals("king"))
			{
				k = true;
			}
			else
			{
				for (int x = i+1 ; x < size() ; x++)
				{
					if((cardAt(x)==(null)))
					//Use "==" b/c ".equals" cannot be used on null
					{
						//Skips over the item
					}
					/*
					else if(cardAt(x).rank().equals("jack"))
					{
						j = true;
					}
					else if(cardAt(x).rank().equals("queen"))
					{
						q = true;
					}
					else if(cardAt(x).rank().equals("king"))
					{
						k = true;
					}
					IF THIS CODE IS ADDED IN AND IF EITHER J, Q, K ARE AT THE ENDS OF THE BOARD IT DOESN'T WORK
					WHY???
					*/
					else if(containsPairSum11(Arrays.asList(i , x)))
					{
						return true;
					}
				}
			}
		}
		return j&&q&&k;
		/* *** IMPLEMENTED */
	}

	/**
	 * Check for an 11-pair in the selected cards.
	 * @param selectedCards selects a subset of this board.  It is list
	 *                      of indexes into this board that are searched
	 *                      to find an 11-pair.
	 * @return true if the board entries in selectedCards
	 *              contain an 11-pair; false otherwise.
	 */
	private boolean containsPairSum11(List<Integer> selectedCards) {
		if(selectedCards.size() != 2)
		{
			return false;
		}
		else
		{
			return ((cardAt(selectedCards.get(0)).pointValue() + cardAt(selectedCards.get(1)).pointValue()) == 11);
		}
	}

	/**
	 * Check for a JQK in the selected cards.
	 * @param selectedCards selects a subset of this board.  It is list
	 *                      of indexes into this board that are searched
	 *                      to find a JQK group.
	 * @return true if the board entries in selectedCards
	 *              include a jack, a queen, and a king; false otherwise.
	 */
	private boolean containsJQK(List<Integer> selectedCards) {
		boolean j = false, k = false, q = false;
		for(int i : selectedCards) //Checks each condition
		{
			if(cardAt(i).rank().equals("jack"))
			{
				j = true;
			}
			else if(cardAt(i).rank().equals("queen"))
			{
				q = true;
			}
			else if(cardAt(i).rank().equals("king"))
			{
				k = true;
			}
			else
			{
				return false;
			}
		}
		return j&&q&&k;
		/* *** IMPLEMENTED*/
	}
}
