package training.supportbank;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Account {
    private static final Logger LOGGER = LogManager.getLogger();


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

        if (transaction.getFromAccount().equals(name)) {
            balance = balance.subtract(transaction.getAmount());
        } else if (transaction.getToAccount().equals(name)) {
            balance = balance.add(transaction.getAmount());
        }
        transactions.add(transaction);
    }
}
