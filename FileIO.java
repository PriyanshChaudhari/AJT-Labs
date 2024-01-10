import java.io.*;
import java.util.ArrayList;

public class FileIO {
    File f1 = new File("Books.dat");

    public FileIO() throws IOException {

    }

    public void writeToFile(Book obj) throws IOException {
        FileOutputStream  opFOS = new FileOutputStream(f1, true);
        //DataOutputStream opDOS = new DataOutputStream(opFOS);
        ObjectOutputStream opOOS = new ObjectOutputStream(opFOS);
        /*opDOS.writeUTF(
                "\n" + obj.bookID
                + "," + obj.bookName
                + "," + obj.authorName
                + "," + obj.publication
                + "," + obj.dateofPublication
                + "," + obj.priceofBook);*/
        opOOS.writeObject(obj);
        opOOS.close();
        opFOS.close();
    }

    public Book readFromFile() throws IOException, ClassNotFoundException {
        FileInputStream ipFIS = new FileInputStream(f1);
        ObjectInputStream ipOIS = new ObjectInputStream(ipFIS);
        Book bo = new Book();
        bo = (Book) ipOIS.readObject();
        //ArrayList approach
        /*ArrayList<Book> booksList = new ArrayList<>();
        boolean cont = true;
        while (cont) {
            try (ObjectInputStream input = new ObjectInputStream(ipFIS)) {
                Book obj = (Book) ipOIS.readObject();
                if (obj != null) {
                    booksList.add(obj);
                } else {
                    cont = false;
                }
            } catch (Exception e) {
                // System.out.println(e.printStackTrace());
            }
        }*/
        /*DataInputStream ipDIS = new DataInputStream(ipFIS);
        Date dateofPubl;
        String str;
        int i = 3;
        while (i>0){
            str = ipDIS.readLine();
            bo.bookID = Integer.parseInt(str.split(",")[0]);
            bo.bookName = str.split(",")[1];
            bo.authorName = str.split(",")[2];
            bo.publication = str.split(",")[3];
//          dateofPubl = new Date(str.split(",")[4]);
//          bo.dateofPublication = dateofPubl;
            bo.priceofBook = Integer.parseInt(ipDIS.readLine().split(",")[5]);
            System.out.println(bo.bookID + "\t" + bo.bookName + "\t"+ bo.authorName + "\t" + bo.publication + "\t" + bo.priceofBook );
            i--;
        }*/
        ipOIS.close();
        ipFIS.close();
        return bo;
    }
}