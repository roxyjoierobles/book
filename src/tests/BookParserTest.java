import books.Book;
import org.junit.jupiter.api.BeforeEach;
import parsers.IBookParser;
import parsers.XMLBookParser;

import static org.junit.jupiter.api.Assertions.fail;

public class BookParserTest {
    private static final String FILE = "https://www.goodreads.com/book/title.xml?key=mb40XFLSOsxeK6aQ2Q&title=endersgame";
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

}