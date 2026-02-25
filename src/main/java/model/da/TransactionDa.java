package model.da;

import model.entity.Transaction;
import model.mapper.TransactionMapper;
import model.tools.ConnectionProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TransactionDa implements AutoCloseable {
    private final TransactionMapper transactionMapper = new TransactionMapper();
    private Connection connection;
    private PreparedStatement preparedStatement;

    public void saveTransaction(Transaction transaction) throws Exception {
        connection = ConnectionProvider.getInstance().getConnection();
        preparedStatement = connection.prepareStatement(
                "INSERT INTO TRANSACTION (TRANSACTIONID, TRANSACTIONTYPE, AMOUNT," +
                        " TRANSACTIONDATE, SOURCEACCOUNT, TARGETACCOUNT) "+"values (?,?,?,?,?,?)"
        );
        preparedStatement.setLong(1,transaction.getTransactionid());
        preparedStatement.setString(2,transaction.getTransactionType().name());
        preparedStatement.setDouble(3,transaction.getAmount());
        preparedStatement.setInt(4,transaction.getSourceAccount().getAccountNumber());
        preparedStatement.setInt(5,transaction.getTargetAccount().getAccountNumber());
        preparedStatement.execute();
    }
    public void updateTransaction(Transaction transaction) throws Exception {
        connection = ConnectionProvider.getInstance().getConnection();
        preparedStatement = connection.prepareStatement(
                "update TRANSACTION set TRANSACTIONTYPE=?,AMOUNT=?,TRANSACTIONDATE=?" +
                        ",SOURCEACCOUNT=?,TARGETACCOUNT=? where TRANSACTIONID=?"
        );

        preparedStatement.setString(1,transaction.getTransactionType().name());
        preparedStatement.setDouble(2,transaction.getAmount());
        preparedStatement.setInt(3,transaction.getSourceAccount().getAccountNumber());
        preparedStatement.setInt(4,transaction.getTargetAccount().getAccountNumber());
        preparedStatement.setLong(5,transaction.getTransactionid());
        preparedStatement.execute();
    }
    public void deleteTransaction(Transaction transaction) throws Exception {
        connection = ConnectionProvider.getInstance().getConnection();
        preparedStatement = connection.prepareStatement(
                "delete from TRANSACTION where TRANSACTIONID=?"
        );
        preparedStatement.setLong(1,transaction.getTransactionid());
        preparedStatement.execute();
    }

    public List<Transaction> findAllTransactions() throws Exception {
        connection = ConnectionProvider.getInstance().getConnection();
        preparedStatement = connection.prepareStatement(
                "SELECT * FROM TRANSACTION"
        );
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Transaction> transactionList = new ArrayList<>();

        while (resultSet.next()) {
            Transaction transaction = transactionMapper.recordToTransaction(resultSet);
            transaction.setTransactionid(resultSet.getLong("TRANSACTIONID"));
        }
        return transactionList;
    }



    public Transaction findTransactionById(long transactionid) throws Exception {
        connection = ConnectionProvider.getInstance().getConnection();
        preparedStatement = connection.prepareStatement(
                "select * from TRANSACTION where TRANSACTIONID=?"
        );
        preparedStatement.setLong(1,transactionid);
        ResultSet resultSet = preparedStatement.executeQuery();
        Transaction transaction = null;
        if(resultSet.next()){
            transaction = transactionMapper.recordToTransaction(resultSet);
        }
        return transaction;

    }
    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
    }
}
