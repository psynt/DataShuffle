package cards;

import java.io.Serializable;
import java.util.ArrayList;

public class CardState implements Serializable{
	
	private static final long serialVersionUID = 4051449391098461176L;
	private ArrayList<Deck> allDecks;

	public CardState(){
		allDecks = new ArrayList<Deck>();
	}
	
	public void addDeck(Deck newDeck) {
		allDecks.add(newDeck);
	}
	
	public ArrayList<Deck> getAllDecks(){
		return allDecks;
	}
	
}
