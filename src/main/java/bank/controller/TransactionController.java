package bank.controller;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import bank.model.bl.TransactionBl;
import bank.model.entity.*;
import java.time.LocalDateTime;

@Slf4j
public class TransactionController {
    @Getter
    private final static TransactionController instance = new TransactionController();
    private TransactionController() {}

    public Response save(TransactionType transactionType,
                         double amount, LocalDateTime dateTime, Account account) {
        try {
            Transaction transaction =
                    Transaction
                            .builder()
                            .transactionType(transactionType)
                            .amount(amount)
                            .dateTime(dateTime)
                            .account(account)
                            .build();
            TransactionBl.getInstance().save(transaction);
            log.debug("Transaction saved successfully");
            return new Response(ResponseStatus.Success,"Transaction saved successfully",transaction);
        } catch (Exception e) {
            log.error("Transaction save failed");
            return new Response(ResponseStatus.Failure,"Transaction save failed",e.getMessage());
        }
    }
    public Response update(long transactionId, TransactionType transactionType,
                         double amount, LocalDateTime dateTime, Account account) {
        try {
            Transaction transaction =
                    Transaction
                            .builder()
                            .transactionid(transactionId)
                            .transactionType(transactionType)
                            .amount(amount)
                            .dateTime(dateTime)
                            .account(account)
                            .build();
            TransactionBl.getInstance().update(transaction);
            log.debug("Transaction edited");
            return new Response(ResponseStatus.Success,"Transaction edited successfully",transaction);
        } catch (Exception e) {
            log.error("Transaction edit failed");
            return new Response(ResponseStatus.Failure,"Transaction edit failed",e.getMessage());
        }
    }
    public Response delete(long transactionId) {
        try {
            Transaction transaction = TransactionBl.getInstance().delete(transactionId);
            log.debug("Transaction deleted successfully");
            return new Response(ResponseStatus.Success,"Transaction deleted successfully",transaction);
        }catch (Exception e) {
            log.error("Transaction delete failed");
            return new Response(ResponseStatus.Failure,"Transaction delete failed",e.getMessage());
        }
    }
    public Response findAll() {
        try {
            log.debug("select all transactions");
            return new Response(ResponseStatus.Success,"FindAll",TransactionBl.getInstance().findAll());
        } catch (Exception e) {
            log.error("findAll failed");
            return new Response(ResponseStatus.Failure, e.getMessage());

        }
    }
    public Response findById(long id) {
        try {
            log.debug("select transaction by id");
            return new Response(ResponseStatus.Success,"FindById : "+ id,TransactionBl.getInstance().findById(id));
        } catch (Exception e) {
            return new Response(ResponseStatus.Failure, e.getMessage());
        }
    }
}

