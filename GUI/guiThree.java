package GUI;

import Book.Book;
import IO.FileIO;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.FontUIResource;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class guiThree extends JFrame {
    public JPanel mainPanel;
    public JPanel topPanel;
    public JPanel middlePanel;

    public JLabel title;
    public JLabel labelOne;
    public JTextField searchId;
    public JButton searchButton;
    public JTable searchResult;
    public JScrollPane searchResultScroll;
    public JPanel bottomPanel;
    public JButton deleteButton;
    public JButton updateButton;

    public DefaultTableModel model;
    public DefaultTableModel searchResultModel;

    public JTableHeader searchResultHeader;

    String[] columnNames = {"BookId", "BookName", "AuthorName", "Publication", "DateofPublication", "PriceofBook"};
    String[] columnNamesWithButtons = {"BookId", "BookName", "AuthorName", "Publication", "DateofPublication", "PriceofBook", "Edit", "Delete"};

    FileIO fio = new FileIO();


    public guiThree() throws IOException {

        $$$setupUI$$$();
        setFrame();

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    clearTable();
                    setData();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        searchResult.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = searchResult.getSelectedRow();
                    if (selectedRow != -1) {
                    }
                }
            }
        });


        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = searchResult.getSelectedRow();
                if (selectedRow != -1) {
                    sendUpdateData(selectedRow);
                }
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = searchResult.getSelectedRow();
                if (selectedRow != -1) {
                    try {
                        deleteData(selectedRow);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });
    }


    private void setFrame() {
        setContentPane(mainPanel);
        setSize(800, 450);
        setVisible(true);
    }

    public void clearTable() {
        DefaultTableModel model = (DefaultTableModel) searchResult.getModel();
        model.setRowCount(0);
    }

    public void setData() throws IOException {
        ArrayList<Book> resultList = fio.searchBook(searchId.getText());
        DefaultTableModel addRow = (DefaultTableModel) searchResult.getModel();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String doP = "01/01/2024";
        for (Book opObj : resultList) {
            try {
                doP = sdf.format(opObj.dateofPublication.getTime());
                System.out.println(opObj.bookID + "\t" + opObj.bookName + "\t" + opObj.authorName + "\t" + opObj.publication + "\t" + doP + "\t" + opObj.priceofBook);
                addRow.addRow(new Object[]{opObj.bookID, opObj.bookName, opObj.authorName, opObj.publication, doP, opObj.priceofBook});
                searchResult.setEnabled(true);
            } catch (NullPointerException ex) {
                JOptionPane.showMessageDialog(mainPanel, "No data Found!");
            }
        }

    }

    public void sendUpdateData(int selectedRow) {
        Book updateObj = new Book();
        Date dateofPubl = new Date(Date.parse("01/01/2024"));
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            dateofPubl = new Date(sdf.parse(searchResult.getValueAt(selectedRow, 4).toString()).getTime());
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(mainPanel, "Please enter date in dd/MM/yyyy format!");
        }

        updateObj.bookID = (int) searchResult.getValueAt(selectedRow, 0);
        updateObj.bookName = (String) searchResult.getValueAt(selectedRow, 1);
        updateObj.authorName = (String) searchResult.getValueAt(selectedRow, 2);
        updateObj.publication = (String) searchResult.getValueAt(selectedRow, 3);
        updateObj.dateofPublication = dateofPubl;
        updateObj.priceofBook = (int) searchResult.getValueAt(selectedRow, 5);
        JOptionPane.showMessageDialog(mainPanel, "Data Updated Successfully!");
        try {
            fio.updateBookDetails(updateObj);
            JOptionPane.showMessageDialog(mainPanel, "Data Updated Successfully!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteData(int selectedRow) throws IOException {
        fio.deleteBookById((int) searchResult.getValueAt(selectedRow, 0));
        JOptionPane.showMessageDialog(mainPanel, "Data deleted successfully!");
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        createUIComponents();
        mainPanel = new JPanel();
        mainPanel.setLayout(new FormLayout("fill:d:grow", "center:d:noGrow,top:4dlu:noGrow,center:max(d;4px):noGrow,top:4dlu:noGrow,center:max(d;4px):noGrow,top:4dlu:noGrow,center:d:grow,top:4dlu:noGrow,center:max(d;4px):noGrow"));
        mainPanel.setMinimumSize(new Dimension(800, 450));
        mainPanel.setPreferredSize(new Dimension(800, 450));
        topPanel = new JPanel();
        topPanel.setLayout(new FormLayout("fill:d:grow", "center:d:noGrow"));
        CellConstraints cc = new CellConstraints();
        mainPanel.add(topPanel, cc.xy(1, 1));
        title = new JLabel();
        Font titleFont = this.$$$getFont$$$("Segoe UI", Font.PLAIN, 18, title.getFont());
        if (titleFont != null) title.setFont(titleFont);
        title.setHorizontalAlignment(0);
        title.setHorizontalTextPosition(0);
        title.setText("Search Book");
        topPanel.add(title, cc.xy(1, 1));
        middlePanel = new JPanel();
        middlePanel.setLayout(new FormLayout("fill:d:grow,left:4dlu:noGrow,fill:d:grow", "center:d:noGrow"));
        mainPanel.add(middlePanel, cc.xy(1, 3));
        labelOne = new JLabel();
        labelOne.setPreferredSize(new Dimension(100, 18));
        labelOne.setText("Enter Keyword");
        middlePanel.add(labelOne, cc.xy(1, 1, CellConstraints.CENTER, CellConstraints.DEFAULT));
        searchId = new JTextField();
        searchId.setPreferredSize(new Dimension(300, 30));
        middlePanel.add(searchId, cc.xy(3, 1, CellConstraints.CENTER, CellConstraints.DEFAULT));
        searchButton = new JButton();
        searchButton.setText("Search");
        mainPanel.add(searchButton, cc.xy(1, 5, CellConstraints.CENTER, CellConstraints.DEFAULT));
        searchResultScroll = new JScrollPane();
        mainPanel.add(searchResultScroll, cc.xy(1, 7, CellConstraints.FILL, CellConstraints.FILL));
        searchResultScroll.setViewportView(searchResult);
        bottomPanel = new JPanel();
        bottomPanel.setLayout(new FormLayout("fill:max(d;4px):noGrow,left:4dlu:noGrow,fill:max(d;4px):noGrow", "center:d:grow"));
        mainPanel.add(bottomPanel, cc.xy(1, 9, CellConstraints.CENTER, CellConstraints.DEFAULT));
        deleteButton = new JButton();
        deleteButton.setPreferredSize(new Dimension(100, 30));
        deleteButton.setText("Delete");
        bottomPanel.add(deleteButton, cc.xy(3, 1, CellConstraints.FILL, CellConstraints.DEFAULT));
        updateButton = new JButton();
        updateButton.setPreferredSize(new Dimension(100, 30));
        updateButton.setText("Update");
        bottomPanel.add(updateButton, cc.xy(1, 1));
    }

    /**
     * @noinspection ALL
     */
    private Font $$$getFont$$$(String fontName, int style, int size, Font currentFont) {
        if (currentFont == null) return null;
        String resultName;
        if (fontName == null) {
            resultName = currentFont.getName();
        } else {
            Font testFont = new Font(fontName, Font.PLAIN, 10);
            if (testFont.canDisplay('a') && testFont.canDisplay('1')) {
                resultName = fontName;
            } else {
                resultName = currentFont.getName();
            }
        }
        Font font = new Font(resultName, style >= 0 ? style : currentFont.getStyle(), size >= 0 ? size : currentFont.getSize());
        boolean isMac = System.getProperty("os.name", "").toLowerCase(Locale.ENGLISH).startsWith("mac");
        Font fontWithFallback = isMac ? new Font(font.getFamily(), font.getStyle(), font.getSize()) : new StyleContext().getFont(font.getFamily(), font.getStyle(), font.getSize());
        return fontWithFallback instanceof FontUIResource ? fontWithFallback : new FontUIResource(fontWithFallback);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        model = new DefaultTableModel(columnNames, 0);
        searchResult = new JTable(model);
        searchResult.setEnabled(false);
        searchResultHeader = searchResult.getTableHeader();
        searchResultHeader.setBackground(Color.PINK);
    }
}
