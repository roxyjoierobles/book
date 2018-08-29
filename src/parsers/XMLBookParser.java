package parsers;

import books.Book;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

public class XMLBookParser implements IBookParser {
    private String source;
    private XMLReader reader;
    private List<Book> books;
    private List<Book> similar;

    /** constructs parser for data in given source */
    public XMLBookParser(String source) {
        this.source = source;
    }

    @Override
    public Book parse() throws BookParsingException, IOException{
        Book book = new Book();
        /*
        try {
            reader = XMLReaderFactory.createXMLReader();
            BookHandler bh = new BookHandler(book);
            reader.setContentHandler(bh);
            reader.parse(new InputSource(new URL(source).openStream()));
        }catch (SAXException se) {
            BookParsingException bookParsingException = new BookParsingException("SAX parsers Error");
            bookParsingException.initCause(se);
            throw bookParsingException;
        }
        */

        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            BookHandler bh = new BookHandler(book);
            saxParser.parse(source, bh);
            books = bh.getBooks();
            book = books.get(books.size() - 1);
            similar = books.subList(0, books.size() - 1);
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
        //System.out.println("title: "book.getTitle());
        return book;
    }
}