package training.supportbank;

import java.util.ArrayList;
import java.util.List;

public class Bank {

    private List<Account> accounts;

    public Bank() {
        this.accounts = new ArrayList<>();
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void includeTransaction(Transaction transaction) {
        Account fromAccount = accountWithName(transaction.getFromAccount());
        Account toAccount = accountWithName(transaction.getToAccount());
        fromAccount.updateBalanceIncludeTransaction(transaction);
        toAccount.updateBalanceIncludeTransaction(transaction);
    }

    public Account accountWithName(String name) {
        for (Account individualAccount : accounts) {
            if (individualAccount.getName().equals(name)){
                return individualAccount;
            }
        }

        Account newAccount = new Account(name);
        accounts.add(newAccount);
        return newAccount;
    }
}
