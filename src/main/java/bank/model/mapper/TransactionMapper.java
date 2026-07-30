package bank.model.mapper;

import bank.model.entity.Account;
import bank.model.entity.Customer;
import bank.model.entity.Transaction;
import bank.model.entity.TransactionType;

import java.sql.ResultSet;
import java.sql.SQLException;


public class TransactionMapper {

    public Transaction recordToTransaction(ResultSet resultSet) throws SQLException {

        // ========== مشتری منبع (Source Customer) ==========
//        Customer sourceCustomer = Customer
//                .builder()
//                .id(resultSet.getLong("source_customer_id"))
//                .username(resultSet.getString("source_customer_username"))
//                .password(resultSet.getString("source_customer_password"))
//                .firstName(resultSet.getString("source_customer_firstname"))
//                .lastName(resultSet.getString("source_customer_lastname"))
//                .nationllid(resultSet.getString("source_customer_nationllid"))
//                .phone(resultSet.getString("source_customer_phone"))
//                .email(resultSet.getString("source_customer_email"))
//                .address(resultSet.getString("source_customer_address"))
//                .dateOfBirth(resultSet.getDate("source_customer_dateofbirth").toLocalDate())
//                .registrationDate(resultSet.getDate("source_customer_registrationdate").toLocalDate())
//                .build();
//
//        // ========== مشتری مقصد (Target Customer) ==========
//        Customer targetCustomer = Customer
//                .builder()
//                .id(resultSet.getLong("target_customer_id"))
//                .username(resultSet.getString("target_customer_username"))
//                .password(resultSet.getString("target_customer_password"))
//                .firstName(resultSet.getString("target_customer_firstname"))
//                .lastName(resultSet.getString("target_customer_lastname"))
//                .nationllid(resultSet.getString("target_customer_nationllid"))
//                .phone(resultSet.getString("target_customer_phone"))
//                .email(resultSet.getString("target_customer_email"))
//                .address(resultSet.getString("target_customer_address"))
//                .dateOfBirth(resultSet.getDate("target_customer_dateofbirth").toLocalDate())
//                .registrationDate(resultSet.getDate("target_customer_registrationdate").toLocalDate())
//                .build();

        // ========== حساب منبع (Source Account) ==========
//        Account sourceAccount = Account
//                .builder()
//                .accountNumber(resultSet.getString("source_account_number"))
//                .balance(resultSet.getDouble("source_balance"))
//                .openDate(resultSet.getDate("source_open_date").toLocalDate())
//                .status(resultSet.getString("source_status"))
//                .customer(sourceCustomer)
//                .build();
//
//        // ========== حساب مقصد (Target Account) ==========
//        Account targetAccount = Account
//                .builder()
//                .accountNumber(resultSet.getString("target_account_number"))
//                .balance(resultSet.getDouble("target_balance"))
//                .openDate(resultSet.getDate("target_open_date").toLocalDate())
//                .status(resultSet.getString("target_status"))
//                .customer(targetCustomer)
//                .build();

        // ========== ساخت تراکنش ==========
//        return Transaction
//                .builder()
//                .transactionid(resultSet.getLong("transaction_id"))
//                .transactionType(TransactionType.valueOf(resultSet.getString("transaction_type")))
//                .amount(resultSet.getFloat("amount"))
//                .date(resultSet.getDate("transaction_date").toLocalDate())
//                .sourceAccount(sourceAccount)
//                .targetAccount(targetAccount)
//                .build();

        // ========== مشتری ==========
        Customer customer = Customer
                .builder()
                .id(resultSet.getLong("customer_id"))
                .username(resultSet.getString("customer_username"))
                .password(resultSet.getString("customer_password"))
                .firstName(resultSet.getString("customer_firstname"))
                .lastName(resultSet.getString("customer_lastname"))
                .nationllid(resultSet.getString("customer_nationallid"))
                .phone(resultSet.getString("customer_phone"))
                .email(resultSet.getString("customer_email"))
                .address(resultSet.getString("customer_address"))
                .dateOfBirth(resultSet.getDate("customer_dateofbirth").toLocalDate())
                .registrationDate(resultSet.getDate("customer_registrationdate").toLocalDate())
                .build();

        // ========== حساب ==========
        Account account = Account
                .builder()
                .accountId(resultSet.getInt("account_id"))
                .balance(resultSet.getDouble("account_balance"))
                .openDate(resultSet.getDate("account_open_date").toLocalDate())
                .status(resultSet.getString("account_status"))
                .customer(customer)
                .build();

        // ========== تراکنش ==========
        return Transaction
                .builder()
                .transactionId(resultSet.getLong("transaction_id"))
                .transactionType(TransactionType.valueOf(resultSet.getString("transaction_type")))
                .amount(resultSet.getDouble("amount"))
                .dateTime(resultSet.getTimestamp("transaction_date").toLocalDateTime())
                .account(account)
                .build();
    }
}