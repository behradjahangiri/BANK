package model.da;

import model.entity.Customer;
import model.mapper.CustomerMapper;
import model.tools.ConnectionProvider;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDa implements AutoCloseable {
    private final CustomerMapper customerMapper = new CustomerMapper();
    private Connection connection;
    private PreparedStatement preparedStatement;

    public void saveCustomer(Customer customer)  throws Exception {
        connection = ConnectionProvider.getInstance().getConnection();
        customer.setId(ConnectionProvider.getInstance().getNextId("customer_seq"));
        preparedStatement = connection.prepareStatement(
                "INSERT INTO CUSTOMER (ID, USERNAME, PASSWORD, FIRSTNAME, LASTNAME, NATIONLLID, " +
                        "PHONE, EMAIL, ADDRESS, DATEOFBIRTH, REGISTRATIONDATE) "+"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)"
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
        preparedStatement.execute();
    }
    public void updateCustomer(Customer customer)  throws Exception {
        connection = ConnectionProvider.getInstance().getConnection();
        preparedStatement = connection.prepareStatement(
                "update CUSTOMER set USERNAME=?, PASSWORD=?, FIRSTNAME=?,LASTNAME=?,NATIONLLID=?" +
                        ",PHONE=?,EMAIL=?,ADDRESS=? ,dateofbirth=?, registrationdate=? where id=?"
        );

        preparedStatement.setString(1, customer.getUsername());
        preparedStatement.setString(2, customer.getPassword());
        preparedStatement.setString(3, customer.getFirstName());
        preparedStatement.setString(4, customer.getLastName());
        preparedStatement.setString(5,customer.getNationllid());
        preparedStatement.setString(6, customer.getPhone());
        preparedStatement.setString(7, customer.getEmail());
        preparedStatement.setString(8, customer.getAddress());
        preparedStatement.setDate(9, Date.valueOf(customer.getDateOfBirth()));
        preparedStatement.setDate(10, Date.valueOf(customer.getRegistrationDate()));
        preparedStatement.setLong(11, customer.getId());
        preparedStatement.execute();
    }
    public void deleteCustomer(long id)  throws Exception {
        connection = ConnectionProvider.getInstance().getConnection();
        preparedStatement = connection.prepareStatement(
                "delete from CUSTOMER where id=?"
        );
        preparedStatement.setLong(1, id);
        preparedStatement.execute();
    }
    public List<Customer> findAllCustomers()  throws Exception {
        connection = ConnectionProvider.getInstance().getConnection();
        preparedStatement = connection.prepareStatement(
                "select * from CUSTOMER"
        );
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Customer> customerList = new ArrayList<>();
        while (resultSet.next()) {
            Customer customer = customerMapper.recordToCustomer(resultSet);
            customerList.add(customer);
        }
        return customerList;
    }
    public Customer findCustomerById(long id)  throws Exception {
        connection = ConnectionProvider.getInstance().getConnection();
        preparedStatement = connection.prepareStatement(
                "select * from CUSTOMER where id=?"
        );
        preparedStatement.setLong(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        Customer customer = null;
        if (resultSet.next()) {
            customer = customerMapper.recordToCustomer(resultSet);
        }
        return customer;
    }
    public Customer findCustomerByNationllid(long id)  throws Exception {
        connection = ConnectionProvider.getInstance().getConnection();
        preparedStatement = connection.prepareStatement(
                "select * from CUSTOMER where NATIONLLID=?"
        );
        preparedStatement.setLong(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        Customer customer = null;
        if (resultSet.next()) {
            customer = customerMapper.recordToCustomer(resultSet);
        }
        return customer;
    }
    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
    }
}
