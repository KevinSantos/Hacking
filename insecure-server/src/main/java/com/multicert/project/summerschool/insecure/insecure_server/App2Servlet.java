package com.multicert.project.summerschool.insecure.insecure_server;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class App2Servlet extends HttpServlet {

	private static Map<Integer, String> data;


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {

		try {
			Connection conn = DriverManager.getConnection("jdbc:h2:mem:test");

			Statement stmt = conn.createStatement();
			ResultSet rset = stmt.executeQuery("select id, username from user");

			while (rset.next()) {
				Integer id = rset.getInt(1);
				String username = rset.getString(2);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//load large file into byte[]

		response.setContentType("text/html");
		response.setStatus(HttpServletResponse.SC_OK);
		response.getWriter().println("<h1>Hello Servlet 2</h1>");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		super.doPost(req, resp);
	}


}
