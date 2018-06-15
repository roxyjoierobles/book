import books.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import parsers.IBookParser;
import parsers.XMLBookParser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class BookParserTest {
    private static final String FILE = "https://www.goodreads.com/book/title.xml?key=mb40XFLSOsxeK6aQ2Q&title=dreamology";
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

    @Test
    public void testGetTitle() {
        assertEquals("Dreamology", book.getTitle());
    }

    /** NEED TO WORK ON - CDATA
    @Test
    public void testGetISBNs() {
        assertEquals("006238001", book.getISBN());
        assertEquals("9780062380005", book.getISBN13());
    }
    */

    @Test
    public void testGetImgURL() {
        assertEquals("https://images.gr-assets.com/books/1435962370m/25817310.jpg", book.getImgURL());
    }

    @Test
    public void testGetPublicationInfo() {
        assertEquals("2016", book.getPublicationYear());
        assertEquals("April", book.getPublicationMonth());
        assertEquals("12", book.getPublicationDay());
        assertEquals("2016 April 12", book.getPublicationDate());
        assertEquals("HarperTeen", book.getPublisher());
    }

    /* needs CDATA - to fix
    @Test
    public void testGetDescription() {
    }
    */


    @Test
    public void testGetRatingInfo() {
        assertTrue(3.66 == book.getAvgRating());
        assertTrue(26575 == book.getRatingsSum());
        assertTrue(7262 == book.getRatingsCount());
    }

    /** needs CDATA
     @Test
     public void testGetGoodreadsURL() {
     }
     */


}