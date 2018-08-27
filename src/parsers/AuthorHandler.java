package parsers;

import author.Author;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class AuthorHandler extends DefaultHandler {

    private Author author;
    private List<Author> authors = new ArrayList<>();
    private Stack<Author> authorStack = new Stack<>();

    private Stack elemStack = new Stack();

    public AuthorHandler(List<Author> authors) {
        this.authors = authors;
    }

    // helper function
    private Object currElem() {
        return this.elemStack.peek();
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        //builder.append(ch, start, length);
        String val = new String(ch, start, length).trim();
        if (val.length() == 0) {
            return;
        }
    }


    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        this.elemStack.push(qName);
        if (qName.equals("author")) {
            author = new Author();
            this.authorStack.push(author);
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
        //System.out.println(this.elemStack.peek());
        this.elemStack.pop();


        // at end of a book, it will be popped from stack and added to list
        // last book in list is book with title that was inputted
        if (qName.equals("author")) {
            author = this.authorStack.pop();
            this.authors.add(author);
        }
    }
}
