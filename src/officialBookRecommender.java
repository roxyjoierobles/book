import books.Book;
import parsers.IBookParser;
import parsers.XMLBookParser;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;

public class officialBookRecommender {
    public static final String KEY = "mb40XFLSOsxeK6aQ2Q";
    public static final String SECRET = "IF5wIib7I0uc3piWd7jje5onlAyPN0Du6jksVlDy7k";
    public static final String URL_BASE = "https://www.goodreads.com/book/title.xml?";
    public static final String URL = URL_BASE + "key=" + KEY + "&title=";
    public static String URL_SOURCE;
    public static final String goodreadsBadge = "https://s.gr-assets.com/assets/icons/goodreads_icon_50x50-823139ec9dc84278d3863007486ae0ac.png";

    private static Book book;

    // used to sort list of books to recommended by greatest rating -> least
    private static Double[] bookRatings;


    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String book_title;
        System.out.print("Enter book title: ");
        book_title = input.next();
        book_title += input.nextLine();
        //System.out.println("book entered: " + book_title);
        try {
            URL_SOURCE = URL + URLEncoder.encode(book_title, "UTF-8");
            //System.out.println("url: " + URL_SOURCE);
            IBookParser parser = new XMLBookParser(URL_SOURCE);
            book = parser.parse();
            System.out.println("You have read: " + book.getTitle());
            System.out.println("\n So here are some books you should read: ");
            for (Book b : book.getSimilarBooks()) {
                System.out.println(b.getTitle());
                // takes book titles and recursively calls bookhandler for each book in similar
                //TODO - before able to do the following need to parse author so that it can use the right book

                parser = new XMLBookParser(URL + URLEncoder.encode(b.getTitle(), "UTF-8"));
                b = parser.parse();
                System.out.println(b.getTitle() + " " + b.getAvgRating());
            }
            // initializes array of ratings
            bookRatings = new Double[book.getSimilarBooks().size()];
            // puts all the book ratings into array
            for (int i = 0; i < book.getSimilarBooks().size(); i++) {
                bookRatings[i] = book.getAvgRating();
            }

            System.out.println("\n books arranged from least to greatest rating");
            // following sorts list by insertion sort
            for (int j = 1; j < bookRatings.length; j++) {
                Double temp = bookRatings[j];
                int pos = j;
                while (pos > 0 && bookRatings[pos - 1] > temp) {
                    bookRatings[pos] = bookRatings[pos - 1];
                    pos--;
                }
                bookRatings[pos] = temp;
            }

            // still working on the following
            // need to include due to goodreads api terms and conditions
            System.out.println("\n \n \n all data and info is from Goodreads");
            URL goodreadsImgURL = new URL(goodreadsBadge);
            Image goodreadsImg = ImageIO.read(goodreadsImgURL);
        } catch (UnsupportedEncodingException uee) {
            uee.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}