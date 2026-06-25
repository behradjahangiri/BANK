package controller;

import lombok.Getter;
import model.entity.Account;

import java.util.List;

public class CustomerController {
    @Getter
    private final static CustomerController instance = new CustomerController();
    private CustomerController() {}

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

