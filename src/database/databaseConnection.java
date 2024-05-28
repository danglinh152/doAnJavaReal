package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class databaseConnection {
	public static Connection getDatabaseConnection() {
		Connection c = null;
		try {
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				c.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
				c.setAutoCommit(false);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String url = "jdbc:oracle:thin:@//localhost:1522/DANGLINH1502";
			c = DriverManager.getConnection(url, "c##sinhVien03", "123");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return c;
	}

	public static void closeDatabaseConnection(Connection c) {
		try {
			c.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
