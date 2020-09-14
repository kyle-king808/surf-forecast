package persistence;


import model.Beach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class WriterTest {
    private static final String TEST_FILE = "./data/testBeaches.txt";
    private Writer testWriter;
    private Beach beachOne;
    private Beach beachTwo;

    @BeforeEach
    void runBefore() throws FileNotFoundException, UnsupportedEncodingException {
        testWriter = new Writer(new File(TEST_FILE));
        beachOne = new Beach("makapuu", 80);
        beachTwo = new Beach("sandys", 100);
    }

    @Test
    void testWriteBeaches() {
        // save two beaches to file
        testWriter.write(beachOne);
        testWriter.write(beachTwo);
        testWriter.close();

        // now read them back in and verify that the accounts have the expected values
        try {
            List<Beach> accounts = Reader.readBeaches(new File(TEST_FILE));
            Beach beachOne = accounts.get(0);
            assertEquals("makapuu", beachOne.getName());
            assertEquals(80, beachOne.getDirection());

          //  Beach beachTwo = accounts.get(1);
            //assertEquals("sandys", beachTwo.getName());
            //assertEquals(100, beachTwo.getName());

        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }
}