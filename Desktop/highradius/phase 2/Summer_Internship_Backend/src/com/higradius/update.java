package com.higradius;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.higradius.DAO;
import com.higradius.model2;


/**
 * Servlet implementation class update
 */

@WebServlet("/update")

public class update extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DAO userDAO;   

	public void init() {
		userDAO = new DAO();
	}
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			updateRow(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}
	
	private void updateRow(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		long invoice_id = Long.parseLong(request.getParameter("invoice_id"));
		int total_open_amount = Integer.parseInt(request.getParameter("total_open_amount"));	
		String Notes = request.getParameter("Notes");

		model2 book = new model2(invoice_id, total_open_amount, Notes);
		userDAO.updateRow(book);
		response.sendRedirect("/Summer_Internship_Backend/");
	}

}

