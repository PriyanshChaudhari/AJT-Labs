package ajt.Filters;

import ajt.JDBC.jdbcAuth;
import ajt.JDBC.jdbcCRUD;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;

import java.io.IOException;
import java.io.PrintWriter;

@WebFilter(filterName = "authFilter", servletNames = {"loginHttpSession"}, urlPatterns = "/login-http-session")
public class AuthenticationFilter implements Filter {
    jdbcAuth jAuth = new jdbcAuth();
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String username = servletRequest.getParameter("username");
        String password = servletRequest.getParameter("password");
        System.out.println("checking auth in authfilter");
        boolean isEmployee = jAuth.checkEmployee(username, password);
        if(!isEmployee){
            PrintWriter out = servletResponse.getWriter();
            out.println("<html><body>");
            out.println("<h1>" + "You can not access searchBook " + "</h1>");
            out.println("</body></html>");
        }
        System.out.println("auth succes at authfilter");
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
