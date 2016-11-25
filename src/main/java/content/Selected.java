package content;

/**
 * values that the selected-ness of an Item or Attibute may have
 * @author nichita
 *
 */
public enum Selected {
	Yes,No,Maybe;
	public int value(){
		int r;
		switch(this){
		case Maybe: r=0;
			break;
		case No: 	r=-1;
			break;
		case Yes:	r=1;
			break;
		default:	throw new RuntimeException("Undefined Selected type");
		}
		return r;
	}
	
}
