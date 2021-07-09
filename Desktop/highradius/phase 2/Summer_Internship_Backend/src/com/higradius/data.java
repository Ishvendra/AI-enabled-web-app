package com.higradius;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.higradius.DAO;
import com.higradius.model;

/**
 * ControllerServlet.java
 * This servlet acts as a page controller for the application, handling all
 * requests from the user.
 */

@WebServlet("")
public class data extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DAO userDAO;
	
	public void init() {
		userDAO = new DAO();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();
		System.out.println(action);

		try {
			listRow(request, response);
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	private void listRow(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		int pageNo = request.getParameter("pgno")==null?0:Integer.parseInt(request.getParameter("pgno"));
		System.out.println(pageNo);

		List<model> listRow = userDAO.selectAllRows(pageNo);
		request.setAttribute("listRow", listRow);
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
	}

}
