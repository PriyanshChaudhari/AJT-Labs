import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

public class guiTwo extends JFrame {

    public JPanel mainPanel;
    public JPanel topPanel;
    public JLabel title;
    public JTable detailsTable;
    public JScrollPane tableScroll;

    public DefaultTableModel model;
    public JTableHeader detailsTableHeader;

    String[] columnNames = {"BookId", "BookName", "AuthorName", "Publication", "DateofPublication", "PriceofBook"};

    FileIO fio = new FileIO();


    public guiTwo() throws IOException, ClassNotFoundException {
        $$$setupUI$$$();
        setContentPane(mainPanel);
        setSize(800, 450);
        setVisible(true);
        insertData();
    }

    public void insertData() throws IOException, ClassNotFoundException {
        ArrayList<Book> booksList = fio.readFromFile();
        DefaultTableModel addRow = (DefaultTableModel) detailsTable.getModel();
        for (Book b : booksList) {
            System.out.println(b.bookID + "\t" + b.bookName + "\t" + b.authorName + "\t" + b.publication + "\t" + b.dateofPublication + "\t" + b.priceofBook);
            addRow.addRow(new Object[]{b.bookID, b.bookName, b.authorName, b.publication, b.dateofPublication, b.priceofBook});
        }
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
        mainPanel.setLayout(new FormLayout("fill:d:grow", "center:max(d;4px):noGrow,top:4dlu:noGrow,center:d:grow"));
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
        title.setText("Book Details");
        topPanel.add(title, cc.xy(1, 1));
        tableScroll = new JScrollPane();
        mainPanel.add(tableScroll, cc.xy(1, 3, CellConstraints.FILL, CellConstraints.FILL));
        tableScroll.setViewportView(detailsTable);
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
        detailsTable = new JTable(model);
        detailsTable.setEnabled(false);
        detailsTableHeader = detailsTable.getTableHeader();
        detailsTableHeader.setBackground(Color.PINK);
        //detailsTable.setVisible(true);
    }
}
