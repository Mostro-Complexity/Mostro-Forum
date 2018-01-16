package com.controller;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/RegistrateServlet")
public class RegistrateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String successPage = "welcome.jsp";
	private static final String failPage = "login.jsp";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegistrateServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		String user = request.getParameter("usrname");
		String passwd = request.getParameter("passwd_1");
		String passwd_again = request.getParameter("passwd_2");

		registrate(request, response, user, passwd, passwd_again);
	}

	public void registrate(HttpServletRequest request, HttpServletResponse response, String username, String passwd,
			String passwd_again) throws IOException {
		HttpSession session = request.getSession();
		session.setAttribute("error", "");
		session.setAttribute("message", "");
		if (username.equals("")) {
			session.setAttribute("error", "用户名不能为空");
		} else if (passwd.equals("")) {
			session.setAttribute("error", "密码不能为空");
		} else if (passwd.equals(passwd_again)) {
			session.setAttribute("error", "两次输入密码不一致");
		} else {
			System.out.println("new user " + username + " registrate");
			session.setAttribute("user", username);
			storeRegInfo(username, passwd);
			response.sendRedirect(successPage);
		}
	}

	public void storeRegInfo(String usrname, String password) {
		String driverClass = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
		String url = "jdbc:sqlserver://localhost:1433; DatabaseName = BBSM";
		String DBUSER = "Ting";
		String PASSWORD = "zt18798859427";
		try {
			Class.forName(driverClass);
			java.sql.Connection cn = DriverManager.getConnection(url, DBUSER, PASSWORD);
			Statement stmt = cn.createStatement();
			String sql = "insert into usr_info values (\'" + usrname + "\' , \'" + password + "\', NULL,\'��\',NULL)";
			stmt.execute(sql);
			cn.close();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
	}
}
