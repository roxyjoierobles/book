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