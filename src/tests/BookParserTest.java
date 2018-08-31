import books.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import parsers.IBookParser;
import parsers.XMLBookParser;

import java.util.ArrayList;
import java.util.List;

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

    @Test
    public void testGetDescription() {
        assertEquals("For as long as Alice can remember, she has dreamed of Max. Together, they have travelled the world and fallen deliriously, hopelessly in love. Max is the boy of her dreams—and only her dreams. Because he doesn’t exist.<br /><br />But when Alice walks into class on her first day at a new school, there he is. Real Max is nothing like Dream Max. He’s stubborn and complicated. And he has a whole life Alice isn’t a part of. Getting to know each other in reality isn’t as perfect as Alice always hoped.<br /><br />Alarmingly, when their dreams start to bleed into their waking hours, the pair realize that they might have to put an end to a lifetime of dreaming about each other. But when you fall in love in your dreams, can reality ever be enough?", book.getDescription());
    }

    @Test
    public void testGetRatingInfo() {
        assertTrue(3.65 == book.getAvgRating());
        assertTrue(27702 == book.getRatingsSum());
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


    @Test
    public void testGetAuthorInfo() {
        assertEquals("Lucy Keating", book.getAuthor().getName());
        assertEquals("author", book.getAuthor().getRole());
        assertTrue(3.62 == book.getAuthor().getRating());
        assertEquals("https://www.goodreads.com/author/show/9809970.Lucy_Keating", book.getAuthor().getLink());
        assertTrue(8275 == book.getAuthor().getRatingsCount());
        assertEquals("https://images.gr-assets.com/authors/1425358382p2/9809970.jpg", book.getAuthor().getImg());
    }


   @Test
    public void testGetSimilarBooks() {
       assertEquals(18, book.getSimilarBooks().size());
       // following is to see if all similar books were parsed
       // checking by getting title for all books parsed not including inputted
       // and checking if it has all 18 titles
       List<String> similarTitles = new ArrayList<>();
       List<String> similarTitlesAuthors = new ArrayList<>();

       for (Book b: book.similarBooks) {
           similarTitles.add(b.getTitle());
           similarTitlesAuthors.add(b.getAuthor().getName());
       }
       // tests to see if all titles are parsed
       assertTrue(similarTitles.contains("Girl Against the Universe"));
       assertTrue(similarTitles.contains("Down with the Shine"));
       assertTrue(similarTitles.contains("The Secret of a Heart Note"));
       assertTrue(similarTitles.contains("Wanderlost"));
       assertTrue(similarTitles.contains("The Geek's Guide to Unrequited Love"));
       assertTrue(similarTitles.contains("Suffer Love"));
       assertTrue(similarTitles.contains("Timekeeper (Timekeeper, #1)"));
       assertTrue(similarTitles.contains("Diplomatic Immunity"));
       assertTrue(similarTitles.contains("Trouble from the Start"));
       assertTrue(similarTitles.contains("The Lifeboat Clique"));
       assertTrue(similarTitles.contains("Of Fire and Stars (Of Fire and Stars, #1)"));
       assertTrue(similarTitles.contains("Even if the Sky Falls"));
       assertTrue(similarTitles.contains("A Season for Fireflies"));
       assertTrue(similarTitles.contains("A Drop of Night"));
       assertTrue(similarTitles.contains("I'm Not Your Manic Pixie Dream Girl"));
       assertTrue(similarTitles.contains("The Leaving Season"));
       assertTrue(similarTitles.contains("Beast"));
       assertTrue(similarTitles.contains("Harmony House"));

       // tests to see if all authors were parsed
       assertTrue(similarTitlesAuthors.contains("Paula Stokes"));
       assertTrue(similarTitlesAuthors.contains("Kate Karyus Quinn"));
       assertTrue(similarTitlesAuthors.contains("Stacey Lee"));
       assertTrue(similarTitlesAuthors.contains("Jen Malone"));
       assertTrue(similarTitlesAuthors.contains("Sarvenaz Tash"));
       assertTrue(similarTitlesAuthors.contains("Ashley Herring Blake"));
       assertTrue(similarTitlesAuthors.contains("Tara Sim"));
       assertTrue(similarTitlesAuthors.contains("Brodi Ashton"));
       assertTrue(similarTitlesAuthors.contains("Rachel Hawthorne"));
       assertTrue(similarTitlesAuthors.contains("Kathy Parks"));
       assertTrue(similarTitlesAuthors.contains("Audrey Coulthurst"));
       assertTrue(similarTitlesAuthors.contains("Mia Garcia"));
       assertTrue(similarTitlesAuthors.contains("Rebecca Maizel"));
       assertTrue(similarTitlesAuthors.contains("Stefan Bachmann"));
       assertTrue(similarTitlesAuthors.contains("Gretchen McNeil"));
       assertTrue(similarTitlesAuthors.contains("Cat Jordan"));
       assertTrue(similarTitlesAuthors.contains("Brie Spangler"));
       assertTrue(similarTitlesAuthors.contains("Nic Sheff"));

   }
}