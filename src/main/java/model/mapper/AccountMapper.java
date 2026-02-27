package model.mapper;

import model.entity.Account;
import model.entity.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountMapper {
    public Account recordToAccount(ResultSet resultSet) throws SQLException {
        Customer customer =
                Customer
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

        return Account
                .builder()
                .accountId(resultSet.getString("accountid"))
                .balance(resultSet.getDouble("balance"))
                .openDate(resultSet.getDate("opendate").toLocalDate())
                .status(resultSet.getString("status"))
                .customer(customer)
                .build();
    }
}
