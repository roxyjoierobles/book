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

    @Test
    public void testGetISBNs() {
        assertEquals("0062380001", book.getISBN());
        assertEquals("9780062380005", book.getISBN13());
    }

    /* NEED TO FIGURE OUT - USES IMG URL FROM AUTHOR
    @Test
    public void testGetImgURL() {
        assertEquals("https://images.gr-assets.com/books/1435962370m/25817310.jpg", book.getImgURL());
    }
    */

    @Test
    public void testGetPublicationInfo() {
        assertEquals("2016", book.getPublicationYear());
        assertEquals("April", book.getPublicationMonth());
        assertEquals("12", book.getPublicationDay());
        assertEquals("2016 April 12", book.getPublicationDate());
        assertEquals("HarperTeen", book.getPublisher());
    }

    @Test
    public void testGetDescription() {
        assertEquals("For as long as Alice can remember, she has dreamed of Max. Together, they have travelled the world and fallen deliriously, hopelessly in love. Max is the boy of her dreams—and only her dreams. Because he doesn’t exist.<br /><br />But when Alice walks into class on her first day at a new school, there he is. Real Max is nothing like Dream Max. He’s stubborn and complicated. And he has a whole life Alice isn’t a part of. Getting to know each other in reality isn’t as perfect as Alice always hoped.<br /><br />Alarmingly, when their dreams start to bleed into their waking hours, the pair realize that they might have to put an end to a lifetime of dreaming about each other. But when you fall in love in your dreams, can reality ever be enough?", book.getDescription());
    }

    @Test
    public void testGetRatingInfo() {
        //assertTrue(3.65 == book.getAvgRating());
        //assertTrue(27702 == book.getRatingsSum());
        assertTrue(7584 == book.getRatingsCount());
        assertTrue(1781 == book.getDist5());
        assertTrue(2565 == book.getDist4());
        assertTrue(2267 == book.getDist3());
        assertTrue(765 == book.getDist2());
        assertTrue(206 == book.getDist1());
    }

     @Test
     public void testGetGoodreadsURL() {
        assertEquals("https://www.goodreads.com/book/show/25817310-dreamology", book.getGoodreadsLink());
     }


     /*
    @Test
    public void testGetAuthorInfo() {
        assertEquals("Lucy Keating", book.getAuthor().getName());
        assertEquals("author", book.getAuthor().getRole());
        assertTrue(3.62 == book.getAuthor().getRating());
    }
    */

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

   @Test
    public void testGetSimilarBooks() {
       //assertTrue(18 == book.getSimilarBooks().size());
   }

}