package model.da;

import model.entity.Account;
import model.mapper.AccountMapper;
import model.tools.ConnectionProvider;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDa implements AutoCloseable {
    private final AccountMapper accountMapper = new AccountMapper();
    private Connection connection;
    private PreparedStatement preparedStatement;


    public void saveAccount(Account account) throws Exception {
        connection = ConnectionProvider.getInstance().getConnection();
//        account.setAccountNumber(ConnectionProvider.getInstance().getNextId("account_seq"));
        preparedStatement = connection.prepareStatement(
                "INSERT INTO account (ACCOUNTID ,BALANCE,OPENDATE,STATUS,CUSTOMERID) VALUES (?,?,?,?,?)"
        );
        preparedStatement.setInt(1, account.getAccountId());
        preparedStatement.setDouble(2, account.getBalance());
        preparedStatement.setDate(3, Date.valueOf(account.getOpenDate()));
        preparedStatement.setString(4, account.getStatus());
        preparedStatement.setLong(5, account.getCustomer().getId());
        preparedStatement.execute();
    }

    public void updateAccount(Account account) throws Exception {
        connection = ConnectionProvider.getInstance().getConnection();
        preparedStatement = connection.prepareStatement(
                "UPDATE account SET BALANCE=?,OPENDATE=?,STATUS=?,CUSTOMERID=? where ACCOUNTID=?"
        );

        preparedStatement.setDouble(1, account.getBalance());
        preparedStatement.setDate(2, Date.valueOf(account.getOpenDate()));
        preparedStatement.setString(3, account.getStatus());
        preparedStatement.setLong(4, account.getCustomer().getId());
        preparedStatement.setInt(5, account.getAccountId());
        preparedStatement.execute();
    }

    public void deleteAccount(int accountid) throws Exception {
        connection = ConnectionProvider.getInstance().getConnection();
        preparedStatement = connection.prepareStatement(
                "DELETE FROM account WHERE ACCOUNTID=?"
        );
        preparedStatement.setInt(1, accountid);
        preparedStatement.execute();
    }

    public List<Account> findAllAccount() throws Exception {
        connection = ConnectionProvider.getInstance().getConnection();
        preparedStatement = connection.prepareStatement(
                "SELECT * FROM account"
        );
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Account> accountList = new ArrayList<>();
        while (resultSet.next()) {
            Account account = accountMapper.recordToAccount(resultSet);
            account.setAccountId(resultSet.getInt("ACCOUNTid"));
        }
        return accountList;
    }

    public Account findAccountById(int id) throws Exception {
        connection = ConnectionProvider.getInstance().getConnection();
        preparedStatement = connection.prepareStatement(
                "SELECT * FROM account WHERE ACCOUNTID=?"
        );
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        Account account = accountMapper.recordToAccount(resultSet);

        return account;
    }


    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
    }
}
