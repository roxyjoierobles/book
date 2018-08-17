package parsers;


// ORIGINAL METHOD

import author.Author;
import books.Book;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class BookHandler extends DefaultHandler {
    private StringBuilder builder;

    private Book book;
    private String dist5;
    private String dist4;
    private String dist3;
    private String dist2;
    private String dist1;

    // for author
    private Author author;
    private String name;
    private String role;
    private Double authorRating;
    private String authorRatingStr;

    private Stack<Book> bookStack = new Stack<Book>();
    private Stack elemStack = new Stack();
    private List elems = new ArrayList();

    private Stack<Author> authorStack = new Stack<>();


    public List<Book> books = new ArrayList<>();


    public BookHandler(Book book) {
        this.book = book;
        builder = new StringBuilder();
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
            //NEED TO FIGURE OUT HOW TO DEAL WITH CDATA (ISBN, ISBN13, ETC.)
        } else if ("title".equals(currElem())) {
            Book b = (Book) this.bookStack.peek();
            b.setTitle(val);
            //System.out.println(b.getTitle());
        } else if ("isbn".equals(currElem())) {
            Book b = (Book) this.bookStack.peek();
            b.setISBN(val);
        } else if ("isbn13".equals(currElem())) {
            Book b = (Book) this.bookStack.peek();
            b.setISBN13(val);
        } else if ("publication_year".equals(currElem())) {
            Book b = (Book) this.bookStack.peek();
            b.setPublicationYear(val);
        } else if ("publication_month".equals(currElem())) {
            Book b = (Book) this.bookStack.peek();
            String month = formatMonth(val);
            b.setPublicationMonth(month);
        } else if ("publication_day".equals(currElem())) {
            Book b = (Book) this.bookStack.peek();
            b.setPublicationDay(val);
            b.setPublicationDate(b.getPublicationYear(), b.getPublicationMonth(), b.getPublicationDay());
        } else if ("publisher".equals(currElem())) {
            Book b = (Book) this.bookStack.peek();
            b.setPublisher(val);
        } else if ("description".equals(currElem())) {
            Book b = (Book) this.bookStack.peek();
            b.setDescription(val);
        }
        /* NEED TO FIX
        else if ("ratings_sum".equals(currElem())) {
            Book b = (Book) this.bookStack.peek();
            Integer sum = Integer.parseInt(val);
            b.setRatingsSum(sum);
        }
        else if ("ratings_count".equals(currElem())) {
            Book b = (Book) this.bookStack.peek();
            Integer count = Integer.parseInt(val);
            b.setRatingsCount(count);
        }
        */
        else if ("rating_dist".equals(currElem())) {
            Book b = (Book) this.bookStack.peek();
            List<String> dist = Arrays.asList(val.split(":"));
            // 1st string in allDist is 5
            // 2nd string is dist5|4
            dist5 = dist.get(1).substring(0, dist.get(1).length() - 2);
            // 3rd string is dist4|3
            dist4 = dist.get(2).substring(0, dist.get(2).length() - 2);
            // 4th string is dist3|2
            dist3 = dist.get(3).substring(0, dist.get(3).length() - 2);
            // 5th string is dist2|1
            dist2 = dist.get(4).substring(0, dist.get(4).length() - 2);
            // 6th string is dist1|total
            dist1 = dist.get(5).substring(0, dist.get(5).length() - 6);

            Integer Dist5 = Integer.parseInt(dist5);
            Integer Dist4 = Integer.parseInt(dist4);
            Integer Dist3 = Integer.parseInt(dist3);
            Integer Dist2 = Integer.parseInt(dist2);
            Integer Dist1 = Integer.parseInt(dist1);

            b.setDist5(Dist5);
            b.setDist4(Dist4);
            b.setDist3(Dist3);
            b.setDist2(Dist2);
            b.setDist1(Dist1);
        }
        /* NEED TO FIX RATING - GIVES 3.62 INSTEAD OF 3.66 (rating of author give)
        else if ("average_rating".equals(currElem())) {
            Book b = (Book) this.bookStack.peek();
            Double rating = Double.parseDouble(val);
            b.setAvgRating(rating);
        } */
        else if ("url".equals(currElem())) {
            Book b = (Book) this.bookStack.peek();
            b.setGoodreadsLink(val);
        }
    }


    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        this.elemStack.push(qName);
        if (qName.equals("book")) {
            Book b = new Book();
            this.bookStack.push(b);
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
            Book b = this.bookStack.pop();
            this.books.add(b);
            //System.out.println(b.getTitle());
        }
        /*
        if (qName.equalsIgnoreCase("title") && !titleCount) {
            title = builder.toString().trim();
            book.setTitle(title);
            titleCount = true;
        } else if (qName.equalsIgnoreCase("isbn") && !isbnCount) {
            isbn = builder.toString().trim();
            book.setISBN(isbn);
            isbnCount = true;
        } else if (qName.equalsIgnoreCase("isbn13") && !isbn13Count) {
            isbn13 = builder.toString().trim();
            book.setISBN13(isbn13);
            isbn13Count = true;
        } else if (qName.equalsIgnoreCase("image_url") && !imgCount) {
            img_url = builder.toString().trim();
            book.setImgURL(img_url);
            imgCount = true;
        } else if (qName.equalsIgnoreCase("publication_year") && !yearCount) {
            publicationYear = builder.toString().trim();
            book.setPublicationYear(publicationYear);
            yearCount = true;
        } else if (qName.equalsIgnoreCase("publication_month") && !monthCount) {
            publicationMonthVal = builder.toString().trim();
            formatMonth(publicationMonthVal);
            book.setPublicationMonth(publicationMonth);
            monthCount = true;
        } else if (qName.equalsIgnoreCase("publication_day") && !dayCount) {
            publicationDay = builder.toString().trim();
            book.setPublicationDay(publicationDay);
            dayCount = true;
            book.setPublicationDate(publicationYear, publicationMonth, publicationDay);
        } else if (qName.equalsIgnoreCase("publisher") && !publisherCount) {
            publisher = builder.toString().trim();
            book.setPublisher(publisher);
            publisherCount = true;
        } else if (qName.equalsIgnoreCase("description") && !descriptionCount) {
            description = builder.toString().trim();
            book.setDescription(description);
            descriptionCount = true;
        } else if (qName.equalsIgnoreCase("average_rating") && !avgRatingCount) {
            avgRatingStr = builder.toString().trim();
            avgRating = Double.parseDouble(avgRatingStr);
            book.setAvgRating(avgRating);
            avgRatingCount = true;
        } else if (qName.equalsIgnoreCase("ratings_sum") && !ratingsSumCount) {
            ratingsSumStr = builder.toString().trim();
            ratingsSum = Integer.parseInt(ratingsSumStr);
            book.setRatingsSum(ratingsSum);
            ratingsSumCount = true;
        } else if (qName.equalsIgnoreCase("ratings_count") && !ratingsCountCount) {
            ratingsCountStr = builder.toString().trim();
            ratingsCount = Integer.parseInt(ratingsCountStr);
            book.setRatingsCount(ratingsCount);
            ratingsCountCount = true;
        } else if (qName.equalsIgnoreCase("rating_dist") && !distCount) {
            String s = builder.toString().trim();
            List<String> dist = Arrays.asList(s.split(":"));
            // 1st string in allDist is 5
            // 2nd string is dist5|4
            dist5Str = dist.get(1).substring(0, dist.get(1).length() - 2);
            // 3rd string is dist4|3
            dist4Str = dist.get(2).substring(0, dist.get(2).length() - 2);
            // 4th string is dist3|2
            dist3Str = dist.get(3).substring(0, dist.get(3).length() - 2);
            // 5th string is dist2|1
            dist2Str = dist.get(4).substring(0, dist.get(4).length() - 2);
            // 6th string is dist1|total
            dist1Str = dist.get(5).substring(0, dist.get(5).length() - 6);

            dist5 = Integer.parseInt(dist5Str);
            dist4 = Integer.parseInt(dist4Str);
            dist3 = Integer.parseInt(dist3Str);
            dist2 = Integer.parseInt(dist2Str);
            dist1 = Integer.parseInt(dist1Str);

            book.setDist5(dist5);
            book.setDist4(dist4);
            book.setDist3(dist3);
            book.setDist2(dist2);
            book.setDist1(dist1);
            distCount = true;
        } else if (qName.equalsIgnoreCase("url") && !goodreadsCount) {
            goodreads_link = builder.toString().trim();
            book.setGoodreadsLink(goodreads_link);
            goodreadsCount = true;
        } else if (qName.equalsIgnoreCase("name") && !nameCount) {
            name = builder.toString().trim();
            author = new Author(name);
            book.setAuthor(author);
            nameCount = true;
        } else if (qName.equalsIgnoreCase("role") && !roleCount) {
            role = builder.toString().trim();
            if (role.equals("")) {
                author.setRole("author");
            } else {
                author.setRole(role);
            }
            roleCount = true;
        } else if (qName.equalsIgnoreCase("average_rating") && !aRatingCount) {
            authorRatingStr = builder.toString().trim();
            authorRating = Double.parseDouble(authorRatingStr);
            author.setRating(authorRating);
            aRatingCount = true;
        } else if (qName.equalsIgnoreCase("similar_books")) {

        }
        */
        builder.setLength(0);
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
