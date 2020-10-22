package com.zensar.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		ServletConfig config = getServletConfig();
		
		String driver = config.getInitParameter("driver");
		String url = config.getInitParameter("url");
		String user = config.getInitParameter("username");
		String pass = config.getInitParameter("password");
		
		PrintWriter writer = resp.getWriter();
		String userName=req.getParameter("txtUser");
		String password=req.getParameter("txtPassword");
		writer.println("<html>");
		writer.println("<body>");
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
			try {
				Class.forName(driver);

					con = DriverManager.getConnection(url, user, pass);
					 ps = con.prepareStatement("select * from login where uname=? and pass = ?");
				
					  ps.setString(1,userName);
					  ps.setString(2,password);
					 
					rs =ps.executeQuery();
			           
					RequestDispatcher dis = null;
					    
			            if(rs.next())
						{
							dis=req.getRequestDispatcher("success.html");
//							dis.forward(req, resp);
//							dis.include(req, resp);
							resp.sendRedirect("success.html");
						}
						else {
							dis=req.getRequestDispatcher("error.html");
							resp.sendRedirect("error.html");
						}
			            writer.println("</html>");
						writer.println("</body>");
			}catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}


