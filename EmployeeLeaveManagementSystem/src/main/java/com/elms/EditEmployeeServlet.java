package com.elms;



import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.*;

@WebServlet("/EditEmployeeServlet")
public class EditEmployeeServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        response.setContentType("text/html");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/elms", "root", "root");

            PreparedStatement ps = con.prepareStatement(
                "SELECT * FROM employees WHERE id=?");

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                response.getWriter().println("<!DOCTYPE html><html><head><title>Edit Employee</title></head>");

                // Background + Center box
                response.getWriter().println("<body style='margin:0;font-family:Arial;background:linear-gradient(135deg,#6a11cb,#2575fc);display:flex;justify-content:center;align-items:center;height:100vh;'>");

                response.getWriter().println("<div style='background:white;padding:40px;border-radius:15px;width:350px;box-shadow:0 10px 25px rgba(0,0,0,0.2);'>");

                response.getWriter().println("<h2 style='text-align:center;color:#2575fc;'>Edit Employee</h2>");

                // Form
                response.getWriter().println(
                    "<form action='UpdateEmployeeServlet' method='post'>" +

                    "<input type='hidden' name='id' value='" + id + "'>" +

                    "<label>Name:</label><br>" +
                    "<input type='text' name='name' value='" + rs.getString("name") + "' style='width:100%;padding:10px;margin:10px 0;border-radius:8px;border:1px solid #ccc;'><br>" +

                    "<label>Email:</label><br>" +
                    "<input type='email' name='email' value='" + rs.getString("email") + "' style='width:100%;padding:10px;margin:10px 0;border-radius:8px;border:1px solid #ccc;'><br>" +

                    "<button type='submit' style='width:100%;padding:10px;background:#6a11cb;color:white;border:none;border-radius:8px;'>Update</button>" +

                    "</form>"
                );

                response.getWriter().println("<a href='ViewEmployeeServlet' style='display:block;text-align:center;margin-top:15px;color:#2575fc;'>Back</a>");

                response.getWriter().println("</div></body></html>");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}