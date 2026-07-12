package bank.model.tools;
import bank.model.entity.Account;


public class Session {
    public static Integer customerId;
    public static Account account;

    public static Account getAccount() {
        return account;
    }

    public static void setAccount(Account account) {
        Session.account = account;
    }

    public static void logout() {
        account = null;
    }
}
