package savertest;

import content.Attribute;
import content.Selected;

public class DummyAttribute implements Attribute {

	@Override
	public Selected getSelected() {
		return Selected.Maybe;
	}

	@Override
	public String getValue() {
		return "foo";
	}

	@Override
	public String getName() {
		return "bar";
	}

}
