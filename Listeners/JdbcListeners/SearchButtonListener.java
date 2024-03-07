package Listeners.JdbcListeners;

import Book.Book;
import GUI.JDBC.mainFrame;
import RMI.SearchBook.SearchClient;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class SearchButtonListener implements ActionListener {
    mainFrame mFObj;
    SearchClient searchClient = new SearchClient();

    public SearchButtonListener(mainFrame mF){
        this.mFObj = mF;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        clearSearchResultTable(mFObj);
        try {
            setData(mFObj);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void clearSearchResultTable(mainFrame f) {
        DefaultTableModel model = (DefaultTableModel) f.searchResult.getModel();
        model.setRowCount(0);
    }

    public void setData(mainFrame f) throws SQLException {
//        ArrayList<Book> resultList = f.jCRUD.searchBook(f.searchId.getText());
        ArrayList<Book> resultList = null;
        try {
            resultList = searchClient.searchRemote(searchClient.stub, f.searchId.getText());
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }

        DefaultTableModel addRowT3 = (DefaultTableModel) f.searchResult.getModel();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String doP = "01/01/2024";
        if (resultList == null) {
            JOptionPane.showMessageDialog(f.mainPanelT3, "No data Found!");
        }
        for (Book opObj : resultList) {
            try {
                doP = sdf.format(opObj.dateofPublication.getTime());
                System.out.println(opObj.bookID + "\t" + opObj.bookName + "\t" + opObj.authorName + "\t" + opObj.publication + "\t" + doP + "\t" + opObj.priceofBook);
                addRowT3.addRow(new Object[]{opObj.bookID, opObj.bookName, opObj.authorName, opObj.publication, doP, opObj.priceofBook});
                addRowT3.fireTableDataChanged();
                f.searchResult.setEnabled(true);
            } catch (NullPointerException ex) {
                JOptionPane.showMessageDialog(f.mainPanelT3, "No data Found!");
            }
        }

    }
}
