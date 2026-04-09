package com.elms;


import java.io.*;
import java.sql.*;
import com.elms.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
        throws ServletException, IOException {

        String email = req.getParameter("email");
        String password = req.getParameter("password");

        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(
                "SELECT * FROM users WHERE email=? AND password=?"
            );

            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                String role = rs.getString("role");

                if(role.equals("admin")) {
                    res.sendRedirect("admin/dashboard.jsp");
                } else {
                    res.sendRedirect("employee/dashboard.jsp");
                }
            } else {
                res.getWriter().println("Invalid Login");
            }

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}