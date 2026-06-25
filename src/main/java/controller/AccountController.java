package controller;

import lombok.Getter;
import model.entity.Account;

import java.util.List;

public class AccountController {
    @Getter
    private final static AccountController instance = new AccountController();
    private AccountController() {}

    public String save() {
        return null;
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
