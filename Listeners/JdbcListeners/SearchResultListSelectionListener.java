package Listeners.JdbcListeners;

import GUI.JDBC.mainFrame;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class SearchResultListSelectionListener implements ListSelectionListener {
    mainFrame mFObj;

    public SearchResultListSelectionListener(mainFrame mF){
        this.mFObj = mF;
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            int selectedRow = mFObj.searchResult.getSelectedRow();
            if (selectedRow != -1) {
            }
        }
    }
}
