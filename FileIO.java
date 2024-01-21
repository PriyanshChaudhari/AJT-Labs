import java.io.*;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class FileIO {
    File f1 = new File("Books.dat");



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
        Book obj = new Book();
        ArrayList<Book> booksList = new ArrayList<>();
        String op;
        String[] strings;
        Date dateofPubl;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        while((op = br.readLine())!=null){
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

}