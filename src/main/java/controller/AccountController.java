package controller;

import lombok.Getter;
import lombok.extern.java.Log;
import model.bl.AccountBl;
import model.entity.Account;
import model.entity.Customer;
import model.entity.Response;
import model.entity.ResponseStatus;
import lombok.extern.slf4j.Slf4j;
import java.time.LocalDate;
import java.util.List;
@Slf4j

public class AccountController {
    @Getter
    private final static AccountController instance = new AccountController();

    private AccountController() {}

    public Response save(int accountId, double balance, LocalDate openDate, String status, Customer customer) {
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
        AccountBl.getInstance().save(account);
        log.info("Account saved");
        return new Response(ResponseStatus.Success, "Account has been saved", account);
    } catch (Exception e) {
        log.error("Account save failed");
        return new Response(ResponseStatus.Failure, e.getMessage());
    }
}
    public String update() {
        return null;
    }
    public String delete() {
        return null;
    }
    public List<Account> findAll() {
        return null;
    }
    public Account findById(int id) {
        return null;
    }
}
