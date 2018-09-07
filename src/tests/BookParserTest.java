import author.Author;
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
    // to make it easier, instead of 18 similar to parse, there is only 3
    private static final String FILE = "./data/test.xml";
    private static final double DELTA = 1.0e-8;  // tolerance for testing equality on doubles
    private Book book;
    private Author lucyKeating;

    // authors of similar books parsed
    private Author paulaStokes;
    private Author kateKaryusQuinn;
    private Author staceyLee;

    // similar books
    private Book girlAgainstTheUniverse;
    private Book downWithTheShine;
    private Book theSecretOfAHeartNote;

    private List<Book> similar = new ArrayList<>();

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
    public void testParseBook() {
        assertEquals("Dreamology", book.getTitle());
        assertEquals("0062380001", book.getISBN());
        assertEquals("9780062380005", book.getISBN13());
        assertEquals("https://www.goodreads.com/book/show/25817310-dreamology", book.getGoodreadsLink());
        assertEquals("https://images.gr-assets.com/books/1435962370m/25817310.jpg", book.getImgURL());
        assertEquals("2016", book.getPublicationYear());
        assertEquals("April", book.getPublicationMonth());
        assertEquals("12", book.getPublicationDay());
        assertEquals("2016 April 12", book.getPublicationDate());
        assertEquals("HarperTeen", book.getPublisher());
        assertEquals("For as long as Alice can remember, she has dreamed of Max. Together, they have travelled the world and fallen deliriously, hopelessly in love. Max is the boy of her dreams—and only her dreams. Because he doesn’t exist.<br /><br />But when Alice walks into class on her first day at a new school, there he is. Real Max is nothing like Dream Max. He’s stubborn and complicated. And he has a whole life Alice isn’t a part of. Getting to know each other in reality isn’t as perfect as Alice always hoped.<br /><br />Alarmingly, when their dreams start to bleed into their waking hours, the pair realize that they might have to put an end to a lifetime of dreaming about each other. But when you fall in love in your dreams, can reality ever be enough?", book.getDescription());
        assertTrue(book.getAvgRating().equals(3.65));
        assertTrue(book.getRatingsSum().equals(27702));
        assertTrue(book.getRatingsCount().equals(7584));
        assertTrue(book.getDist5().equals(1781));
        assertTrue(book.getDist4().equals(2565));
        assertTrue(book.getDist3().equals(2267));
        assertTrue(book.getDist2().equals(765));
        assertTrue(book.getDist1().equals(206));
        lucyKeating = book.getAuthor();
        assertEquals("Lucy Keating", lucyKeating.getName());
        assertEquals("https://images.gr-assets.com/authors/1425358382p2/9809970.jpg", lucyKeating.getImg());
        assertEquals("https://www.goodreads.com/author/show/9809970.Lucy_Keating", lucyKeating.getLink());
        assertEquals("author", lucyKeating.getRole());
        assertTrue(lucyKeating.getRating().equals(3.62));
        assertTrue(lucyKeating.getRatingsCount().equals(8275));
    }

    @Test
    public void testSimilar() {
        // instead of testing all 18, we only left 3 in xml file
        assertEquals(3, book.getSimilarBooks().size());
        // book 1
        girlAgainstTheUniverse = book.getSimilarBooks().get(0);
        assertEquals("Girl Against the Universe", girlAgainstTheUniverse.getTitle());
        assertEquals("https://www.goodreads.com/book/show/22297294-girl-against-the-universe", girlAgainstTheUniverse.getGoodreadsLink());
        assertEquals("https://images.gr-assets.com/books/1460011581m/22297294.jpg", girlAgainstTheUniverse.getImgURL());
        assertEquals("2016", girlAgainstTheUniverse.getPublicationYear());
        assertEquals("May", girlAgainstTheUniverse.getPublicationMonth());
        assertEquals("17", girlAgainstTheUniverse.getPublicationDay());
        assertEquals("2016 May 17", girlAgainstTheUniverse.getPublicationDate());
        assertTrue(girlAgainstTheUniverse.getAvgRating().equals(4.11));
        assertTrue(girlAgainstTheUniverse.getRatingsCount().equals(3872));
        paulaStokes = girlAgainstTheUniverse.getAuthor();
        assertEquals("Paula Stokes", paulaStokes.getName());
        assertEquals("https://www.goodreads.com/author/show/6549373.Paula_Stokes", paulaStokes.getLink());
        assertEquals("author", paulaStokes.getRole());

        // book 2
        downWithTheShine = book.getSimilarBooks().get(1);
        assertEquals("https://www.goodreads.com/book/show/25647300-down-with-the-shine", downWithTheShine.getGoodreadsLink());
        assertEquals("https://images.gr-assets.com/books/1458745280m/25647300.jpg", downWithTheShine.getImgURL());
        assertEquals("0062356046", downWithTheShine.getISBN());
        assertEquals("9780062356048", downWithTheShine.getISBN13());
        assertEquals("2016", downWithTheShine.getPublicationYear());
        assertEquals("April", downWithTheShine.getPublicationMonth());
        assertEquals("26", downWithTheShine.getPublicationDay());
        assertEquals("2016 April 26", downWithTheShine.getPublicationDate());
        assertTrue(downWithTheShine.getAvgRating().equals(3.52));
        assertTrue(downWithTheShine.getRatingsCount().equals(1132));
        kateKaryusQuinn = downWithTheShine.getAuthor();
        assertEquals("Kate Karyus Quinn", kateKaryusQuinn.getName());
        assertEquals("https://www.goodreads.com/author/show/5218360.Kate_Karyus_Quinn", kateKaryusQuinn.getLink());
        assertEquals("author", kateKaryusQuinn.getRole());

        // book 3
        theSecretOfAHeartNote = book.getSimilarBooks().get(2);
        assertEquals("The Secret of a Heart Note", theSecretOfAHeartNote.getTitle());
        assertEquals("https://www.goodreads.com/book/show/25165389-the-secret-of-a-heart-note", theSecretOfAHeartNote.getGoodreadsLink());
        assertEquals("https://images.gr-assets.com/books/1456424870m/25165389.jpg", theSecretOfAHeartNote.getImgURL());
        assertEquals("2016", theSecretOfAHeartNote.getPublicationYear());
        assertEquals("December", theSecretOfAHeartNote.getPublicationMonth());
        assertEquals("27", theSecretOfAHeartNote.getPublicationDay());
        assertEquals("2016 December 27", theSecretOfAHeartNote.getPublicationDate());
        assertTrue(theSecretOfAHeartNote.getAvgRating().equals(3.89));
        assertTrue(theSecretOfAHeartNote.getRatingsCount().equals(1805));
        staceyLee = theSecretOfAHeartNote.getAuthor();
        assertEquals("Stacey Lee", staceyLee.getName());
        assertEquals("author", staceyLee.getRole());
        assertEquals("https://www.goodreads.com/author/show/7267239.Stacey_Lee", staceyLee.getLink());
    }
}
