package com.elms;



import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (username.equals("admin") && password.equals("123")) {

            HttpSession session = request.getSession();
            session.setAttribute("user", username);

            response.sendRedirect("adminDashboard.html");
        }

        else if (username.equals("emp") && password.equals("123")) {

            HttpSession session = request.getSession();
            session.setAttribute("user", username);

            response.sendRedirect("employeeDashboard.html");
        }

        else {
            response.getWriter().println(
                "<h3 style='color:red;text-align:center;'>Invalid Username or Password</h3>"
            );
        }
    }
}