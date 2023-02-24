package org.pentgn.ForwardApp;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/fs")
public class FirstServlet extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException 
	{
		String pname = req.getParameter("pn");
		String pqty = req.getParameter("pq");
		
		//add request object attributes into scope
		req.setAttribute("prdnm", pname);
		req.setAttribute("prdqt", pqty);
		
		//dispatch request
		RequestDispatcher rd = req.getRequestDispatcher("ss");
		rd.forward(req, resp);// ----> request forwarded to next servlet
	}

}
