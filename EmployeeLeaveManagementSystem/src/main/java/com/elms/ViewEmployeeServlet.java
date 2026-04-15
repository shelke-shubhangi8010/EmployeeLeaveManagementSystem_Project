package com.elms;



import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.*;

@WebServlet("/ViewEmployeeServlet")
public class ViewEmployeeServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/elms", "root", "root");

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM employees");

            response.getWriter().println("<!DOCTYPE html><html><body style='font-family:Arial;background:linear-gradient(135deg,#6a11cb,#2575fc);'>");

            response.getWriter().println("<div style='max-width:600px;margin:50px auto;background:white;padding:30px;border-radius:15px;'>");
            response.getWriter().println("<h2 style='text-align:center;color:#2575fc;'>Employee List</h2>");

            response.getWriter().println("<table style='width:100%;border-collapse:collapse;'>");
            response.getWriter().println("<tr style='background:#6a11cb;color:white;'><th>ID</th><th>Name</th><th>Email</th></tr>");

            while (rs.next()) {
                response.getWriter().println("<tr style='text-align:center;border-bottom:1px solid #ccc;'>"+
                    "<td>"+rs.getInt("id")+"</td>"+
                    "<td>"+rs.getString("name")+"</td>"+
                    "<td>"+rs.getString("email")+"</td>"+

                    "<td>"+
                    "<a href='EditEmployeeServlet?id="+rs.getInt("id")+"' style='color:blue;'>Edit</a> | "+
                    "<a href='DeleteEmployeeServlet?id="+rs.getInt("id")+"' style='color:red;'>Delete</a>"+
                    "</td>"+

                    "</tr>");
            }

            response.getWriter().println("</table>");
            response.getWriter().println("<div style='text-align:center;margin-top:20px;'><a href='adminDashboard.html'>Back</a></div>");
            response.getWriter().println("</div></body></html>");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}