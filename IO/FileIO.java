package IO;

import Book.Book;
import java.io.*;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class FileIO {
    File f1 = new File("IO/Books.dat");

    public FileIO() throws IOException {
    }

    public void writeToFile(Book book) throws IOException {
        FileWriter fw = new FileWriter(f1, true);
        BufferedWriter bw = new BufferedWriter(fw);
        try{
            bw.write(book.bookID+",");
            bw.write(book.bookName+",");
            bw.write(book.authorName+",");
            bw.write(book.publication+",");
            bw.write(book.dateofPublication+",");
            bw.write(book.priceofBook+"\n");
        }catch (Exception e){
            System.out.println(e);
        }
        bw.close();
        fw.close();
    }

    public ArrayList<Book> readFromFile() throws IOException, ClassNotFoundException {
        FileReader fr = new FileReader(f1);
        BufferedReader br = new BufferedReader(fr);

        ArrayList<Book> booksList = new ArrayList<>();
        String op;
        String[] strings;
        Date dateofPubl;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        while((op = br.readLine())!=null){
            Book obj = new Book();
            strings = op.split(",");
            obj.bookID = Integer.parseInt(strings[0]);
            obj.bookName = strings[1];
            obj.authorName = strings[2];
            obj.publication = strings[3];
            try {
                dateofPubl = new Date(sdf.parse(strings[4]).getTime());
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            obj.dateofPublication = dateofPubl;
            obj.priceofBook = Integer.parseInt(strings[5]);
            booksList.add(obj);
        }
        return booksList;
    }

    public ArrayList<Book> searchBook(String searchId) throws IOException{
        FileReader fr = new FileReader(f1);
        BufferedReader br = new BufferedReader(fr);
        ArrayList<Book> resultList = new ArrayList<>();

        String line;
        Date dateofPubl;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");
            //String bookId = parts[0].trim();

            if (parts[0].equals(searchId)|| parts[1].contains(searchId) || parts[2].contains(searchId) || parts[3].contains(searchId)) {
                Book obj = new Book();
                obj.bookID = Integer.parseInt(parts[0]);
                obj.bookName = parts[1];
                obj.authorName = parts[2];
                obj.publication = parts[3];
                try {
                    dateofPubl = new Date(sdf.parse(parts[4]).getTime());
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
                obj.dateofPublication = dateofPubl;
                obj.priceofBook = Integer.parseInt(parts[5]);
                resultList.add(obj);
            }
        }
        return resultList;
    }

    public void deleteBookById(int bookId) throws IOException {
        List<String> lines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(f1))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] rowData = line.split(",");
                int currentBookId = Integer.parseInt(rowData[0]);

                // Check if the current row corresponds to the bookId to be deleted
                if (currentBookId != bookId) {
                    lines.add(line);
                }
            }
        }
        FileWriter fw = new FileWriter(f1);
        BufferedWriter bw = new BufferedWriter(fw);
        try{
        for(String line : lines){
            bw.write(line);
            bw.newLine();
        }}catch(Exception e){
            System.out.println(e);
        }
        bw.close();
        fw.close();
    }

    public void updateBookDetails(Book updateObj) throws IOException {
        System.out.println("Book.Book w/ bookId: " + updateObj.bookID + "is to be updated!");
        deleteBookById(updateObj.bookID);
        System.out.println("Book.Book w/ bookId: " + updateObj.bookID + "is deleted!");
        writeToFile(updateObj);
        System.out.println("Book.Book w/ bookId: " + updateObj.bookID + "is entered!");
    }
}