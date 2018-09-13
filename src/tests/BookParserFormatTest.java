import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import parsers.BookParsingException;
import parsers.IBookParser;
import parsers.XMLBookParser;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.fail;

// test case given an author - xml file taken from url with author in it
// has entities ie ' in title
// uses 4 similar books to parse instead
public class BookParserFormatTest {
    private static final String FILE = "./data/testBadXMLFormat.xml";
    private static final double DELTA = 1.0e-8;  // tolerance for testing equality on doubles
    private IBookParser parser;

    @BeforeEach
    public void runBefore() {
        parser = new XMLBookParser(FILE);
    }

    @Test
    public void testParse() {
        try {
            parser.parse();
            fail("BookParsingException should have been thrown");
        } catch (BookParsingException bpe) {
            // expected
        } catch (IOException ioe) {
            ioe.printStackTrace();
            fail("\"IOException should not have been thrown");
        }
    }
}