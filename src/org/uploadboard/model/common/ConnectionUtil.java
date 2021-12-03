package org.uploadboard.model.common;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConnectionUtil {
	private static DataSource ds;
	
	static {
		try {
			InitialContext ctx = new InitialContext();
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/myOracle"); // ConnectionPool 객체 얻어오기! (context.xml과 web.xml에 resource 설정필요)
		}catch(NamingException e) {}
	}
	
	public static Connection getConnection() throws SQLException {
		return ds.getConnection();
	}
}
