package model.tools;

import lombok.Getter;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

//public class ConnectionProvider {
//    @Getter
//    private final static ConnectionProvider instance = new ConnectionProvider();
//    private ConnectionProvider() {
//    }
//    public Connection getConnection() throws Exception {
//        Class.forName("oracle.jdbc.OracleDriver");
//        return DriverManager.getConnection(
//                "jdbc:oracle:thin:@localhost:1522:xe",
//                "java",
//                "java123"
//        );
//    }
//}
public class ConnectionProvider {
    //    Singleton
    private final static BasicDataSource BASIC_DATA_SOURCE = new BasicDataSource();

    @Getter
    private final static ConnectionProvider instance = new ConnectionProvider();

    private ConnectionProvider() {
//        System.out.println("Connection Provider Created");
        BASIC_DATA_SOURCE.setDriverClassName("oracle.jdbc.driver.OracleDriver");
        BASIC_DATA_SOURCE.setUrl("jdbc:oracle:thin:@localhost:1521:XE");
        BASIC_DATA_SOURCE.setUsername("java");
        BASIC_DATA_SOURCE.setPassword("java123");
        BASIC_DATA_SOURCE.setMinIdle(5);
        BASIC_DATA_SOURCE.setMaxTotal(20);
    }

    public Connection getConnection() throws Exception {
        return BASIC_DATA_SOURCE.getConnection();
    }



    public int getNextId(String sequenceName) throws Exception {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT "+sequenceName+".NEXTVAL FROM DUAL");
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        return resultSet.getInt("NEXTVAL");
    }
}