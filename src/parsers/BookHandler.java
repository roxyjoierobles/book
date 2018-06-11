package parsers;

import author.Author;
import books.Book;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
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
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
        try {
            DocumentBuilderFactory docFact = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFact.newDocumentBuilder();
            Document doc = docBuilder.parse(uri);
            NodeList list = doc.getElementsByTagName("book");
            Node b = list.item(0); // first book in XML file - book we need
            NodeList children = b.getChildNodes();
            String title = children.item(1).getNodeValue();
            book.setTitle(title);
        } catch (Exception e) {
            e.printStackTrace();
        }
        builder.setLength(0);
    }
}

