package com.elms;



import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/StatusLoginServlet")
public class StatusLoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if(password.equals("123")) {

            HttpSession session = request.getSession();
            session.setAttribute("user", username);

            response.sendRedirect("EmployeeLeaveStatusServlet");

        } else {
            response.getWriter().println("Invalid Password!");
        }
    }
}