package parsers;

public class BookParsingException extends Exception{
    public BookParsingException() {
        super();
    }

    public BookParsingException(String msg) {
        super(msg);
    }
}
