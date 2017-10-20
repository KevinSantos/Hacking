package com.multicert.project.summerschool.insecure.insecure_server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class Main {

	public static void main(String[] args) {

		Server server;
		try {
			initDatabase();

			server = new Server();
			ServerConnector connector = new ServerConnector(server);
			connector.setPort(8080);
			server.addConnector(connector);

			ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
			context.setContextPath("/");
			server.setHandler(context);

			ServletHolder login = new ServletHolder("login", new LoginServlet());
			context.addServlet(login, "/login/*");

			ServletHolder doc = new ServletHolder("doc", DocumentServlet.class);
			context.addServlet(doc, "/doc/*");


		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return;
		}


		try {
			server.start();
			server.join();
		} catch (Exception t) {
			t.printStackTrace(System.err);
		}

	}


	private static Connection initDatabase() throws ClassNotFoundException, SQLException {


		Class.forName("org.h2.Driver");


		Connection conn = DriverManager.getConnection("jdbc:h2:mem:test");

		Statement st = conn.createStatement();
		st.execute("create table user(id integer, username varchar(50), password varchar(30))");

		st.execute("INSERT INTO user(id, username, password) VALUES(1, 'admin', '12345678')");
		st.execute("INSERT INTO user(id, username, password) VALUES(1, 'john', '1234')");
		st.execute("INSERT INTO user(id, username, password) VALUES(1, 'smith', '4321')");

		return conn;

	}

}
