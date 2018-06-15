package parsers;

import author.Author;
import books.Book;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.List;

public class BookHandler extends DefaultHandler {
    private StringBuilder builder;
    private String title;
    private String isbn;
    private String isbn13;
    private String avgRatingStr;
    private Double avgRating;
    private String description;
    private String img_url;
    private String goodreads_link;

    private String ratingsSumStr;
    private Integer ratingsSum;;
    private String ratingsCountStr;
    private Integer ratingsCount;

    private Book book;
    private List<Author> authors;
    private Author author;

    // used to retrieve title of book
    private boolean titleCount = false;

    /*
    // for cdata - isbn
    private boolean cdatai = false;

    // for cdata - isbn13
    private boolean cdatai13 = false;
    */

    public BookHandler(Book book) {
        this.book = book;
        builder = new StringBuilder();
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        builder.append(ch, start, length);
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        /* if (qName.equalsIgnoreCase("isbn")) {
            cdatai = true;
        } else if (qName.equalsIgnoreCase("isbn13")) {
            cdatai13 = true;
        } */
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
        if (titleCount) {
            return;
        } else if (qName.equalsIgnoreCase("title") && !titleCount) {
            title = builder.toString().trim();
            book.setTitle(title);
            titleCount = true;
        }
        /*else if (qName.equalsIgnoreCase("isbn") &&) {
            isbn = builder.toString().trim();
            book.setISBN(isbn);
        } else if (qName.equalsIgnoreCase("isbn13")) {
            isbn13 = builder.toString().trim();
            book.setISBN13(isbn13);
        }
        */
        builder.setLength(0);
    }
}

