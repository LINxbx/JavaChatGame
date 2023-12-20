package chat.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Check {
    public static ResultSet MyAccount(String account) throws SQLException {
        String sql = "SELECT* FROM register where account = ?";
        ResultSet resultSet;
        PreparedStatement preparedStatement;
        preparedStatement = JDBCConnection.getConnection().prepareStatement(sql);
        preparedStatement.setString(1, account);
        resultSet = preparedStatement.executeQuery();//查询数据
        return resultSet;
    }
}
