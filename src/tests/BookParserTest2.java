import books.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import parsers.IBookParser;
import parsers.XMLBookParser;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    public void testGetTitle() {
        assertEquals("To All the Boys I've Loved Before (To All the Boys I've Loved Before, #1)", book.getTitle());
    }

    @Test
    public void testGetISBNs() {
        assertEquals(null, book.getISBN());
        assertEquals(null, book.getISBN13());
    }

    @Test
    public void testGetImgURL() {
        assertEquals("https://images.gr-assets.com/books/1372086100m/15749186.jpg", book.getImgURL());
    }


    @Test
    public void testGetPublicationInfo() {
        assertEquals("2015", book.getPublicationYear());
        assertEquals("March", book.getPublicationMonth());
        assertEquals("20", book.getPublicationDay());
        assertEquals("2015 March 20", book.getPublicationDate());
        assertEquals("Penerbit Spring", book.getPublisher());
    }

    @Test
    public void testGetDescription() {
    assertEquals("What if all the crushes you ever had found out how you felt about them… all at once? <br /><br />Sixteen-year-old Lara Jean Song keeps her love letters in a hatbox her mother gave her. They aren’t love letters that anyone else wrote for her; these are ones she’s written. One for every boy she’s ever loved—five in all. When she writes, she pours out her heart and soul and says all the things she would never say in real life, because her letters are for her eyes only. Until the day her secret letters are mailed, and suddenly, Lara Jean’s love life goes from imaginary to out of control.", book.getDescription());    }

    @Test
    public void testGetRatingInfo() {
        assertTrue(4.13 == book.getAvgRating());
        assertTrue(883036 == book.getRatingsSum());
        assertTrue(214006 == book.getRatingsCount());
        assertTrue(93162 == book.getDist5());
        assertTrue(72812 == book.getDist4());
        assertTrue(34407 == book.getDist3());
        assertTrue(9132 == book.getDist2());
        assertTrue(4493 == book.getDist1());
    }

     @Test
     public void testGetGoodreadsURL() {
        assertEquals("https://www.goodreads.com/book/show/15749186-to-all-the-boys-i-ve-loved-before", book.getGoodreadsLink());
     }

/*
    @Test
    public void testGetAuthorInfo() {
        // TODO :need to fix because second author is added instead
   }
    */


   @Test
    public void testGetSimilarBooks() {
       assertEquals(18, book.getSimilarBooks().size());

   }
}