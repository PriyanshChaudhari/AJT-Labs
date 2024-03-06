package RMI.SearchBook;

import Book.Book;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface SearchBook extends Remote {
    public ArrayList<Book> searchBook(String searchId) throws RemoteException;
}
