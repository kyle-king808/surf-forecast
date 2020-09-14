package persistence;

import model.Beach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ReaderTest {


    @Test
    void testParseAccountsFile1() {
        try {
            List<Beach> beachList = Reader.readBeaches(new File("./data/testBeachesFile1.txt"));
            Beach beachOne = beachList.get(0);
            assertEquals("makapuu", beachOne.getName());
            assertEquals(80, beachOne.getDirection());


            Beach beachTwo = beachList.get(1);
            assertEquals("sandys", beachTwo.getName());
            assertEquals(100, beachTwo.getDirection());

        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }

    @Test
    void testParseAccountsFile2() {
        try {
            List<Beach> beachList = Reader.readBeaches(new File("./data/testBeachesFile2.txt"));
            Beach beachOne = beachList.get(0);
            assertEquals("wreck", beachOne.getName());
            assertEquals(271, beachOne.getDirection());


            Beach beachTwo = beachList.get(1);
            assertEquals("longbeach", beachTwo.getName());
            assertEquals(265, beachTwo.getDirection());

        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }

    @Test
    void testIOException() {
        try {
            Reader.readBeaches(new File("./path/does/not/exist/testAccount.txt"));
        } catch (IOException e) {
            // expected
        }
    }


}
