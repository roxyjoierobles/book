package parsers;

import books.Book;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class BookHandler extends DefaultHandler {
    private StringBuilder builder;
    private String title;
    private String isbn;
    private String isbn13;
    private String publicationYear;
    private String publicationDay;
    private String publicationMonth;
    private String publicationMonthVal;
    private String publisher;

    private String avgRatingStr;
    private Double avgRating;
    private String description;
    private String img_url;
    private String goodreads_link;

    private String ratingsSumStr;
    private Integer ratingsSum;;
    private String ratingsCountStr;
    private Integer ratingsCount;

    private Integer dist5;
    private String dist5Str;
    private Integer dist4;
    private String dist4Str;
    private Integer dist3;
    private String dist3Str;
    private Integer dist2;
    private String dist2Str;
    private Integer dist1;
    private String dist1Str;

    private Book book;

    private String name;
    private String role;
    private String authorLink;
    private Double authorAvgRating;
    private String authorAvgRatingStr;
    private Integer authorRatingCount;
    private String authorRatingCountStr;


    // counts for book to ensure each book is parse
    private boolean titleCount = false;
    private boolean imgCount = false;
    private boolean yearCount = false;
    private boolean monthCount = false;
    private boolean dayCount = false;
    private boolean publisherCount = false;
    private boolean descriptionCount = false;
    private boolean goodreadsCount = false;
    private boolean avgRatingCount = false;
    private boolean ratingsSumCount = false;
    private boolean ratingsCountCount = false;
    private boolean distCount;

    // for author
    private boolean authorCount = false;
    private boolean nameCount = false;
    private boolean roleCount = false;
    private boolean authorLinkCount = false;
    private boolean authorAvgRatingCount = false;
    private boolean authorRatingCountCount = false;


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
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
        if (qName.equalsIgnoreCase("title") && !titleCount) {
            title = builder.toString().trim();
            book.setTitle(title);
            titleCount = true;
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
        }

        // TODO
        // figure out how to get CDATA - isbns, goodreads url, description, author link

        builder.setLength(0);
    }

    // helper function for month publication
    private void formatMonth(String month) {
        if (month.equals("1")) {
            publicationMonth = "January";
        } else if (month.equals("2")) {
            publicationMonth = "February";
        } else if (month.equals("3")) {
            publicationMonth =  "March";
        } else if (month.equals("4")) {
            publicationMonth = "April";
        } else if (month.equals("5")) {
            publicationMonth = "May";
        } else if (month.equals("6")) {
            publicationMonth = "June";
        } else if (month.equals("7")) {
            publicationMonth = "July";
        } else if (month.equals("8")) {
            publicationMonth = "August";
        } else if (month.equals("9")) {
            publicationMonth = "September";
        } else if (month.equals("10")) {
            publicationMonth = "October";
        } else if (month.equals("11")) {
            publicationMonth = "November";
        } else if (month.equals("12")) {
            publicationMonth = "December";
        }
    }
}

