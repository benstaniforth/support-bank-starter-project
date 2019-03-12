package training.supportbank;

import java.util.ArrayList;
import java.util.List;

public class Bank {

    private List<Account> accounts;
    private List<Transaction> transactions;

    public Bank() {
        this.accounts = new ArrayList<>();
        this.transactions = new ArrayList<>();
    }

    public void includeTransaction(Transaction transaction) {
        Account fromAccount = accountWithName(transaction.getTransactionFrom());
        Account toAccount = accountWithName(transaction.getTransactionTo());
        fromAccount.updateBalance(transaction);
        toAccount.updateBalance(transaction);
        transactions.add(transaction);
    }

    private Account accountWithName(String name) {
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
