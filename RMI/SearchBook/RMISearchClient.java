package RMI.SearchBook;

import Book.Book;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class RMISearchClient {
    public SearchBook stub;
    public RMISearchClient(){
        try {
            this.stub = (SearchBook) Naming.lookup("rmi://localhost:5000/searchBook");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public ArrayList<Book> searchRemote(SearchBook stub, String searchInput) throws RemoteException {
        ArrayList<Book> resultSet= stub.searchBook(searchInput);
        System.out.println("Search book function performed using RMI");

        return  resultSet;
    }

    public static void main(String[] args) {
        RMISearchClient searchClient = new RMISearchClient();
        try {
            ArrayList<Book> resultSet = searchClient.searchRemote(searchClient.stub, "102");
            System.out.println(resultSet);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }
}
