package savertest

import saver.Saver
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll

class SaverSpec extends Specification {

	@Unroll("#n items")
	def "Basic test"(){
		given:
		@Subject
		Saver<DummyItem> saver = Mock();
		
		when:
		for(int i= 0; i<n ; i++)
			saver.save(new DummyItem());
		
		then:
		(n) * saver.save(_)
		
		where:
		n << [1,10,100]
		
	}
	
	
}
