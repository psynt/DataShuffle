package savertest

import content.Item
import saver.Saver
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll

class SaverSpec extends Specification {

	@Unroll("#n items")
	def "Basic test"(){
		given:
		@Subject
		Saver<Item> saver = Mock();
		
		when:
		for(int i= 0; i<n ; i++)
			saver.save(new Item());
		
		then:
		(n) * saver.save(_)
		
		where:
		n << [1,10,100]
		
	}
	
	
}
