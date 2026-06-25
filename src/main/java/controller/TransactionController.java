package controller;

import lombok.Getter;
import model.entity.Account;

import java.util.List;

public class TransactionController {
    @Getter
    private final static TransactionController instance = new TransactionController();
    private TransactionController() {}

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

