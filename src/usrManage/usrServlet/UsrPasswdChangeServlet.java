package usrManage.usrServlet;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/UsrPasswdChangeServlet")

public class UsrPasswdChangeServlet extends HttpServlet{

	public UsrPasswdChangeServlet() {
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
		String usrname= request.getParameter("usrname");
		String passwd= request.getParameter("passwd");
		//页面代码：（修改密码页面）
		
		//
		passwdChange(usrname, passwd);
	}
	
	public void passwdChange(String usrname,String passwd)
	{
		String driverClass = "com.microsoft.sqlserver.jdbc.SQLServerDriver";  
		String url = "jdbc:sqlserver://localhost:1433; DatabaseName = BBSM";  
		String DBUSER="Ting";
		String PASSWORD="zt18798859427";
		try{
			Class.forName(driverClass);
		    java.sql.Connection cn=DriverManager.getConnection(url,DBUSER,PASSWORD);
			Statement stmt=cn.createStatement();
		    String sql="update user_info set password=\'"+passwd+"\'"+
			"where usrname=\'"+usrname+"\'";
		   stmt.execute(sql);
		   cn.close();
		}
		catch(Exception ex){
		System.out.println(ex.getMessage());
		System.out.println("连接异常");
		ex.printStackTrace();
		}

	}
}
