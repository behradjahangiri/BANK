package bank.model.bl;

import lombok.Getter;
import bank.model.da.AccountDa;
import bank.model.entity.Account;
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
        return account;
    }

    @Override
    public Account update(Account account) throws Exception{
        try (AccountDa accountDa = new AccountDa())
        {
            if (accountDa.findAccountById(account.getAccountId()) == null) {
                throw new Exception("Account with id = " + account.getAccountId() + " Not exists !!!");
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

    public void deposit(Integer accountId,Double amount) throws Exception{
        try (AccountDa accountDa = new AccountDa())
        {
            accountDa.deposit(accountId, amount);
        }
    }
    public void withdraw(Integer accountId,Double amount) throws Exception{
        try (AccountDa accountDa = new AccountDa())
            {
            accountDa.withdraw(accountId, amount);
            }
    }
    public Double getBalance(Integer accountId) throws Exception{
        try (AccountDa accountDa = new AccountDa())
            {
            return accountDa.getBalance(accountId);
            }
    }

}
