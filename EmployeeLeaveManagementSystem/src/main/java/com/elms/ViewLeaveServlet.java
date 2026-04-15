package com.elms;



import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.*;

@WebServlet("/ViewLeaveServlet")
public class ViewLeaveServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/elms", "root", "root");

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM leave_requests");

            // Start page + centered box
            response.getWriter().println("<!DOCTYPE html><html><head><title>View Leave Requests</title></head>");
            response.getWriter().println("<body style='margin:0;padding:0;font-family:Arial,sans-serif;background:linear-gradient(135deg,#6a11cb,#2575fc);'>");

            response.getWriter().println("<div style='max-width:900px;margin:50px auto;background:white;padding:30px;border-radius:15px;box-shadow:0 10px 25px rgba(0,0,0,0.2);'>");
            response.getWriter().println("<h2 style='text-align:center;color:#2575fc;'>Leave Requests</h2>");

            // Start table
            response.getWriter().println("<table style='width:100%;border-collapse:collapse;'>");
            response.getWriter().println("<tr style='background:#6a11cb;color:white;text-align:center;'><th>Name</th><th>Type</th><th>From</th><th>To</th><th>Reason</th><th>Status</th><th>Action</th></tr>");

            while (rs.next()) {
                int id = rs.getInt("id");

                // Row colors alternate
                String rowColor = (id % 2 == 0) ? "#f2f2f2" : "#ffffff";

                response.getWriter().println("<tr style='background:"+rowColor+";text-align:center;border-bottom:1px solid #ccc;'>"+
                    "<td>"+rs.getString("name")+"</td>"+
                    "<td>"+rs.getString("type")+"</td>"+
                    "<td>"+rs.getString("from_date")+"</td>"+
                    "<td>"+rs.getString("to_date")+"</td>"+
                    "<td>"+rs.getString("reason")+"</td>"+
                    "<td>"+rs.getString("status")+"</td>"+
                    "<td>" +
                        "<a href='ApproveServlet?id="+id+"' style='background:#6a11cb;color:white;padding:5px 10px;border-radius:5px;text-decoration:none;margin-right:5px;'>Approve</a>" +
                        "<a href='RejectServlet?id="+id+"' style='background:#fc4157;color:white;padding:5px 10px;border-radius:5px;text-decoration:none;'>Reject</a>" +
                    "</td>"+
                    "</tr>");
            }

            response.getWriter().println("</table>");

            response.getWriter().println("<div style='text-align:center;margin-top:20px;'><a href='adminDashboard.html' style='text-decoration:none;color:#2575fc;'>Back to Dashboard</a></div>");

            response.getWriter().println("</div></body></html>");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}