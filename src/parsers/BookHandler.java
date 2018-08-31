package parsers;

import author.Author;
import books.Book;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BookHandler extends DefaultHandler {
    private Book book;

    private String dist5;
    private String dist4;
    private String dist3;
    private String dist2;
    private String dist1;
    private String ratingsCount;

    // for duplicates that are in author
    // i.e. imageUrl, link, average_rating, ratings_count
    private boolean inAuthor = false;

    // ran into trouble with book title for book "Trouble from the Start"
    // took title from "series_work" qName, need to ensure this doesnt happen
    private boolean inSeriesWork = false;

    // for some reason the author of inputted book is Libraries
    // the reason it is doing this is because there is "name" in book_links
    private boolean inBookLinks = false;

    private Stack elemStack = new Stack();

    private Stack<Book> bookStack = new Stack<Book>();
    public List<Book> books = new ArrayList<>();

    private Author author;
    private Stack<Author> authorsStack = new Stack<>();

    public BookHandler(Book book) {
        this.book = book;
    }

    // helper function - returns list of books parsed
    public List<Book> getBooks() {
        return this.books;
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
        } else if ("title".equals(currElem()) && !inSeriesWork) {
            book = (Book) this.bookStack.peek();
            book.setTitle(val);
        } else if ("isbn".equals(currElem())) {
            book = (Book) this.bookStack.peek();
            book.setISBN(val);
        } else if ("isbn13".equals(currElem())) {
            book = (Book) this.bookStack.peek();
            book.setISBN13(val);
        } else if ("image_url".equals(currElem()) && !inAuthor) {
            book = (Book) this.bookStack.peek();
            book.setImgURL(val);
        } else if ("publication_year".equals(currElem())) {
            book = (Book) this.bookStack.peek();
            book.setPublicationYear(val);
        } else if ("publication_month".equals(currElem())) {
            book = (Book) this.bookStack.peek();
            String month = formatMonth(val);
            book.setPublicationMonth(month);
        } else if ("publication_day".equals(currElem())) {
            book = (Book) this.bookStack.peek();
            book.setPublicationDay(val);
            book.setPublicationDate(book.getPublicationYear(), book.getPublicationMonth(), book.getPublicationDay());
        } else if ("publisher".equals(currElem())) {
            book = (Book) this.bookStack.peek();
            book.setPublisher(val);
        } else if ("description".equals(currElem())) {
            book = (Book) this.bookStack.peek();
            book.setDescription(val);
        }
        else if ("ratings_sum".equals(currElem()) && !inAuthor) {
            book = (Book) this.bookStack.peek();
            Integer sum = Integer.parseInt(val);
            book.setRatingsSum(sum);
        } else if ("rating_dist".equals(currElem())) {
            book = (Book) this.bookStack.peek();
            // note: val is in format: "5:1781|4:2565|3:2267|2:765|1:206|total:7584"
            String[] dist = val.split(":");
            // dist[0] is "5"
            // note: length - 2 becasue "|{number dist}" is included
            // dist[1] is distribution for 5 stars
            dist5 = dist[1].substring(0, dist[1].length() - 2);
            // dist[2] is 4 star dist
            dist4 = dist[2].substring(0, dist[2].length() - 2);
            // dist[3] is 3 star
            dist3 = dist[3].substring(0, dist[3].length() - 2);
            // dist[4] is 2 star
            dist2 = dist[4].substring(0, dist[4].length() - 2);
            // dist[5] is 1 star
            // note: length - 6 because "|total" is included
            dist1 = dist[5].substring(0, dist[5].length() - 6);

            // dist[6] is total ratings count
            ratingsCount = dist[6];

            Integer Dist5 = Integer.parseInt(dist5);
            Integer Dist4 = Integer.parseInt(dist4);
            Integer Dist3 = Integer.parseInt(dist3);
            Integer Dist2 = Integer.parseInt(dist2);
            Integer Dist1 = Integer.parseInt(dist1);
            Integer rateCount = Integer.parseInt(ratingsCount);

            book.setDist5(Dist5);
            book.setDist4(Dist4);
            book.setDist3(Dist3);
            book.setDist2(Dist2);
            book.setDist1(Dist1);
            book.setRatingsCount(rateCount);
        }
        else if ("average_rating".equals(currElem()) && !inAuthor) {
            book = (Book) this.bookStack.peek();
            Double rating = Double.parseDouble(val);
            book.setAvgRating(rating);
        }
        else if ("url".equals(currElem())) {
            book = (Book) this.bookStack.peek();
            book.setGoodreadsLink(val);
        } else if ("name".equals(currElem()) && inAuthor && !inBookLinks) {
            author = (Author) this.authorsStack.peek();
            author.setName(val);
        } else if ("role".equals(currElem())) {
            author = (Author) this.authorsStack.peek();
            author.setRole(val);
        } else if ("average_rating".equals(currElem()) && inAuthor) {
            author = (Author) this.authorsStack.peek();
            Double rating = Double.parseDouble(val);
            author.setRating(rating);
        } else if ("link".equals(currElem()) && inAuthor) {
            author = (Author) this.authorsStack.peek();
            author.setLink(val);
        } else if ("ratings_count".equals(currElem()) && inAuthor) {
            author = (Author) this.authorsStack.peek();
            Integer count = Integer.parseInt(val);
            author.setRatingsCount(count);
        } else if ("small_image_url".equals(currElem()) && inAuthor) {
            author = (Author) this.authorsStack.peek();
            author.setImg(val);
        }
    }


    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        this.elemStack.push(qName);
        if (qName.equals("book")) {
            book = new Book();
            this.bookStack.push(book);
        }
        // doing something similar for author
        // keeps track of # of author lists - # of book == # authors
        // bottom author group is for book with inputted title
        if (qName.equals("author")) {
            author = new Author();
            this.authorsStack.push(author);
            inAuthor = true;
        }
        if (qName.equals("book_link")) {
            inBookLinks = true;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
        //System.out.println(this.elemStack.peek());
        this.elemStack.pop();

        // at end of a book, it will be popped from stack and added to list
        // last book in list is book with title that was inputted
        if (qName.equals("book")) {
            book = (Book) this.bookStack.pop();
            this.books.add(book);
            // System.out.println(book.getTitle());
            //System.out.println("size: " + books.size());
        }

        if (qName.equals("author")) {
            book = this.bookStack.peek();
            author = (Author) this.authorsStack.pop();
            if (author.getRole().equals("")) {
                author.setRole("author");
                book.setAuthor(author);
            } else {
                book.addAdditionalAuthors(author);
            }
            inAuthor = false;
        }

        if (qName.equals("book_link")) {
            inBookLinks = false;
        }

    }

    // helper function for month publication
    public String formatMonth(String month) {
        if (month.equals("1")) {
            return "January";
        } else if (month.equals("2")) {
            return  "February";
        } else if (month.equals("3")) {
            return  "March";
        } else if (month.equals("4")) {
            return  "April";
        } else if (month.equals("5")) {
            return  "May";
        } else if (month.equals("6")) {
            return  "June";
        } else if (month.equals("7")) {
            return  "July";
        } else if (month.equals("8")) {
            return  "August";
        } else if (month.equals("9")) {
            return  "September";
        } else if (month.equals("10")) {
            return  "October";
        } else if (month.equals("11")) {
            return  "November";
        } else if (month.equals("12")) {
            return  "December";
        }
        return "";
    }
}