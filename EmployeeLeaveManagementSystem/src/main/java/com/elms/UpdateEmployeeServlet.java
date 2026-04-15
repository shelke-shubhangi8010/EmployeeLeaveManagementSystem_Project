package com.elms;



import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/UpdateEmployeeServlet")
public class UpdateEmployeeServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public UpdateEmployeeServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Step 1: Get data from form
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");

        try {
            // Step 2: Load Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Step 3: Create Connection
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/elms", "root", "root");

            // Step 4: SQL Query
            PreparedStatement ps = con.prepareStatement(
                    "UPDATE employees SET name=?, email=? WHERE id=?");

            // Step 5: Set Values
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setInt(3, id);

            // Step 6: Execute
            int result = ps.executeUpdate();

            // Step 7: Redirect
            if (result > 0) {
                response.sendRedirect("ViewEmployeeServlet");
            } else {
                response.getWriter().println("Update Failed!");
            }

            // Step 8: Close connection
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}