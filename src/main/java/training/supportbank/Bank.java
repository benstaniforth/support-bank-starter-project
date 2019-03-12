package training.supportbank;

public class Bank {

    Account account;
    Transaction transaction;

    public Bank(Account account, Transaction transaction) {
        this.account = account;
        this.transaction = transaction;
    }
}
