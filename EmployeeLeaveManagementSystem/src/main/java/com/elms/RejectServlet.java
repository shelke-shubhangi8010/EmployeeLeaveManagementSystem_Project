package com.elms;



import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.*;

@WebServlet("/RejectServlet")
public class RejectServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/elms", "root", "root");

            PreparedStatement ps = con.prepareStatement(
                "UPDATE leave_requests SET status='Rejected' WHERE id=?");

            ps.setInt(1, id);
            ps.executeUpdate();

            response.sendRedirect("ViewLeaveServlet");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}