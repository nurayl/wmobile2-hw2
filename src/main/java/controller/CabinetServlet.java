package controller;

import beans.Course;
import beans.User;
import utils.DBUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CabinetServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        String firstname = "", surname = "", age = "", gender = "", email = "", courseName = "";
        int userid = 0;
        try {
            if (session != null) {
                String userEmail = (String) session.getAttribute("email");
                if (!userEmail.equals("")) {
                    ResultSet resultSet = DBUtils.executeQuery("SELECT * FROM site_users where email = ? ", userEmail);
                    if (resultSet != null) {
                        while (resultSet.next()) {
                            if (resultSet.getString("firstname") != null) {
                                firstname = resultSet.getString("firstname");
                            }
                            if (resultSet.getString("surname") != null) {
                                surname = resultSet.getString("surname");
                            }
                            if (resultSet.getString("age") != null) {
                                age = resultSet.getString("age");
                            }
                            if (resultSet.getString("gender") != null) {
                                gender = resultSet.getString("gender");
                            }
                            if (resultSet.getString("email") != null) {
                                email = resultSet.getString("email");
                            }
                            if (resultSet.getString("course_name") != null) {
                                courseName = resultSet.getString("course_name");
                            }
                            if (resultSet.getString("id") != null) {
                                userid = resultSet.getInt("id");
                            }
                        }
                        User user = new User(firstname, surname, age, gender, courseName, userid);

                        ResultSet coursesSet = DBUtils.executeQuery("SELECT * FROM site_courses");
                        List<Course> courses = new ArrayList<Course>();
                        if (coursesSet != null) {
                            while (coursesSet.next()) {
                                Course course = new Course();
                                course.setName(coursesSet.getString("name"));
                                courses.add(course);
                            }
                        }

                        RequestDispatcher rd = request.getRequestDispatcher("cabinetView.jsp");
                        request.setAttribute("user", user);
                        request.setAttribute("courses", courses);
                        rd.forward(request, response);
                    } else {
                        response.sendRedirect("register");
                    }
                }
            } else {
                PrintWriter writer = response.getWriter();
                writer.println("<h1>Unauthenticated: 401</h1>");
                writer.println("<a href=\"auth\">Login</a>");
            }
        } catch (SQLException exception) {
            System.out.println("SQL Error: " + exception.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            if (session.getAttribute("email") != null) {
                String firstname = request.getParameter("firstname");
                String surname = request.getParameter("surname");
                String age = request.getParameter("age");
                String gender = request.getParameter("gender");
                DBUtils.singleQuery("UPDATE site_users SET firstname = ?, surname = ?, age = ?, gender = ? WHERE email = ? ",
                        new String[]{firstname, surname, age, gender, (String) session.getAttribute("email")});
                response.sendRedirect("cabinet");
            }
        }
    }
}
