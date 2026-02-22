package model.tools;

import lombok.Getter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ConnectionProvider {
    @Getter
    private final static ConnectionProvider instance = new ConnectionProvider();
    private ConnectionProvider() {
    }
    public Connection getConnection() throws Exception {
        Class.forName("oracle.jdbc.OracleDriver");
        return DriverManager.getConnection(
                "jdbc:oracle:thin:@localhost:1522:xe",
                "java",
                "java123"
        );
    }
    public int getNextId(String sequenceName) throws Exception {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT "+sequenceName+".NEXTVAL FROM DUAL");
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        return resultSet.getInt("NEXTVAL");
    }
}
