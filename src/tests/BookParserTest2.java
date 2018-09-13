import author.Author;
import books.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import parsers.IBookParser;
import parsers.XMLBookParser;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// test case given an author - xml file taken from url with author in it
// has entities ie ' in title
// uses 4 similar books to parse instead
public class BookParserTest2 {
    private static final String FILE = "./data/test2.xml";
    private static final double DELTA = 1.0e-8;  // tolerance for testing equality on doubles
    private Book book;
    private Author jennyHan;

    // authors of similar books parsed
    private Author paulaStokes;
    private Author mirandaKenneally;
    private Author emeryLord;
    private Author annieCardi;

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
        assertEquals("To All the Boys I've Loved Before (To All the Boys I've Loved Before, #1)", book.getTitle());
        assertEquals("https://www.goodreads.com/book/show/25817310-dreamology", book.getGoodreadsLink());
        assertEquals("https://images.gr-assets.com/books/1372086100m/15749186.jpg", book.getImgURL());
        assertEquals("2014", book.getPublicationYear());
        assertEquals("April", book.getPublicationMonth());
        assertEquals("15", book.getPublicationDay());
        assertEquals("2014 April 15", book.getPublicationDate());
        assertEquals("Simon & Schuster Books for Young Readers", book.getPublisher());
        assertEquals("To All the Boys I’ve Loved Before is the story of Lara Jean, who has never openly admitted her crushes, but instead wrote each boy a letter about how she felt, sealed it, and hid it in a box under her bed. But one day Lara Jean discovers that somehow her secret box of letters has been mailed, causing all her crushes from her past to confront her about the letters: her first kiss, the boy from summer camp, even her sister's ex-boyfriend, Josh. As she learns to deal with her past loves face to face, Lara Jean discovers that something good may come out of these letters after all.", book.getDescription());
        assertTrue(book.getAvgRating().equals(4.13));
        assertTrue(book.getRatingsSum().equals(924939));
        assertTrue(book.getRatingsCount().equals(223877));
        assertTrue(book.getDist5().equals(97753));
        assertTrue(book.getDist4().equals(76382));
        assertTrue(book.getDist3().equals(35755));
        assertTrue(book.getDist2().equals(9394));
        assertTrue(book.getDist1().equals(4593));
        jennyHan = book.getAuthor();
        assertEquals("Jenny Han", jennyHan.getName());
        assertEquals("https://images.gr-assets.com/authors/1492645464p2/151371.jpg\n", jennyHan.getImg());
        assertEquals("https://www.goodreads.com/author/show/151371.Jenny_Han", jennyHan.getLink());
        assertEquals("author", jennyHan.getRole());
        assertTrue(jennyHan.getRating().equals(4.08));
        assertTrue(jennyHan.getRatingsCount().equals(775619));
    }

    @Test
    public void testSimilar() {
        // instead of testing all 18, we only left 3 in xml file
        assertEquals(4, book.getSimilarBooks().size());
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
        mirandaKenneally = downWithTheShine.getAuthor();
        assertEquals("Miranda Kennealyy", mirandaKenneally.getName());
        assertEquals("https://www.goodreads.com/author/show/4506073.Miranda_Kenneally", mirandaKenneally.getLink());
        assertEquals("author", mirandaKenneally.getRole());

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
        emeryLord = theSecretOfAHeartNote.getAuthor();
        assertEquals("Emery Lord", emeryLord.getName());
        assertEquals("author", emeryLord.getRole());
        assertEquals("https://www.goodreads.com/author/show/6553850.Emery_Lord", emeryLord.getLink());

        // book 4
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
        annieCardi = theSecretOfAHeartNote.getAuthor();
        assertEquals("Emery Lord", annieCardi.getName());
        assertEquals("author", annieCardi.getRole());
        assertEquals("https://www.goodreads.com/author/show/5610666.Annie_Cardi\n", annieCardi.getLink());
    }
}
