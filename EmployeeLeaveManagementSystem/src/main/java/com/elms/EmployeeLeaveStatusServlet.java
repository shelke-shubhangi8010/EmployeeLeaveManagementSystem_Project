package com.elms;



import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.*;

@WebServlet("/EmployeeLeaveStatusServlet")
public class EmployeeLeaveStatusServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");

        try {
            HttpSession session = request.getSession(false);

            if(session == null || session.getAttribute("user") == null){
                response.sendRedirect("login.html");
                return;
            }

            String name = (String) session.getAttribute("user");

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/elms", "root", "root");

            PreparedStatement ps = con.prepareStatement(
                "SELECT * FROM leave_requests WHERE name=?");

            ps.setString(1, name);

            ResultSet rs = ps.executeQuery();

            response.getWriter().println("<!DOCTYPE html><html><head><title>Leave Status</title></head>");

            response.getWriter().println("<body style='margin:0;font-family:Arial;background:linear-gradient(135deg,#6a11cb,#2575fc);'>");

            response.getWriter().println("<div style='max-width:800px;margin:50px auto;background:white;padding:30px;border-radius:15px;box-shadow:0 10px 25px rgba(0,0,0,0.2);'>");

            response.getWriter().println("<h2 style='text-align:center;color:#2575fc;'>My Leave Status</h2>");

            
            response.getWriter().println("<table style='width:100%;border-collapse:collapse;'>");

            response.getWriter().println("<tr style='background:#6a11cb;color:white;text-align:center;'>"+
                "<th>Type</th><th>From</th><th>To</th><th>Status</th></tr>");

            boolean found = false;

            while (rs.next()) {
                found = true;

                response.getWriter().println("<tr style='text-align:center;border-bottom:1px solid #ccc;'>"+
                    "<td>"+rs.getString("type")+"</td>"+
                    "<td>"+rs.getString("from_date")+"</td>"+
                    "<td>"+rs.getString("to_date")+"</td>"+
                    "<td>"+rs.getString("status")+"</td>"+
                    "</tr>");
            }

            if(!found){
                response.getWriter().println("<tr><td colspan='4' style='text-align:center;'>No leave records found!</td></tr>");
            }

            response.getWriter().println("</table>");

            response.getWriter().println("<div style='text-align:center;margin-top:20px;'>"+
                "<a href='employeeDashboard.html' style='text-decoration:none;color:#2575fc;'>Back to Dashboard</a></div>");

            response.getWriter().println("</div></body></html>");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}