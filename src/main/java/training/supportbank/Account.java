package training.supportbank;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Account {

    private String name;
    private BigDecimal balance;
    private List<Transaction> transactions;

    public Account(String name) {
        this.name = name;
        this.balance = BigDecimal.valueOf(0);
        this.transactions = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void updateBalanceIncludeTransaction(Transaction transaction) {
        if (transaction.getTransactionFrom().equals(name)) {
            balance = balance.subtract(transaction.getTransactionAmount());
        } else if (transaction.getTransactionTo().equals(name)) {
            balance = balance.add(transaction.getTransactionAmount());
        }
        transactions.add(transaction);
    }
}
