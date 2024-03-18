package ajt.SessionHandling.Cookies;

import ajt.JDBC.jdbcAuth;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "loginCookie", value = "/login-cookie")
public class LoginServlet extends HttpServlet {
    jdbcAuth jAuth = new jdbcAuth();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        Cookie userCookie = new Cookie("username", username);
        resp.addCookie(userCookie);
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        System.out.println("auth success at loginservlet");
        JSONObject json = new JSONObject();
        json.put("status", "success");
        out.print(json.toString());
    }
}