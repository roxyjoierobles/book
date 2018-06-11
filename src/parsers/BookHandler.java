package parsers;

import author.Author;
import books.Book;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class BookHandler extends DefaultHandler {
    private StringBuilder builder;
    private String title;
    private String isbn;
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
        if (qName.toLowerCase().equals("book")) {
            title = "";
            isbn = null;
            avgRating = 0.0;
            description = null;
            img_url = null;
            goodreads_link = null;
            author = null;
            authors = new ArrayList<Author>();
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
        if (qName.toLowerCase().equals("title")) {
            title = builder.toString().trim();
            book.setTitle(title);
        }
        builder.setLength(0);
    }
}

