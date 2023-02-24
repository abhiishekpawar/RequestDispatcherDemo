package org.pentgn.ForwardApp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ss")
public class SecondServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException 
	{
		String pname = (String) req.getAttribute("prdnm");
		String pqty = (String) req.getAttribute("prdqt");
		int ipqty = Integer.parseInt(pqty);
		
		//Buisness logic
		double price = 40000;
		double totalsum = price * ipqty;
		
		//presentation logic
		PrintWriter out = resp.getWriter();
		out.println("<html><body bgcolor='B6ABAB'><h1> Product name "+pname+" is added to cart and "
				+ "toatl price has to pay is  "+totalsum+"</h1></body></html>");
		
		out.flush();
		out.close();
		
		//persistence logic
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "insert into pentagon.product values(?,?,?)";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306?user=root&password=admin");
			pstmt = con.prepareStatement(sql);
			//set data for placeholder before exection
			pstmt.setString(1, pname);
			pstmt.setInt(2,ipqty);
			pstmt.setDouble(3, totalsum);
			
			//execute now
			pstmt.executeUpdate();
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(pstmt != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
		}
	}

}
