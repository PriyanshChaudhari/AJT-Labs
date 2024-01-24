package Book;

import java.io.Serializable;
import java.util.Date;

public class Book implements Serializable {
    public int bookID;
    public String bookName;
    public String authorName;
    public String publication;
    public Date dateofPublication;
    public int priceofBook;
    public int totalQty;
    public int totalCost;

    public Book(){}

    public Book(int bookID, String bookName, String authorName, String publication, Date dateofPublication, int priceofBook){
        this.bookID = bookID;
        this.bookName = bookName;
        this.authorName = authorName;
        this.publication = publication;
        this.dateofPublication = dateofPublication;
        this.priceofBook = priceofBook;
    }


    public Book setBook(Book bookObj){
        return bookObj;
    }

}
