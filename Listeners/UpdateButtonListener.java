package Listeners;

import Book.Book;
import GUI.mainFrame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class UpdateButtonListener implements ActionListener {
    mainFrame mFObj;

    public UpdateButtonListener(mainFrame mF){
        this.mFObj = mF;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int selectedRow = mFObj.searchResult.getSelectedRow();
        if (selectedRow != -1) {
            sendUpdateData(selectedRow, mFObj);
        }
    }

    public void sendUpdateData(int selectedRow, mainFrame f) {
        Book updateObj = new Book();
        DefaultTableModel refreshAfterUpdate = (DefaultTableModel) f.searchResult.getModel();
        Date dateofPubl = new Date(Date.parse("01/01/2024"));
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            dateofPubl = new Date(sdf.parse(f.searchResult.getValueAt(selectedRow, 4).toString()).getTime());
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(f.mainPanelT3, "Please enter date in dd/MM/yyyy format!");
        }

        updateObj.bookID = (int) f.searchResult.getValueAt(selectedRow, 0);
        updateObj.bookName = (String) f.searchResult.getValueAt(selectedRow, 1);
        updateObj.authorName = (String) f.searchResult.getValueAt(selectedRow, 2);
        updateObj.publication = (String) f.searchResult.getValueAt(selectedRow, 3);
        updateObj.dateofPublication = dateofPubl;
        updateObj.priceofBook = Integer.parseInt(f.searchResult.getValueAt(selectedRow, 5).toString());
        JOptionPane.showMessageDialog(f.mainPanelT3, "Data Updated Successfully!");
        try {
            f.fio.updateBookDetails(updateObj);
            refreshAfterUpdate.fireTableDataChanged();
            JOptionPane.showMessageDialog(f.mainPanelT3, "Data Updated Successfully!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
