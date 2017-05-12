package contenttest

import content.Attribute
import content.Item
import content.Selected
import spock.lang.Specification
import spock.lang.Subject
/**
 * Created by nichita on 15.12.2016.
 */
class ItemSpec extends Specification {

    def "Test Item"(){
        given:
        @Subject
        def it = new Item(Selected.Yes);
        def att=new Attribute("Foo","Bar",Selected.Yes)

        when:
        it.addAttribute(att);

        then:
        it.size() == 1
        it.get(att.key) == att.value
        it.getSelected() == Selected.Yes

    }

    def "Test Item Multi-insert"(){
        given:
        @Subject
        def it = new Item(Selected.Yes);
        def att=[new Attribute("Foo","Bar",Selected.Yes),
                 new Attribute("Foo2","More bar",Selected.Maybe),
                 new Attribute("Foo3","Even more bar",Selected.No)]

        when:
        it.addAttributes(att)

        then:
        it.getAttributes().size() == 3
        it.getAttributes().each {it in att }
        it.getSelected() == Selected.Yes

    }

}