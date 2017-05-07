package cards;

import java.io.Serializable;
import java.util.ArrayList;

import model.Data;

public class CardState implements Serializable{
	
	private static final long serialVersionUID = 4051449391098461176L;
	private Data savedData;

	public CardState(){
	}
	
	public void setData(Data newData) {
		savedData = newData;
	}
	
	public Data getData(){
		return savedData;
	}
	
}
