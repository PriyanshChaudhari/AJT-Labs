package ajt.assign05;

import ajt.Book.Book;
import ajt.IO.jdbcCRUD;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "searchBook", value = "/search-book")
public class SearchBookServlet extends HttpServlet {
    jdbcCRUD jCRUD = new jdbcCRUD();

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        String searchID = req.getParameter("bookId");
        try {
            ArrayList<Book> resultList = jCRUD.searchBook(searchID);
            JSONArray jsonArray = new JSONArray();
            System.out.println("searchID: " + searchID);
            for (Book book : resultList) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("bookID", book.bookID);
                jsonObject.put("bookName", book.bookName);
                jsonObject.put("authorName", book.authorName);
                jsonObject.put("publication", book.publication);
                jsonObject.put("dateofPublication", book.dateofPublication.toString());
                jsonObject.put("priceofBook", book.priceofBook);
                jsonObject.put("totalQty", book.totalQty);
                jsonObject.put("totalCost", book.totalCost);
                jsonArray.put(jsonObject);
            }
            resp.getWriter().write(jsonArray.toString());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
