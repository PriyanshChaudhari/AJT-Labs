import java.io.*;
import java.util.ArrayList;

public class FileIO {
    File f1 = new File("Books.dat");



    public FileIO() throws IOException {

    }

    public void writeToFile(ArrayList<Book> books) throws IOException {
        FileOutputStream  opFOS = new FileOutputStream(f1, true);
        ObjectOutputStream opOOS = new ObjectOutputStream(opFOS);
        try{
            opOOS.writeObject(books);
        }catch (Exception e){
            System.out.println(e);
        }
//        opOOS.reset();
    }

    public ArrayList<Book> readFromFile() throws IOException, ClassNotFoundException {
        FileInputStream ipFIS = new FileInputStream(f1);
        ObjectInputStream ipOIS = new ObjectInputStream(ipFIS);
        ArrayList<Book> booksList = new ArrayList<>();
        try {
            try {
                while (true) {
                    booksList = (ArrayList<Book>) ipOIS.readObject();
                }
            }catch (StreamCorruptedException e){
                System.out.println("Stream Corrupted Exception");
            }
        }catch (EOFException e){
            System.out.println("end of file reached");
        }
        return booksList;
    }

}