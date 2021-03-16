package controller;

import org.mindrot.jbcrypt.BCrypt;
import utils.DBUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RegistrationServlet extends HttpServlet {
    private String makeHashFromString(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            if (session.getAttribute("userid") != null && !session.getAttribute("userid").equals(0)) {
                System.out.println("already authenticated");
                response.sendRedirect("cabinet");
            }
        }
        response.sendRedirect("registerView.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("Email");
        String password = request.getParameter("Password");
        System.out.println(email + password);
        try {
            ResultSet resultSet = DBUtils.executeQuery("SELECT id FROM site_users WHERE email = ? ", new String[]{email});
            if (resultSet != null && resultSet.next()) {
                int user = resultSet.getInt("id");
                if (user > 0 ) {
                    PrintWriter writer = response.getWriter();
                    writer.println("<h1>");
                    writer.println("The given email address has its own account!");
                    writer.println("</h1>");
                    writer.println("<a href=\"login\" />");
                    return;
                }
            }
            if (email != null && !password.equals("")) {
                String hash = makeHashFromString(password);
                DBUtils.singleQuery("INSERT INTO site_users (email, password) VALUES (?, ?)", new String[]{email, hash});
                HttpSession session = request.getSession();
                session.invalidate();
                response.sendRedirect("login");
            }
        } catch (SQLException exception) {
            System.out.println("SQL Error: " + exception.getMessage());
            exception.printStackTrace();
        }
    }

}
