package ajt.SessionHandling.HttpSession;

import ajt.JDBC.jdbcAuth;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "loginHttpSession", value = "/login-http-session")
public class LoginServlet extends HttpServlet {
    jdbcAuth jAuth = new jdbcAuth();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        HttpSession session = req.getSession();
        session.setAttribute("username", username);
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        JSONObject json = new JSONObject();
        System.out.println("auth success at loginservlet");
        json.put("status", "success");
        out.print(json.toString());
    }
}