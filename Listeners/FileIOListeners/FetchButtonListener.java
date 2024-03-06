package Listeners.FileIOListeners;

import Book.Book;
import GUI.FileIO.mainFrame;

import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class FetchButtonListener implements ActionListener {
    mainFrame mFObj;

    public FetchButtonListener(mainFrame mF){
        this.mFObj = mF;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            cleardetailsTable(mFObj);
            insertData(mFObj);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void insertData(mainFrame f) throws IOException, ClassNotFoundException {
        ArrayList<Book> booksList = f.fio.readFromFile();
        DefaultTableModel addRowT2 = (DefaultTableModel) f.detailsTable.getModel();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        java.util.Date dateOfPubl;
        String doP;
        for (Book b : booksList) {
            dateOfPubl = b.dateofPublication;
            doP = sdf.format(dateOfPubl.getTime());

            System.out.println(b.bookID + "\t" + b.bookName + "\t" + b.authorName + "\t" + b.publication + "\t" + doP + "\t" + b.priceofBook);

            addRowT2.addRow(new Object[]{b.bookID, b.bookName, b.authorName, b.publication, doP, b.priceofBook});
        }
    }

    public void cleardetailsTable(mainFrame f) {
        DefaultTableModel model = (DefaultTableModel) f.detailsTable.getModel();
        model.setRowCount(0);
    }
}
