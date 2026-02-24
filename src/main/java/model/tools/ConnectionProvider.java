package model.tools;

import lombok.Getter;

import java.sql.Connection;
import java.sql.DriverManager;

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
}
