package training.supportbank;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Transaction {

    private LocalDate date;
    private String transactionFrom;
    private String transactionTo;
    private String narrative;
    private BigDecimal transactionAmount;

    public Transaction(LocalDate date, String transactionFrom, String transactionTo, String narrative, BigDecimal transactionAmount) {
        this.date = date;
        this.transactionFrom = transactionFrom;
        this.transactionTo = transactionTo;
        this.narrative = narrative;
        this.transactionAmount = transactionAmount;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getTransactionFrom() {
        return transactionFrom;
    }

    public String getTransactionTo() {
        return transactionTo;
    }

    public String getNarrative() {
        return narrative;
    }

    public BigDecimal getTransactionAmount() {
        return transactionAmount;
    }
}


