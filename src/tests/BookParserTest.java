import books.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import parsers.IBookParser;
import parsers.XMLBookParser;

import static org.junit.jupiter.api.Assertions.*;

public class BookParserTest {
    //private static final String FILE = "https://www.goodreads.com/book/title.xml?key=mb40XFLSOsxeK6aQ2Q&title=dreamology";
    // using test.xml for book - Dreamology by Lucy Keating (to avoid changing review info)
    private static final String FILE = "./data/test.xml";
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


    /** need to find a way to test this because numbers keep changing */
    @Test
    public void testGetRatingInfo() {
        assertTrue(3.66 == book.getAvgRating());
        assertTrue(26798 == book.getRatingsSum());
        assertTrue(7324 == book.getRatingsCount());
        assertTrue(1731 == book.getDist5());
        assertTrue(2485 == book.getDist4());
        assertTrue(2183 == book.getDist3());
        assertTrue(729 == book.getDist2());
        assertTrue(196 == book.getDist1());
    }

    /** needs CDATA
     @Test
     public void testGetGoodreadsURL() {
     }
     */

    @Test
    public void testGetAuthorInfo() {
        assertEquals("Lucy Keating", book.getAuthor().getName());
        assertEquals("author", book.getAuthor().getRole());
        assertTrue(3.62 == book.getAuthor().getRating());
    }

   /*
    @Test
    public void testGetGenres() {
        List<Genre> genres = new ArrayList<>();
        genres.add(Genre.YOUNGADULT);
        genres.add(Genre.CONTEMPORARY);
        genres.add(Genre.ROMANCE);
        genres.add(Genre.FICTION);
        genres.add(Genre.FANTASY);
        genres.add(Genre.MAGICALREALISM);
        genres.add(Genre.SCIFI);
        genres.add(Genre.PARANORMAL);
        assertEquals(genres, book.getGenres());
    }
    */

}