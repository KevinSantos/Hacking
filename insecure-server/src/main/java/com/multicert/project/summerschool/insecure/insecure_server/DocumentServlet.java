package com.multicert.project.summerschool.insecure.insecure_server;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class DocumentServlet extends HttpServlet {

	private static List<User> users = new ArrayList<User>();


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {

		try {
			Connection conn = DriverManager.getConnection("jdbc:h2:mem:test");

			Statement stmt = conn.createStatement();
			ResultSet rset = stmt.executeQuery("select username from user where username = 'john'");

			while (rset.next()) {
				String username = rset.getString(1);
				User user = new User();
				user.setUsername(username);
				user.setDocument(dummyRead());
				users.add(user);
			}


			//get user data
			User user = users.get(0);

			response.setContentType("text/html");
			response.setStatus(HttpServletResponse.SC_OK);
			response.getWriter().println("<h1>User Data Demo</h1>");
			response.getWriter().println("<div>name: " + user.getUsername() + "</div><br/>");
			response.getWriter().println("Document Base64 <br/>");
			response.getWriter().println("<div>Dummy document contents</div>");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			users.clear();
		}


	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		super.doPost(req, resp);
	}

	private byte[] dummyRead() {
		return new byte[5000000];    //Read 5MB file
	}
}
