package content;

import java.util.ArrayList;

public class Module implements Item {

	ArrayList<Attribute> attributes;

	public Module(){
		attributes = new ArrayList<Attribute>();
	}
	
	@Override
	public void addAttribute(Attribute a) {
		attributes.add(a);
	}
	
	@Override
	public Selected getSelected() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Attribute> getAttributes() {
		return attributes;
	}

}
