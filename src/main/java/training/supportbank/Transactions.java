package training.supportbank;

import java.time.LocalDate;

public class Transactions {

    LocalDate date;
    String transactionFrom;
    String transactionTo;
    String narrative;
    int transactionAmount;

    public Transactions(LocalDate date, String transactionFrom, String transactionTo, String narrative, int transactionAmount) {
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

    public int getTransactionAmount() {
        return transactionAmount;
    }
}


