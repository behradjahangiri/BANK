package model.mapper;

import model.entity.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerMapper {
    public Customer recordTo(ResultSet resultSet) throws SQLException {
        return Customer
                .builder()
                .id(resultSet.getInt("id"))
                .username(resultSet.getString("username"))
                .password(resultSet.getString("password"))
                .firstName(resultSet.getString("firstname"))
                .lastName(resultSet.getString("lastname"))
                .nationllid(resultSet.getString("nationllid"))
                .phone(resultSet.getString("phone"))
                .email(resultSet.getString("email"))
                .address(resultSet.getString("address"))
                .dateOfBirth(resultSet.getDate("dateofbirth").toLocalDate())
                .registrationDate(resultSet.getDate("registrationdate").toLocalDate())
                .build();
    }
}
