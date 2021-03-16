package controller;

import utils.DBUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

public class CourseServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("courseName");
        HttpSession session = request.getSession(false);
        String email;
        if (session != null) {
            email = (String) session.getAttribute("email");
            if (!email.equals("")) {
                DBUtils.singleQuery("UPDATE site_users SET course_name = ? WHERE id = ?", new String[]{name, email});
                response.sendRedirect("cabinetView.jsp");
            }
        }
        System.out.println("User not found in system");
    }
}
