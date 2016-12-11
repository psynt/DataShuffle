package savertest;

import content.Attribute;
import content.Selected;

public class DummyAttribute extends Attribute {

	public DummyAttribute() {
		super("foo", "bar", Selected.Maybe);
	}

}
