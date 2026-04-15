package com.elms;



import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.*;

@WebServlet("/AddEmployeeServlet")
public class AddEmployeeServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        String email = request.getParameter("email");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/elms", "root", "root");

            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO employees(name,email) VALUES(?,?)");

            ps.setString(1, name);
            ps.setString(2, email);

            ps.executeUpdate();

            response.sendRedirect("ViewEmployeeServlet");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}