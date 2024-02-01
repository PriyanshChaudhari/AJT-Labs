package GUI;

import Book.Book;
import IO.*;
import Listeners.*;
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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class mainFrame extends JFrame {
    public JPanel lowPanel;
    public JTabbedPane tabbedPane1;
    public JPanel mainPanelT1;
    public JPanel topPanel;
    public JLabel title;
    public JPanel detailsPanel;
    public JLabel BookId;
    public JLabel BookName;
    public JTextField bookId;
    public JLabel AuthorName;
    public JTextField bookName;
    public JLabel Publication;
    public JTextField authorName;
    public JLabel DateofPublication;
    public JLabel PriceofBook;
    public JTextField publication;
    public JTextField dateofPublication;
    public JTextField priceofBook;
    public JButton submitDetailsButton;
    public JScrollPane tableScroll;
    public JTable detailsTable;
    public JPanel middlePanel;
    public JLabel labelOne;
    public JTextField searchId;
    public JButton searchButton;
    public JScrollPane searchResultScroll;
    public JTable searchResult;
    public JPanel bottomPanel;
    public JButton deleteButton;
    public JButton updateButton;
    public JPanel mainPanelT2;
    public JPanel mainPanelT3;
    public JButton fetchButton;

    public DefaultTableModel model;

    public DefaultTableModel model2;
    public JTableHeader detailsTableHeader;
    public JTableHeader searchResultHeader;

    String[] columnNames = {"BookId", "BookName", "AuthorName", "Publication", "DateofPublication", "PriceofBook"};

    public ArrayList<Book> booksList = new ArrayList<>();

    public FileIO fio = new FileIO();

    Socket clientSocket;
    Socket serverSocket;

    DatagramSocket clientDSocket;
    DatagramSocket serverDSocket;

    public mainFrame(Socket clientSocket, Socket serverSocket) throws IOException {
        this.clientSocket = clientSocket;
        $$$setupUI$$$();
        setFrame();
        SubmitDetailsButtonListener submitDetailsButtonListener = new SubmitDetailsButtonListener(this);
        SearchButtonListener searchButtonListener = new SearchButtonListener(this);
        SearchResultListSelectionListener searchResultListSelectionListener = new SearchResultListSelectionListener(this);
        UpdateButtonListener updateButtonListener = new UpdateButtonListener(this);
        DeleteButtonListener deleteButtonListener = new DeleteButtonListener(this);
        FetchButtonListener fetchButtonListener = new FetchButtonListener(this);

        submitDetailsButton.addActionListener(submitDetailsButtonListener);
        searchButton.addActionListener(searchButtonListener);
        searchResult.getSelectionModel().addListSelectionListener(searchResultListSelectionListener);
        updateButton.addActionListener(updateButtonListener);
        deleteButton.addActionListener(deleteButtonListener);
        fetchButton.addActionListener(fetchButtonListener);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("sending file...");
                TCPClient.sendFile(clientSocket);
                try {
                    System.out.println("receiving file...");
                    TCPServer.receiveFile(serverSocket);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    public mainFrame(DatagramSocket clientDSocket) throws IOException {
        this.clientDSocket = clientDSocket;
        $$$setupUI$$$();
        setFrame();
        SubmitDetailsButtonListener submitDetailsButtonListener = new SubmitDetailsButtonListener(this);
        SearchButtonListener searchButtonListener = new SearchButtonListener(this);
        SearchResultListSelectionListener searchResultListSelectionListener = new SearchResultListSelectionListener(this);
        UpdateButtonListener updateButtonListener = new UpdateButtonListener(this);
        DeleteButtonListener deleteButtonListener = new DeleteButtonListener(this);
        FetchButtonListener fetchButtonListener = new FetchButtonListener(this);

        submitDetailsButton.addActionListener(submitDetailsButtonListener);
        searchButton.addActionListener(searchButtonListener);
        searchResult.getSelectionModel().addListSelectionListener(searchResultListSelectionListener);
        updateButton.addActionListener(updateButtonListener);
        deleteButton.addActionListener(deleteButtonListener);
        fetchButton.addActionListener(fetchButtonListener);

//        addWindowListener(new WindowAdapter() {
//            @Override
//            public void windowClosing(WindowEvent e) {
//
//                try {
//                    System.out.println("sending file...");
//                    InetAddress ia;
//                    ia = InetAddress.getByName("localhost");
//                    int PORT = 4999;
//                    UDPClient.sendFile(clientDSocket, ia, PORT);
//                    System.out.println("receiving file...");
//                    //UDPServer.receiveFile(serverDSocket);
//                } catch (UnknownHostException ex) {
//                    throw new RuntimeException(ex);
//                }
//
//            }
//        });
    }

    private void setFrame() {
        setContentPane(lowPanel);
        setSize(978, 550);
        setVisible(true);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        model = new DefaultTableModel(columnNames, 0);
        model2 = new DefaultTableModel(columnNames, 0);
        detailsTable = new JTable(model);
        detailsTable.setEnabled(false);
        detailsTableHeader = detailsTable.getTableHeader();
        detailsTableHeader.setBackground(Color.PINK);
        searchResult = new JTable(model2);
        searchResult.setEnabled(false);
        searchResultHeader = searchResult.getTableHeader();
        searchResultHeader.setBackground(Color.PINK);
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
        lowPanel = new JPanel();
        lowPanel.setLayout(new FormLayout("fill:d:grow", "center:d:grow"));
        lowPanel.setMinimumSize(new Dimension(800, 550));
        lowPanel.setPreferredSize(new Dimension(800, 550));
        tabbedPane1 = new JTabbedPane();
        CellConstraints cc = new CellConstraints();
        lowPanel.add(tabbedPane1, cc.xy(1, 1));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new FormLayout("fill:d:grow", "center:d:grow"));
        tabbedPane1.addTab("Tab One", panel1);
        mainPanelT1 = new JPanel();
        mainPanelT1.setLayout(new FormLayout("fill:d:grow", "center:d:noGrow,top:4dlu:noGrow,center:max(d;4px):noGrow,top:4dlu:noGrow,center:max(d;4px):noGrow"));
        mainPanelT1.setAutoscrolls(true);
        mainPanelT1.setForeground(new Color(-13882309));
        mainPanelT1.setMinimumSize(new Dimension(800, 450));
        mainPanelT1.setPreferredSize(new Dimension(800, 450));
        panel1.add(mainPanelT1, cc.xy(1, 1));
        topPanel = new JPanel();
        topPanel.setLayout(new FormLayout("fill:d:grow", "center:d:noGrow"));
        mainPanelT1.add(topPanel, cc.xy(1, 1));
        title = new JLabel();
        Font titleFont = this.$$$getFont$$$("Segoe UI", Font.PLAIN, 18, title.getFont());
        if (titleFont != null) title.setFont(titleFont);
        title.setHorizontalAlignment(0);
        title.setHorizontalTextPosition(0);
        title.setText("Book Entry");
        topPanel.add(title, cc.xy(1, 1));
        detailsPanel = new JPanel();
        detailsPanel.setLayout(new FormLayout("fill:max(d;4px):noGrow,left:4dlu:noGrow,fill:d:grow,left:4dlu:noGrow,fill:max(d;4px):noGrow,left:4dlu:noGrow,fill:max(d;4px):noGrow,left:4dlu:noGrow,fill:max(d;4px):noGrow", "center:d:grow,top:4dlu:noGrow,center:max(d;4px):noGrow,top:4dlu:noGrow,center:max(d;4px):noGrow,top:4dlu:noGrow,center:max(d;4px):noGrow,top:4dlu:noGrow,center:max(d;4px):noGrow,top:4dlu:noGrow,center:max(d;4px):noGrow,top:4dlu:noGrow,center:max(d;4px):noGrow"));
        mainPanelT1.add(detailsPanel, cc.xy(1, 3));
        BookId = new JLabel();
        BookId.setHorizontalAlignment(0);
        BookId.setPreferredSize(new Dimension(100, 18));
        BookId.setText("BookId");
        detailsPanel.add(BookId, cc.xy(1, 3));
        BookName = new JLabel();
        BookName.setHorizontalAlignment(0);
        BookName.setPreferredSize(new Dimension(100, 18));
        BookName.setText("BookName");
        detailsPanel.add(BookName, cc.xy(1, 5));
        bookId = new JTextField();
        bookId.setPreferredSize(new Dimension(150, 30));
        detailsPanel.add(bookId, cc.xy(3, 3, CellConstraints.LEFT, CellConstraints.DEFAULT));
        AuthorName = new JLabel();
        AuthorName.setHorizontalAlignment(0);
        AuthorName.setPreferredSize(new Dimension(100, 18));
        AuthorName.setText("Author Name");
        detailsPanel.add(AuthorName, cc.xy(1, 7));
        bookName = new JTextField();
        bookName.setPreferredSize(new Dimension(400, 30));
        detailsPanel.add(bookName, cc.xyw(3, 5, 3, CellConstraints.LEFT, CellConstraints.DEFAULT));
        Publication = new JLabel();
        Publication.setHorizontalAlignment(0);
        Publication.setPreferredSize(new Dimension(100, 18));
        Publication.setText("Publication");
        detailsPanel.add(Publication, cc.xy(1, 9));
        authorName = new JTextField();
        authorName.setPreferredSize(new Dimension(400, 30));
        detailsPanel.add(authorName, cc.xyw(3, 7, 3, CellConstraints.LEFT, CellConstraints.DEFAULT));
        DateofPublication = new JLabel();
        DateofPublication.setHorizontalAlignment(0);
        DateofPublication.setPreferredSize(new Dimension(150, 18));
        DateofPublication.setText("Date of Publication");
        detailsPanel.add(DateofPublication, cc.xy(1, 11));
        PriceofBook = new JLabel();
        PriceofBook.setHorizontalAlignment(0);
        PriceofBook.setPreferredSize(new Dimension(100, 18));
        PriceofBook.setText("Price of Book");
        detailsPanel.add(PriceofBook, cc.xy(1, 13));
        publication = new JTextField();
        publication.setPreferredSize(new Dimension(400, 30));
        detailsPanel.add(publication, cc.xy(3, 9, CellConstraints.LEFT, CellConstraints.DEFAULT));
        dateofPublication = new JTextField();
        dateofPublication.setPreferredSize(new Dimension(150, 30));
        detailsPanel.add(dateofPublication, cc.xy(3, 11, CellConstraints.LEFT, CellConstraints.DEFAULT));
        priceofBook = new JTextField();
        priceofBook.setPreferredSize(new Dimension(150, 30));
        detailsPanel.add(priceofBook, cc.xy(3, 13, CellConstraints.LEFT, CellConstraints.DEFAULT));
        submitDetailsButton = new JButton();
        submitDetailsButton.setText("Submit Details");
        mainPanelT1.add(submitDetailsButton, cc.xy(1, 5, CellConstraints.CENTER, CellConstraints.DEFAULT));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new FormLayout("fill:d:grow", "center:d:grow"));
        tabbedPane1.addTab("Tab Two", panel2);
        mainPanelT2 = new JPanel();
        mainPanelT2.setLayout(new FormLayout("fill:d:grow", "center:max(d;4px):noGrow,top:4dlu:noGrow,center:max(d;4px):noGrow,top:4dlu:noGrow,center:d:grow"));
        mainPanelT2.setMinimumSize(new Dimension(800, 450));
        mainPanelT2.setPreferredSize(new Dimension(800, 450));
        panel2.add(mainPanelT2, cc.xy(1, 1));
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new FormLayout("fill:d:grow", "center:d:noGrow"));
        mainPanelT2.add(panel3, cc.xy(1, 1));
        final JLabel label1 = new JLabel();
        Font label1Font = this.$$$getFont$$$("Segoe UI", Font.PLAIN, 18, label1.getFont());
        if (label1Font != null) label1.setFont(label1Font);
        label1.setHorizontalAlignment(0);
        label1.setHorizontalTextPosition(0);
        label1.setText("Book Details");
        panel3.add(label1, cc.xy(1, 1));
        tableScroll = new JScrollPane();
        tableScroll.setPreferredSize(new Dimension(350, 428));
        mainPanelT2.add(tableScroll, cc.xy(1, 5, CellConstraints.FILL, CellConstraints.CENTER));
        tableScroll.setViewportView(detailsTable);
        fetchButton = new JButton();
        fetchButton.setPreferredSize(new Dimension(100, 30));
        fetchButton.setText("Fetch");
        mainPanelT2.add(fetchButton, cc.xy(1, 3, CellConstraints.LEFT, CellConstraints.DEFAULT));
        final JPanel panel4 = new JPanel();
        panel4.setLayout(new FormLayout("fill:d:grow", "center:d:grow"));
        tabbedPane1.addTab("Tab Three", panel4);
        mainPanelT3 = new JPanel();
        mainPanelT3.setLayout(new FormLayout("fill:d:grow", "center:d:noGrow,top:4dlu:noGrow,center:max(d;4px):noGrow,top:4dlu:noGrow,center:d:grow,top:4dlu:noGrow,center:max(d;4px):noGrow"));
        mainPanelT3.setMinimumSize(new Dimension(800, 450));
        mainPanelT3.setPreferredSize(new Dimension(800, 450));
        panel4.add(mainPanelT3, cc.xy(1, 1));
        final JPanel panel5 = new JPanel();
        panel5.setLayout(new FormLayout("fill:d:grow", "center:d:noGrow"));
        mainPanelT3.add(panel5, cc.xy(1, 1));
        final JLabel label2 = new JLabel();
        Font label2Font = this.$$$getFont$$$("Segoe UI", Font.PLAIN, 18, label2.getFont());
        if (label2Font != null) label2.setFont(label2Font);
        label2.setHorizontalAlignment(0);
        label2.setHorizontalTextPosition(0);
        label2.setText("Search Book");
        panel5.add(label2, cc.xy(1, 1));
        middlePanel = new JPanel();
        middlePanel.setLayout(new FormLayout("fill:d:grow,left:4dlu:noGrow,fill:d:grow,left:4dlu:noGrow,fill:max(d;4px):noGrow,left:36dlu:noGrow", "center:d:noGrow"));
        mainPanelT3.add(middlePanel, cc.xy(1, 3));
        labelOne = new JLabel();
        labelOne.setPreferredSize(new Dimension(100, 18));
        labelOne.setText("Enter Keyword");
        middlePanel.add(labelOne, cc.xy(1, 1, CellConstraints.CENTER, CellConstraints.DEFAULT));
        searchId = new JTextField();
        searchId.setPreferredSize(new Dimension(300, 30));
        middlePanel.add(searchId, cc.xy(3, 1, CellConstraints.CENTER, CellConstraints.DEFAULT));
        searchButton = new JButton();
        searchButton.setPreferredSize(new Dimension(100, 30));
        searchButton.setText("Search");
        middlePanel.add(searchButton, cc.xy(5, 1, CellConstraints.CENTER, CellConstraints.DEFAULT));
        searchResultScroll = new JScrollPane();
        mainPanelT3.add(searchResultScroll, cc.xy(1, 5, CellConstraints.FILL, CellConstraints.FILL));
        searchResultScroll.setViewportView(searchResult);
        bottomPanel = new JPanel();
        bottomPanel.setLayout(new FormLayout("fill:max(d;4px):noGrow,left:4dlu:noGrow,fill:max(d;4px):noGrow", "center:d:grow"));
        mainPanelT3.add(bottomPanel, cc.xy(1, 7, CellConstraints.CENTER, CellConstraints.DEFAULT));
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
        return lowPanel;
    }

}
