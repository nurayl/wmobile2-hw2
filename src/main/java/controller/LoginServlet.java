package controller;

import org.mindrot.jbcrypt.BCrypt;
import utils.DBUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;


public class LoginServlet extends HttpServlet {

    private boolean passwordHashChecking (String password, String hash) {
        return BCrypt.checkpw(password, hash);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("loginView.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String email = request.getParameter("Email");
        String new_password = request.getParameter("Password");
        if (email != null && new_password != null) {
            try {
                ResultSet resultSet = DBUtils.executeQuery("SELECT * FROM site_users WHERE email = ? ",  email);
                if (resultSet != null && resultSet.next()) {
                    String password = resultSet.getString("password");
                    if (!passwordHashChecking(new_password, password)) {
                        PrintWriter writer = response.getWriter();
                        writer.println("<h1>");
                        writer.println("password is incorrect");
                        writer.println("</h1>");
                        System.out.println("password is incorrect");
                    } else {
                        HttpSession session = request.getSession(false);
                        session.setAttribute("email", resultSet.getString("email"));
                        response.sendRedirect("cabinet");
                    }
                } else {
                    PrintWriter writer = response.getWriter();
                    writer.println("<h1>");
                    writer.println("The user with this email not found in our records");
                    writer.println("</h1>");
                }
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        } {
            PrintWriter writer = response.getWriter();
            writer.println("<h1>");
            writer.println("Email and Password can not be empty!");
            writer.println("</h1>");
        }
    }
}
