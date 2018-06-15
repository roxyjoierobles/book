import books.Book;
import parsers.IBookParser;
import parsers.XMLBookParser;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Scanner;

public class officialBookRecommender {
    public static final String KEY = "mb40XFLSOsxeK6aQ2Q";
    public static final String SECRET = "IF5wIib7I0uc3piWd7jje5onlAyPN0Du6jksVlDy7k";
    public static final String URL_BASE = "http://www.goodreads.com/book/title.xml?";

    public static final String URL = URL_BASE + "key=" + KEY + "&title=";
    public static String URL_SOURCE;

    private static Book book;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String book_title;
        System.out.print("Enter book title: ");
        book_title = input.next();
        book_title += input.nextLine();
        System.out.println("book entered: " + book_title);
        try {
            URL_SOURCE = URL + URLEncoder.encode(book_title, "UTF-8");
        } catch (UnsupportedEncodingException uee) {
            uee.printStackTrace();
        }
        System.out.println("url: " + URL_SOURCE);
        try {
            IBookParser parser = new XMLBookParser(URL_SOURCE);
            book = parser.parse();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
