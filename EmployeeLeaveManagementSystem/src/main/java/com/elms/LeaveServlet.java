package com.elms;


import java.io.*;
import java.sql.*;
import com.elms.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LeaveServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
        throws ServletException, IOException {

        String type = req.getParameter("type");
        String from = req.getParameter("from");
        String to = req.getParameter("to");
        String reason = req.getParameter("reason");

        try {
            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(
              "INSERT INTO leaves(emp_id, leave_type, from_date, to_date, reason, status) VALUES (?, ?, ?, ?, ?, 'Pending')"
            );

            ps.setInt(1, 1); // temporary emp id
            ps.setString(2, type);
            ps.setString(3, from);
            ps.setString(4, to);
            ps.setString(5, reason);

            ps.executeUpdate();

            res.sendRedirect("employee/dashboard.jsp");

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}