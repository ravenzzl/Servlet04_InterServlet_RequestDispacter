package com.servlet.login;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CheckLoginServlet
 */
@WebServlet("/loginServlet")
public class CheckLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     Connection con=null;
     PreparedStatement stmt;
    
	public void init() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost/mydb","root","root");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
   
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		try {
			stmt=con.prepareStatement("select * from user where email=? and password=?;");
			String email=req.getParameter("email");
			String pass=req.getParameter("password");
			stmt.setString(1, email);
			stmt.setString(2, pass);
		    ResultSet rs=stmt.executeQuery();
		    if(rs.next()) {
		    	RequestDispatcher r1=req.getRequestDispatcher("Home.html");
		    	r1.forward(req, res); // :Forwards a request from a servlet to another resource (servlet, JSP file, or HTML file) on the server.
		    }else {
		    	RequestDispatcher rd = req.getRequestDispatcher("login.html");
		    	rd.include(req, res); // include the content of resource in response 
		    }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}












