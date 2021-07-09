package com.higradius;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.higradius.DAO;

/**
 * Servlet implementation class delete
 */

@WebServlet("/delete")

public class delete extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DAO userDAO;   

	public void init() {
		userDAO = new DAO();
	}
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			deleteRow(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}
	
	private void deleteRow(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		long invoice_id = Long.parseLong(request.getParameter("invoice_id"));
		userDAO.deleteRow(invoice_id);
		response.sendRedirect("/Summer_Internship_Backend/");

	}


}
