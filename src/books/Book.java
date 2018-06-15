package books;

import author.Author;

import java.util.List;

public class Book {
    public String title;
    public Author author;
    public List<Author> additionalAuthors;
    public String isbn;
    public String isbn13;
    private String publicationYear;
    private String publicationDay;
    private String publicationMonth;
    public String publicationDate;
    public String publisher;

    public Double avgRating;
    public String description;
    public String imgURL;
    public String goodreadsLink;
    public Genre genre;
    public List<String> bookshelves;

    public Integer ratingsCount;
    public Integer ratingsSum;

    public Integer dist5;
    public Integer dist4;
    public Integer dist3;
    public Integer dist2;
    public Integer dist1;

    // default constructor
    public Book() {
    }

    // constructor with given title,
    public Book(String title) {
        this.title = title;
    }

    // constructor with given title, author
    public Book(String title, Author author) {
        this.title = title;
        this.author = author;
    }

    // constructor with given title, isbn
    public Book(String title, String isbn) {
        this.title = title;
        this.isbn = isbn;
    }

    // constructor with given title, author, genre
    public Book(String title, Author author, Genre genre) {
        this.title = title;
        this.author = author;
        this.genre = genre;
    }

    // constructor with given title, author, isbn and rating
    public Book(String title, Author author, String isbn, Genre genre) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.genre = genre;
    }

    // returns title of book
    public String getTitle() {
        return this.title;
    }

    // return author of book
    public Author getAuthor() {
        return this.author;
    }

    // returns additional authors
    public List<Author> getAdditionalAuthors() {
        return this.additionalAuthors;
    }

    // returns isbn
    public String getISBN() {
        return this.isbn;
    }

    // returns isbn13
    public String getISBN13() {
        return this.isbn13;
    }

    // returns publication year
    public String getPublicationYear() {
        return this.publicationYear;
    }

    // returns publication month
    public String getPublicationMonth() {
        return this.publicationMonth;
    }

    // returns publication day
    public String getPublicationDay() {
        return this.publicationDay;
    }

    // returns publication date
    public String getPublicationDate() {
        return this.publicationDate;
    }

    // returns publisher of book
    public String getPublisher() {
        return this.publisher;
    }

    // returns avg rating
    public Double getAvgRating() {
        return this.avgRating;
    }

    // returns description
    public String getDescription() {
        return this.description;
    }

    // returns img url of cover
    public String getImgURL() {
        return this.imgURL;
    }

    // returns link to goodreads book page
    public String getGoodreadsLink() {
        return this.goodreadsLink;
    }

    // returns number of ratings
    public Integer getRatingsCount() {
        return this.ratingsCount;
    }

    // returns sum of all ratings
    public Integer getRatingsSum() {
        return this.ratingsSum;
    }

    // returns distribution of 5 star ratings
    public Integer getDist5() {
        return this.dist5;
    }

    // returns distribution of 4 star ratings
    public Integer getDist4() {
        return this.dist4;
    }

    // returns distribution of 3 star ratings
    public Integer getDist3() {
        return this.dist3;
    }

    // returns distribution of 2 star ratings
    public Integer getDist2() {
        return this.dist2;
    }

    // returns distribution of 1 star ratings
    public Integer getDist1() {
        return this.dist1;
    }

    // returns genre of book
    public Genre getGenre() {
        return this.genre;
    }

    // returns bookshelves that book is on
    public List<String> getBookshelves() {
        return this.bookshelves;
    }

    // sets title of book to given string
    public void setTitle(String title) {
        this.title = title;
    }

    // sets author of book to given
    public void setAuthor(Author author) {
        this.author = author;
    }

    // sets additional authors to given
    public void setAdditionalAuthors(List<Author> authors) {
        this.additionalAuthors = authors;
    }

    // adds an author to additional authors of book
    public void addAdditionalAuthor(Author author) {
        this.additionalAuthors.add(author);
    }

    // sets isbn
    public void setISBN(String isbn) {
        this.isbn = isbn;
    }

    // sets isbn13
    public void setISBN13(String isbn) {
        this.isbn13 = isbn;
    }

    // sets publication year
    public void setPublicationYear(String year) {
        this.publicationYear = year;
    }

    // sets publication month
    public void setPublicationMonth(String month) {
        this.publicationMonth = month;
    }

    // sets publication day
    public void setPublicationDay(String day) {
        this.publicationDay = day;
    }

    // sets publication date
    public void setPublicationDate(String year, String month, String day) {
        this.publicationDate = year + " " + month + " " + day;
    }

    // sets publisher
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    // sets avg rating
    public void setAvgRating(Double rating) {
        this.avgRating = rating;
    }

    // sets description of book
    public void setDescription(String description) {
        this.description = description;
    }

    // sets img url
    public void setImgURL(String url) {
        this.imgURL = url;
    }

    // sets goodreads book page link
    public void setGoodreadsLink (String link) {
        this.goodreadsLink = link;
    }

    // sets total # of ratings
    public void setRatingsCount(Integer count) {
        this.ratingsCount = count;
    }

    // sets sum of ratings
    public void setRatingsSum(Integer sum) {
        this.ratingsSum = sum;
    }

    // sets distribution of 5 star ratings
    public void setDist5(Integer dist) {
        this.dist5 = dist;
    }

    // sets distribution of 4 star ratings
    public void setDist4(Integer dist) {
        this.dist4 = dist;
    }

    // sets distribution of 3 star ratings
    public void setDist3(Integer dist) {
        this.dist3 = dist;
    }

    // sets distribution of 2 star ratings
    public void setDist2(Integer dist) {
        this.dist2 = dist;
    }

    // sets distribution of 1 star ratings
    public void setDist1(Integer dist) {
        this.dist1 = dist;
    }

    // sets genre of book
    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    // adds bookshelf book is on
    public void addBookshelf(String bookshelf) {
        this.bookshelves.add(bookshelf);
    }

}
