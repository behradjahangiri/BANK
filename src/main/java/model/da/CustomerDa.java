package model.da;

import model.entity.Customer;
import model.mapper.CustomerMapper;
import model.tools.ConnectionProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CustomerDa implements AutoCloseable {
    private final CustomerMapper customerMapper = new CustomerMapper();
    private Connection connection;
    private PreparedStatement preparedStatement;

    public void saveCustomer(Customer customer)  throws Exception {
        connection = ConnectionProvider.getInstance().getConnection();
        customer.setId(ConnectionProvider.getInstance().getNextId("customer_seq"));
    }





    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
    }
}
