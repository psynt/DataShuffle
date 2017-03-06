package contenttest

import content.Attribute
import content.Selected
import spock.lang.Specification
import spock.lang.Subject
/**
 * Created by nichita on 15.12.2016.
 */
class AttributeSpec extends Specification {
    def "Test that attributes are stored correctly"(){
        given:
        @Subject
        Attribute<String> a = new Attribute<>("Name","foo",Selected.No);

        expect:
        a.name == "Name"
        a.value == "foo"
        Attribute.getSel(a.key) == Selected.No
    }
}