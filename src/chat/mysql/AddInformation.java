package chat.mysql;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddInformation {
    public static void add(String account,String password,String name,int port) throws SQLException {
        String sql ="INSERT INTO register(account,password,name,port) VALUES (?,?,?,?) ";
        PreparedStatement preparedStatement;
        preparedStatement = JDBCConnection.getConnection().prepareStatement(sql);
        preparedStatement.setString(1,account);
        preparedStatement.setString(2,password);
        preparedStatement.setString(3,name);
        preparedStatement.setInt(4,port);

        preparedStatement.executeLargeUpdate();//插入数据
        preparedStatement.close();
    }
}
