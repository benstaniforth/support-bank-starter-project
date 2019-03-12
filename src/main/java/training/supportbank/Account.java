package training.supportbank;

public class Account {

    String name;
    int balance;

    public Account(String name) {
        this.name = name;
        this.balance = 0;
    }

    public String getName() {
        return name;
    }

    public int getBalance() {
        return balance;
    }
}
