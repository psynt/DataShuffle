package cards;

import java.io.Serializable;
import java.util.ArrayList;

import model.Data;

/**
 * @author Zane
 *
 */

public class CardState implements Serializable{
	
	private static final long serialVersionUID = 4051449391098461176L;
	private Data savedData;

	public CardState(){
	}
	
	/**
	 * @param newData The Data storing the state of the program to be saved.
	 */
	public void setData(Data newData) {
		savedData = newData;
	}
	
	/**
	 * @return The Data storing the state of the program to be loaded.
	 */
	public Data getData(){
		return savedData;
	}
	
}
