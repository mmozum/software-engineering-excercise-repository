import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Card {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		
		
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(bos);
		
		oos.writeObject(new Card1());
		
		System.out.println(bos.toByteArray().length);
		
		bos.close();

		bos = new ByteArrayOutputStream();
		oos = new ObjectOutputStream(bos);
		
		oos.writeObject(new Card2());
		
		System.out.println(bos.toByteArray().length);
		
		bos.close();

	}

}

class Card1 implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public static final int NUM_UNIQUE_CARDS = 54;

	public enum Rank {
		ACE(1), DEUCE(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(
				9), TEN(10), JACK(11), QUEEN(12), KING(13), JOKERONE(14), JOKERTWO(
				15); // including two jokers

		private final int value; // face value

		Rank(int faceValue) {
			value = faceValue;
		}

		public int value() {
			return value;
		}
	}

	public enum Suit {
		CLUBS, DIAMONDS, HEARTS, SPADES, JOKER
	} // including joker

	private final Rank rank;
	private final Suit suit;

	public Card1() {
		this.rank = null;
		this.suit = null;
	}
	
	private Card1(Rank rank, Suit suit) {
		this.rank = rank;
		this.suit = suit;
	}

	public Rank rank() {
		return rank;
	}

	public Suit suit() {
		return suit;
	}

	public String toString() {
		return rank + " of " + suit;
	}

	// protoCards define different cards, use it when assembling decks
	public static Card1[] protoCards = new Card1[NUM_UNIQUE_CARDS]; // hard
																	// coded
																	// protoCards
	static // Initialize protoCards
	{
		Rank[] ranks = { Rank.ACE, Rank.DEUCE, Rank.THREE, Rank.FOUR,
				Rank.FIVE, Rank.SIX, Rank.SEVEN, Rank.EIGHT, Rank.NINE,
				Rank.TEN, Rank.JACK, Rank.QUEEN, Rank.KING };
		Suit[] suits = { Suit.CLUBS, Suit.DIAMONDS, Suit.HEARTS, Suit.SPADES };
		int index = 0;
		for (Suit suit : suits) {
			for (Rank rank : ranks) {
				protoCards[index++] = new Card1(rank, suit);
			}
		}
		protoCards[index++] = new Card1(Rank.JOKERONE, Suit.JOKER);
		protoCards[index] = new Card1(Rank.JOKERTWO, Suit.JOKER);
	}

	public static final Card1[] getUniqueCards() {
		return protoCards;
	}
}


class Card2 implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public static final int NUM_UNIQUE_CARDS = 54;

	private final int data;

	public Card2() {
		data = 0;
	}
	
	private Card2(byte d) {
		data = d;
	}

	public byte rank() {
		return (byte)(data % 13);
	}

	public byte suit() {
		return (byte)(data / 4);
	}

	public String toString() {
		return "" + data;
	}

	// protoCards define different cards, use it when assembling decks
	public static Card2[] protoCards = new Card2[NUM_UNIQUE_CARDS]; // hard
																	// coded
																	// protoCards
	static // Initialize protoCards
	{

		
		int index = 0;
		
		while(index < 54){
			protoCards[index++] = new Card2((byte)index);
		}
	}

	public static final Card2[] getUniqueCards() {
		return protoCards;
	}
}
