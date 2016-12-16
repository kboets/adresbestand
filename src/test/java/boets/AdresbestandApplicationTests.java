package boets;

import boets.adresbestand.web.controller.HomeController;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class AdresbestandApplicationTests {

	//@Autowired
	private HomeController searchController;

	//@Test
	public void contextLoads() {
		assertThat(searchController, notNullValue());

	}

}
