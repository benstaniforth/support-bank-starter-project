package training.supportbank;


import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String args[]) throws IOException {



        Bank supportBank = new Bank();
        List<Transaction> transactions = ReadCSVFile.getTransactionsFromFile("DodgyData.csv");
        for (Transaction transaction : transactions) {
            supportBank.includeTransaction(transaction);
        }


        String input = requestString("What would you like to do. You can choose from: List All Accounts, List [Account]");
        if (input.equalsIgnoreCase("list all accounts")) {
            listAllAccounts(supportBank);
        } else if (input.contains("list")) {
            listSingleAccount(supportBank,input.substring(5));
        }
    }


    private static void listAllAccounts(Bank bank) {
        List <Account> listOfAccounts = bank.getAccounts();
        for (int i = 0; i < listOfAccounts.size(); i++) {
            Account individualAccount = listOfAccounts.get(i);
            System.out.println("Name: " + individualAccount.getName() +
                               " Balance: " + individualAccount.getBalance());
        }
    }


    private static void listSingleAccount(Bank bank, String accountName) {
        Account individualsAccount = bank.accountWithName(accountName);
        for (Transaction transaction : individualsAccount.getTransactions()) {
            System.out.println(transaction.getDate() + " : " + transaction.getTransactionFrom() +
                                                       " owes " + transaction.getTransactionTo() +
                                                       " for " + transaction.getNarrative() +
                                                       " " + NumberFormat.getCurrencyInstance().format(transaction.getTransactionAmount()));
        }
    }
// Sam is making it up as he goes

    private static String requestString(String msg){
        Scanner scanner = new Scanner(System.in);
        System.out.println(msg);
        System.out.print(">");
        return scanner.nextLine();
    }
}
