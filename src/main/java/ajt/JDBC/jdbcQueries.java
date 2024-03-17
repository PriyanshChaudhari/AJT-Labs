package ajt.JDBC;

public class jdbcQueries {
    public String createTable = "CREATE TABLE Book (bookID INT PRIMARY KEY , bookName VARCHAR(255) NOT NULL , authorName VARCHAR(255) NOT NULL , publication VARCHAR(255) , dateofPublication DATE , priceofBook INT , totalQty INT , totalCost INT)";

    public String changeBookPrice = "UPDATE Book SET priceofBook = "
            + "CASE WHEN priceofBook > 500 THEN ROUND(priceofBook * 1.1, 2) "
            + "ELSE ROUND(priceofBook * 1.05, 2) END";
}
