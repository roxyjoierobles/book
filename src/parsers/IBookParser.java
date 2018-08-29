package parsers;

import books.Book;

import java.io.IOException;

public interface IBookParser {

    /**
     * returns parsed book information
     * throws IOException if there is a problem reading data from file
     * throws BookParsingException if
     *  - data in file does not follow syntax
     *  - URL cannot be formed from given webaddress
     */
    Book parse() throws parsers.BookParsingException, IOException;
}
