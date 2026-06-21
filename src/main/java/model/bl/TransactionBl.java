package model.bl;

import model.da.TransactionDa;
import model.entity.Transaction;
import java.util.List;
import lombok.Getter;

public class TransactionBl
{
    @Getter
    private final static TransactionBl instance = new TransactionBl();
    private TransactionBl() {}

//    @Override
    public Transaction save(Transaction transaction) throws Exception {
        try (TransactionDa transactionDa = new TransactionDa()) {
            if (transactionDa.findTransactionById(transaction.getTransactionid()) != null) {
                throw new Exception("Transaction already exists");
            }
            transactionDa.saveTransaction(transaction);
        }
        return null;
    }

//    @Override
    public Transaction update(Transaction transaction) throws Exception {
        try (TransactionDa transactionDa = new TransactionDa()) {
            if (transactionDa.findTransactionById(transaction.getTransactionid()) == null) {
                throw new Exception("Transaction id not found");
            }
            if (transactionDa.findTransactionById(transaction.getTransactionid()) != null) {
                transactionDa.updateTransaction(transaction);
            }
        }
        return transaction;
    }

//    @Override
    public Transaction delete(Integer id) throws Exception {
        return null;
    }

//    @Override
    public Transaction delete(Transaction transaction) throws Exception {
        try (TransactionDa transactionDa = new TransactionDa()) {
            if (transactionDa.findTransactionById(transaction.getTransactionid()) == null) {
                throw new Exception("Transaction id not found");
            }
            transactionDa.deleteTransaction(transaction);
        }
        return null;
    }

//    @Override
    public List<Transaction> findAll() throws Exception {
        try (TransactionDa transactionDa = new TransactionDa()) {
            return transactionDa.findAllTransactions();
        }
    }

//    @Override
    public Transaction findById(Integer id) throws Exception {
        try (TransactionDa transactionDa = new TransactionDa()) {
            if (transactionDa.findTransactionById(id) == null) {
                throw new Exception("Transaction id not found");
            }
            return transactionDa.findTransactionById(id);
        }
    }
}
