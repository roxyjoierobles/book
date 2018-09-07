import books.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import parsers.IBookParser;
import parsers.XMLBookParser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// TODO - need to work on
public class BookParserTest2 {
    // using test.xml for book - To all the boys i've loved before by jenny han)
    private static final String FILE = "./data/test2.xml";
    private static final double DELTA = 1.0e-8;  // tolerance for testing equality on doubles
    private Book book;

    @BeforeEach
    public void runBefore() {
        IBookParser bookParser = new XMLBookParser(FILE);
        try {
            book = bookParser.parse();
        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception should not have been thrown");
        }
    }

    // TODO - find solution to how to parse title with apostrophes
    @Test
    public void testParse() {
        assertEquals("I Know Why the Caged Bird Sings", book.getTitle());

    }
}