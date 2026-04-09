package com.elms;



import java.io.*;
import java.sql.*;
import com.elms.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ApproveServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse res)
        throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("id"));
        String action = req.getParameter("action");

        try {
            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(
              "UPDATE leaves SET status=? WHERE id=?"
            );

            ps.setString(1, action);
            ps.setInt(2, id);

            ps.executeUpdate();

            res.sendRedirect("admin/manageLeaves.jsp");

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}