package boets.adresbestand.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

/**
 * Created by Asus on 27/01/2017.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class DatabaseUtilServiceTest {

    @Autowired
    private DatabaseUtilService objectUnderTest;

    @Test
    public void createMunicipalityScript_returnsOK(){
        assertThat(objectUnderTest.createMunicipalityScript(), is(equalTo("OK")));
    }

}
