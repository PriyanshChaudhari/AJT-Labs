import java.io.*;
import java.util.ArrayList;

public class FileIO {
    File f1 = new File("Books.dat");

    public FileIO() throws IOException {

    }

    public void writeToFile(ArrayList<Book> books) throws IOException {
//        FileOutputStream  opFOS = new FileOutputStream(f1);
//        ObjectOutputStream opOOS = new ObjectOutputStream(opFOS);
//        opOOS.writeObject(books);
        try (ObjectOutputStream opOOS = new ObjectOutputStream(new FileOutputStream(f1, true))) {
            opOOS.writeObject(books);
        }
//        opOOS.close();
//        opFOS.close();
    }

    public ArrayList<Book> readFromFile() throws IOException, ClassNotFoundException {
//        FileInputStream ipFIS = new FileInputStream(f1);
//        ObjectInputStream ipOIS = new ObjectInputStream(ipFIS);
////        Single Object Approach
////        Book bo = new Book();
////        bo = (Book) ipOIS.readObject();
//        //ArrayList approach
//        ArrayList<Book> booksList = new ArrayList<>();
//        try {
//            while (true){
//                booksList = (ArrayList<Book>) ipOIS.readObject();
//            }
//        }catch (EOFException e){
//            System.out.println("end of file reached");
//        }
//
//        ipOIS.close();
//        ipFIS.close();
//        return booksList;
        ArrayList<Book> booksList;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f1))) {
                booksList = (ArrayList<Book>) ois.readObject();
        }
        return booksList;
    }
}