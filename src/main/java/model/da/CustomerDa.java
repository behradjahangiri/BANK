package model.da;

import model.entity.Customer;
import model.mapper.CustomerMapper;
import model.tools.ConnectionProvider;
import oracle.sql.DATE;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CustomerDa implements AutoCloseable {
    private final CustomerMapper customerMapper = new CustomerMapper();
    private Connection connection;
    private PreparedStatement preparedStatement;

    public void saveCustomer(Customer customer)  throws Exception {
        connection = ConnectionProvider.getInstance().getConnection();
        customer.setId(ConnectionProvider.getInstance().getNextId("customer_seq"));
        preparedStatement = connection.prepareStatement(
                "INSERT INTO CUSTOMER (ID, USERNAME, PASSWORD, FIRSTNAME, LASTNAME, NATIONLLID, " +
                        "PHONE, EMAIL, ADDRESS, DATEOFBIRTH, REGISTRATIONDATE) "+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)"
        );
        preparedStatement.setLong(1, customer.getId());
        preparedStatement.setString(2, customer.getUsername());
        preparedStatement.setString(3, customer.getPassword());
        preparedStatement.setString(4, customer.getFirstName());
        preparedStatement.setString(5, customer.getLastName());
        preparedStatement.setString(6,customer.getNationllid());
        preparedStatement.setString(7, customer.getPhone());
        preparedStatement.setString(8, customer.getEmail());
        preparedStatement.setString(9, customer.getAddress());
        preparedStatement.setDate(10, Date.valueOf(customer.getDateOfBirth()));
        preparedStatement.setDate(11, Date.valueOf(customer.getRegistrationDate()));


    }






    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
    }
}
