package ajt.IO;

import ajt.Book.Book;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class jdbcCRUD {
    Connection con = jdbcConnectionProvider.getConnection();
    Statement stmt;
    ResultSet result;


    public void writeToDatabase(Book book) throws SQLException {
        try (PreparedStatement preparedStatement = con.prepareStatement(
                "INSERT INTO Book (bookID, bookName, authorName, publication, dateofPublication, priceofBook) VALUES (?, ?, ?, ?, ?, ?)")) {

            preparedStatement.setInt(1, book.bookID);
            preparedStatement.setString(2, book.bookName);
            preparedStatement.setString(3, book.authorName);
            preparedStatement.setString(4, book.publication);
            preparedStatement.setDate(5, new Date(book.dateofPublication.getTime()));
            preparedStatement.setInt(6, book.priceofBook);

            preparedStatement.executeUpdate();
        }
    }

    public ArrayList<Book> readFromDatabase() throws SQLException {
        ArrayList<Book> booksList = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        try (PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM Book");
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Book obj = new Book();
                obj.bookID = resultSet.getInt("bookID");
                obj.bookName = resultSet.getString("bookName");
                obj.authorName = resultSet.getString("authorName");
                obj.publication = resultSet.getString("publication");
                obj.dateofPublication = resultSet.getDate("dateofPublication");
                obj.priceofBook = resultSet.getInt("priceofBook");

                booksList.add(obj);
            }
        }

        return booksList;
    }

    public ArrayList<Book> searchBook(String searchId) throws SQLException {
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

        return resultList;
    }

    public void deleteBookById(int bookId) throws SQLException {
        try (PreparedStatement preparedStatement = con.prepareStatement("DELETE FROM Book WHERE bookID = ?")) {
            preparedStatement.setInt(1, bookId);
            preparedStatement.executeUpdate();
        }
    }

    public void updateBookDetails(Book updateObj) throws SQLException {
        try {
            // Start a transaction
            con.setAutoCommit(false);

            // Delete the existing record based on book ID
            deleteBookById(updateObj.bookID);

            // Insert the updated details as a new record
            writeToDatabase(updateObj);

            // Commit the transaction
            con.commit();
        } catch (SQLException e) {
            // Rollback the transaction in case of an exception
            con.rollback();
            throw e;
        } finally {
            // Reset the auto-commit to true
            con.setAutoCommit(true);
        }
    }
}
