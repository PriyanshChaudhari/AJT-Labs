package ajt.Filters;

import ajt.JDBC.jdbcAuth;
import ajt.JDBC.jdbcCRUD;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;

@WebFilter(filterName = "authFilter", servletNames = {"loginHttpSession", "loginCookie"}, urlPatterns = {"/login-http-session", "/login-cookie"})
public class AuthenticationFilter implements Filter {
    jdbcAuth jAuth = new jdbcAuth();
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String username = servletRequest.getParameter("username");
        String password = servletRequest.getParameter("password");
        System.out.println("checking auth in authfilter");
        boolean isEmployee = jAuth.checkEmployee(username, password);
        if(isEmployee){
        System.out.println("auth success at authfilter");
        filterChain.doFilter(servletRequest,servletResponse);
        }else {
            System.out.println("auth failed at authfilter");
            PrintWriter out = servletResponse.getWriter();
            JSONObject json = new JSONObject();
            json.put("status", "error");
            json.put("message", "You can not access searchBook");
            out.print(json.toString());
        }
    }
}
