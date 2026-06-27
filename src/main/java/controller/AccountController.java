package controller;

import lombok.Getter;
import model.bl.AccountBl;
import model.entity.Account;
import model.entity.Customer;
import model.entity.Response;
import model.entity.ResponseStatus;
import lombok.extern.slf4j.Slf4j;
import java.time.LocalDate;
@Slf4j

public class AccountController {
    @Getter
    private final static AccountController instance = new AccountController();

    private AccountController() {}

    public Response save(double balance, LocalDate openDate, String status, Customer customer) {
    try {
        Account account =
                Account
                        .builder()
                        .balance(balance)
                        .openDate(openDate)
                        .status(status)
                        .customer(customer)
                        .build();
        AccountBl.getInstance().save(account);
        log.debug("Account saved");
        return new Response(ResponseStatus.Success, "Account has been saved", account);
    } catch (Exception e) {
        log.error("Account save failed");
        return new Response(ResponseStatus.Failure, e.getMessage());
    }
}
    public Response update(int accountId, double balance, LocalDate openDate, String status, Customer customer) {
        try {
            Account account =
                    Account
                            .builder()
                            .accountId(accountId)
                            .balance(balance)
                            .openDate(openDate)
                            .status(status)
                            .customer(customer)
                            .build();
            AccountBl.getInstance().update(account);
            log.info("Account edited");
            return new Response(ResponseStatus.Success, "Account has been edited", account);
        } catch (Exception e) {
            log.error("Account edit failed");
            return new Response(ResponseStatus.Failure, e.getMessage());
        }
    }
    public Response delete(int id) {
        try {
            Account account = AccountBl.getInstance().delete(id);
            log.debug("Account deleted");
            return new Response(ResponseStatus.Success, "Account has been deleted", account);
        } catch (Exception e) {
            return new Response(ResponseStatus.Failure, e.getMessage());
        }
    }
    public Response findAll() {
       try {
           log.debug("select all account");
           return new Response(ResponseStatus.Success,"FindAll",AccountBl.getInstance().findAll());
       }catch (Exception e) {
           log.error("FindAll failed");
           return new Response(ResponseStatus.Failure, e.getMessage());
       }
    }
    public Response findById(int id) {
        try {
            log.debug("select account by id");
            return new Response(ResponseStatus.Success,"FindById : "+id,AccountBl.getInstance().findById(id));
        } catch (Exception e) {
            log.error("FindById failed");
            return new Response(ResponseStatus.Failure, e.getMessage());
        }
    }
}
