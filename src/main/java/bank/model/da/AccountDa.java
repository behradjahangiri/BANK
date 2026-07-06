package bank.model.da;

import lombok.extern.slf4j.Slf4j;
import bank.model.entity.Account;
import bank.model.mapper.AccountMapper;
import bank.model.tools.ConnectionProvider;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
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
//        log.debug("account saved");
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
//        log.debug("account edited");
    }

    public void deleteAccount(int accountid) throws Exception {
        connection = ConnectionProvider.getInstance().getConnection();
        preparedStatement = connection.prepareStatement(
                "DELETE FROM account WHERE ACCOUNTID=?"
        );
        preparedStatement.setInt(1, accountid);
        preparedStatement.execute();
//        log.debug("account deleted");
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
//        log.debug("select all account");
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
//        log.debug("select account by id");
        return account;
    }

    public Double getBalance(int accountId) throws Exception {
        connection = ConnectionProvider.getInstance().getConnection();
        preparedStatement = connection.prepareStatement(
                "SELECT balance FROM account WHERE ACCOUNTID=?"
        );
        preparedStatement.setInt(1, accountId);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getDouble("balance");
        }
        else {
            return 0.0;
        }
    }
    public void deposit(int accountId, double amount) throws Exception {
        AccountDa accountDa = new AccountDa();
        Double NewBalance = accountDa.getBalance(accountId) + amount;
        connection = ConnectionProvider.getInstance().getConnection();
        preparedStatement = connection.prepareStatement(
                "UPDATE account SET BALANCE=? where ACCOUNTID=?"
        );
        preparedStatement.setDouble(1, NewBalance);
        preparedStatement.setInt(2, accountId);

    }

    public void withdraw(int accountId, double amount) throws Exception {
        AccountDa accountDa = new AccountDa();
        Double NewBalance = accountDa.getBalance(accountId) - amount;
        connection = ConnectionProvider.getInstance().getConnection();
        preparedStatement = connection.prepareStatement(
                "UPDATE account SET BALANCE=? where ACCOUNTID=?"
        );
        preparedStatement.setDouble(1, NewBalance);
        preparedStatement.setInt(2, accountId);
    }


    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
    }
}
