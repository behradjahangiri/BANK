package model.mapper;

import model.entity.Account;
import model.entity.Customer;
import model.entity.Transaction;
import model.entity.TransactionType;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TransactionMapper {
    public Transaction recordToTransaction(ResultSet resultSet) throws SQLException {

        Customer sourcecustomer =
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

        Customer targetcustomer =
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

        Account sourceaccount =
                Account
                        .builder()
                        .accountNumber(resultSet.getInt("accountnumber"))
                        .balance(resultSet.getDouble("balance"))
                        .openDate(resultSet.getDate("opendate").toLocalDate())
                        .status(resultSet.getString("status"))
                        .customer(sourcecustomer)
                        .build();
        Account targetaccount =
                Account
                        .builder()
                        .accountNumber(resultSet.getInt("accountnumber"))
                        .balance(resultSet.getDouble("balance"))
                        .openDate(resultSet.getDate("opendate").toLocalDate())
                        .status(resultSet.getString("status"))
                        .customer(targetcustomer)
                        .build();

        return Transaction.
                builder()
                .transactionid(resultSet.getLong("transactionid"))
                .transactionType(TransactionType.valueOf(resultSet.getString("transactiontype")))
                .amount(resultSet.getFloat("amount"))
                .sourceAccount(sourceaccount)
                .targetAccount(targetaccount)
                .build();
    }
}
