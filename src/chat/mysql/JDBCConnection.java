package chat.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnection {
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    private static String url="jdbc:mysql://127.0.0.1:3306/chatgame";
    private  static String username="root";
    private static String password="123456";
    public static Connection getConnection()  {
        Connection connection;
        try {
            connection= DriverManager.getConnection(url,username,password);
            return connection;

        } catch (SQLException e) {
            System.out.println("数据库未连接");
            return null;
        }
    }
}
