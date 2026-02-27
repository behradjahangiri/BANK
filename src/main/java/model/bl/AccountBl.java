package model.bl;

import lombok.Getter;
import model.da.AccountDa;
import model.entity.Account;

import java.util.List;

public class AccountBl implements BusinessLogic<Account, Integer>{
    @Getter
    private final static AccountBl instance = new AccountBl();

    private AccountBl() {
    }


    @Override
    public Account save(Account account) throws Exception {
        try (AccountDa accountDa = new AccountDa())
        {
            if (accountDa.findAccountById(account.getAccountId()) != null)
            {
                throw new Exception("Duplicate Account Number !!!!!");
            }
            accountDa.saveAccount(account);
        }
        return null;
    }

    @Override
    public Account update(Account account) throws Exception{
        try (AccountDa accountDa = new AccountDa())
        {
            if (accountDa.findAccountById(account.getAccountId()) == null) {
                throw new Exception("Account with id = " + account.getAccountId() + " Not exists !!!");
            }
            if (accountDa.findAccountById(account.getAccountId()) != null)
            {
                throw new Exception("Duplicate account number !!!");
            }
            accountDa.updateAccount(account);
        }
        return account;
    }

    @Override
    public Account delete(Integer id) throws Exception
    {
        try (AccountDa accountDa = new AccountDa())
        {
         if (accountDa.findAccountById(id) == null)
         {
             throw new Exception("Account with id = " + id + " Not exists !!!");
         }
         accountDa.deleteAccount(id);
        }
        return null;
    }

    @Override
    public List<Account> findAll() throws Exception {
        try (AccountDa accountDa = new AccountDa())
        {
            return accountDa.findAllAccount();
        }
    }

    @Override
    public Account findById(Integer id) throws Exception{
        try (AccountDa accountDa = new AccountDa()) {
            return accountDa.findAccountById(id);
        }
    }

}
