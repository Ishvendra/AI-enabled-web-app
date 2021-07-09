package com.higradius;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.higradius.DAO;
import com.higradius.model;

/**
 * Servlet implementation class insert
 */

@WebServlet("/insert")

public class insert extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DAO userDAO;   

	public void init() {
		userDAO = new DAO();
	}
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			insertRow(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}
	
	private void insertRow(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		String name_customer = request.getParameter("name_customer");
		String cust_number = request.getParameter("cust_number");
		long invoice_id = Long.parseLong(request.getParameter("invoice_id"));
		int total_open_amount = Integer.parseInt(request.getParameter("total_open_amount"));
		String due_in_date = request.getParameter("due_in_date");
		
		Date predicted_clear_date = null;

		String Notes = request.getParameter("Notes");
		model newUser = new model(name_customer, cust_number,invoice_id, total_open_amount, due_in_date, predicted_clear_date, Notes);
		userDAO.insertRow(newUser);
		response.sendRedirect("/Summer_Internship_Backend/");
	}


}
