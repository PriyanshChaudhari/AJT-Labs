package Listeners;

import GUI.mainFrame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class DeleteButtonListener implements ActionListener {
    mainFrame mFObj;

    public DeleteButtonListener(mainFrame mF){
        this.mFObj = mF;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int selectedRow = mFObj.searchResult.getSelectedRow();
        if (selectedRow != -1) {
            try {
                deleteData(selectedRow, mFObj);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    public void deleteData(int selectedRow, mainFrame f) throws IOException {
        DefaultTableModel refreshAfterDelete = (DefaultTableModel) f.searchResult.getModel();
        f.fio.deleteBookById((int) f.searchResult.getValueAt(selectedRow, 0));
        refreshAfterDelete.fireTableDataChanged();
        JOptionPane.showMessageDialog(f.mainPanelT3, "Data deleted successfully!");
    }
}
