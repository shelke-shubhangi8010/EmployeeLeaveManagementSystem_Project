package com.elms;



import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.*;

@WebServlet("/LeaveServlet")
public class LeaveServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String name = request.getParameter("name");

        String type = request.getParameter("type");
        String from = request.getParameter("from");
        String to = request.getParameter("to");
        String reason = request.getParameter("reason");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/elms", "root", "root");

            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO leave_requests(name,type,from_date,to_date,reason,status) VALUES(?,?,?,?,?, 'Pending')");

            ps.setString(1, name);
            ps.setString(2, type);
            ps.setString(3, from);
            ps.setString(4, to);
            ps.setString(5, reason);

            ps.executeUpdate();

            response.sendRedirect("employeeDashboard.html");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
