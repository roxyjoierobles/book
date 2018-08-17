package parsers;

import books.Book;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

public class XMLBookParser implements IBookParser {
    private String source;
    private XMLReader reader;

    /** constructs parser for data in given source */
    public XMLBookParser(String source) {
        this.source = source;
    }

    @Override
    public Book parse() throws BookParsingException, IOException {
        Book book = new Book();
        // list of books parsed --> last will be booked inputed
        List<Book> books = new ArrayList<Book>();
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            BookHandler bh = new BookHandler(book);
            saxParser.parse(source, bh);
            books = bh.getBooks();
            // book is last element in list of books parsed
            book = books.get(books.size() - 1);
            // rest of the books are the similar books of inputted title
            List similar = books.subList(0, books.size() - 1);
            book.setSimilarBooks(similar);
        } catch (ParserConfigurationException pce) {
            BookParsingException bookParsingException = new BookParsingException("parsers Configuration Error");
            bookParsingException.initCause(pce);
            throw bookParsingException;
        } catch (SAXException se) {
            BookParsingException bookParsingException = new BookParsingException("SAX parsers Error");
            bookParsingException.initCause(se);
            throw bookParsingException;
        } catch (MalformedURLException mue) {
            BookParsingException bookParsingException = new BookParsingException("XML Format Error");
            bookParsingException.initCause(mue);
            throw bookParsingException;
        }
        return book;
    }
}
