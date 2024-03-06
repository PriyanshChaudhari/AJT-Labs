package Listeners.FileIOListeners;

import Book.Book;
import GUI.FileIO.mainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class SubmitDetailsButtonListener implements ActionListener {
    mainFrame mFObj;

    public SubmitDetailsButtonListener(mainFrame mF){
        this.mFObj = mF;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        writeData(mFObj);
        resetTextFields(mFObj);
    }

    private void writeData(mainFrame f) {

        Date dateofPubl = new Date(Date.parse("01/01/2024"));
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            dateofPubl = new Date(sdf.parse(f.dateofPublication.getText()).getTime());
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(f.mainPanelT1, "Please enter date in dd/MM/yyyy format!");
        }

        Book ipObj = new Book(Integer.parseInt(f.bookId.getText()),
                f.bookName.getText(),
                f.authorName.getText(),
                f.publication.getText(),
                dateofPubl,
                Integer.parseInt(f.priceofBook.getText()));
        f.booksList.add(ipObj);

        try {
            f.fio.writeToFile(ipObj);
            JOptionPane.showMessageDialog(f.mainPanelT1, "Book Details entered successfully!");
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    private void resetTextFields(mainFrame f) {
        ArrayList<JTextField> fieldList = new ArrayList<>();
        fieldList.add(f.bookId);
        fieldList.add(f.bookName);
        fieldList.add(f.authorName);
        fieldList.add(f.publication);
        fieldList.add(f.dateofPublication);
        fieldList.add(f.priceofBook);
        for (JTextField field : fieldList) {
            field.setText("");
        }
    }

}
