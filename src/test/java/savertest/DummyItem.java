package savertest;

import content.Attribute;
import content.Item;
import content.Selected;

import java.util.ArrayList;

public class DummyItem extends Item {

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
