package RMI.SearchBook;

import Book.Book;
import IO.JDBC.jdbcConnectionProvider;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class SearchBookRemote extends UnicastRemoteObject implements SearchBook {
    Connection con = jdbcConnectionProvider.getConnection();

    protected SearchBookRemote() throws RemoteException {
        super();
    }

    @Override
    public ArrayList<Book> searchBook(String searchId) throws RemoteException {
        try {
            ArrayList<Book> resultList = new ArrayList<>();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            try (PreparedStatement preparedStatement = con.prepareStatement(
                    "SELECT * FROM Book WHERE bookID = ? OR bookName LIKE ? OR authorName LIKE ? OR publication LIKE ?")) {

                preparedStatement.setString(1, searchId);
                preparedStatement.setString(2, "%" + searchId + "%");
                preparedStatement.setString(3, "%" + searchId + "%");
                preparedStatement.setString(4, "%" + searchId + "%");

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        Book obj = new Book();
                        obj.bookID = resultSet.getInt("bookID");
                        obj.bookName = resultSet.getString("bookName");
                        obj.authorName = resultSet.getString("authorName");
                        obj.publication = resultSet.getString("publication");
                        obj.dateofPublication = resultSet.getDate("dateofPublication");
                        obj.priceofBook = resultSet.getInt("priceofBook");
                        resultList.add(obj);
                    }
                }
            }
            System.out.println("Search book function performed using RMI");
            return resultList;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
