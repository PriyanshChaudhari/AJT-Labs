package ajt.assign05;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@WebServlet(name = "currentTime", value = "/current-time")
public class CurrentTimeServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        super.init();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        LocalTime currentTime = LocalTime.now();
        DateTimeFormatter formatTime = DateTimeFormatter.ofPattern("HH:mm:ss");
        String output = currentTime.format(formatTime);

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + "Current time on server is: " + "</h1>");
        out.println("<h1>" + output + "</h1>");
        out.println("</body></html>");
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
