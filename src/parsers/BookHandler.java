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

    private boolean titleCount=false;

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
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
        if (titleCount)
            return;
        if (qName.equalsIgnoreCase("title")&&!titleCount) {
            title = builder.toString().trim();
            book.setTitle(title);
            titleCount=true;
        }

        builder.setLength(0);
    }
}

