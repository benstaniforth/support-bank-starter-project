package training.supportbank;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String args[]) throws IOException {

        Bank supportBank = new Bank();
        List<Transaction> transactions = ReadCSVFile.getTransactionsFromFile("Transactions2014.csv");
        for (Transaction transaction : transactions) {
            supportBank.includeTransaction(transaction);
        }
        System.out.println("Bank is full");
    }
}
