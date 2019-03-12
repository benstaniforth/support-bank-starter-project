package training.supportbank;

import java.math.BigDecimal;

public class Account {

    private String name;
    private BigDecimal balance;

    public Account(String name) {
        this.name = name;
        this.balance = BigDecimal.valueOf(0);
    }

    public String getName() {
        return name;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void updateBalance(Transaction transaction) {
        if (transaction.getTransactionFrom().equals(name)) {
            balance = balance.subtract(transaction.getTransactionAmount());
        } else if (transaction.getTransactionTo().equals(name)) {
            balance = balance.add(transaction.getTransactionAmount());
        }
    }
}
