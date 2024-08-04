/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package example.crud.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.sql.*;
/**
 *
 * @author James
 */
public class DBUtil {
    private static Connection connection;
    private static String url = "jdbc:mysql://localhost:3306/test";
    private static String username = "root";
    private static String password = "";

    public static Connection getConnection() throws SQLException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			return DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}
}






//connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "");