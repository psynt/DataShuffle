package savertest;

import java.util.ArrayList;

import content.Attribute;
import content.Item;
import content.Selected;

public class DummyItem implements Item {

	@Override
	public Selected getSelected() {
		return Selected.Maybe;
	}

	@Override
	public ArrayList<Attribute> getAttributes() {
		ArrayList<Attribute> a = new ArrayList<Attribute>();
		a.add(new DummyAttribute());
		return a;
	}

}
