package com.multicert.project.summerschool.insecure.insecure_server;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {

		showForm(response, false, false);
	}


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {

		//get username and password from request parameters
		String username = req.getParameter("username");
		String password = req.getParameter("password");

		//print login attempt
		System.out.println("User login attempt: username=" + username + " ; password=" + password);

		try (Connection conn = DriverManager.getConnection("jdbc:h2:mem:test")) {

			Statement stmt = conn.createStatement();
			ResultSet rset = stmt.executeQuery("select password from user where username='" + username + "'");

			if (rset.next()) {
				String dbPassword = rset.getString(1);

				// successful login
				if (password.equals(dbPassword)) {
					response.setContentType("text/html");
					response.setStatus(HttpServletResponse.SC_OK);
					response.getWriter().println("<h1>Insecure Login Demo</h1>");
					response.getWriter().println("<h3>Login Success</h3>");
				} else {
					showForm(response, false, true);
				}
			} else {
				showForm(response, true, false);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new ServletException(e);
		}


	}


	/**
	 * Show login form, and any error message if applicable
	 * @param response - servlet response
	 * @param wrongUsername - if true, show 'wrong username' error message
	 * @param wrongPassword - if true, show 'wrong password' error message
	 * @throws IOException
	 */
	private void showForm(HttpServletResponse response, boolean wrongUsername, boolean wrongPassword) throws IOException {
		response.setContentType("text/html");
		response.setStatus(HttpServletResponse.SC_OK);
		response.getWriter().println("<h1>Insecure Login Demo</h1>");
		if (wrongUsername) {
			response.getWriter().println("<div style='color:red'>Wrong username</div>");
		}
		if (wrongPassword) {
			response.getWriter().println("<div style='color:red'>Wrong password</div>");
		}
		response.getWriter().println("<form method='POST'>");
		response.getWriter().println("username: <input type='text' name='username'/><br/>");
		response.getWriter().println("password: <input type='password' name='password'/><br/>");
		response.getWriter().println("<input type='submit' value='Login'/></form>");
	}
}
