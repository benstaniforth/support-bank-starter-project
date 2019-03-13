package training.supportbank;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Logger LOGGER = LogManager.getLogger();
    public static void main(String args[]) throws IOException {
        LOGGER.info("SupportBank starting up!");
        LOGGER.info("Creating a new bank");

        Bank supportBank = new Bank();

        LOGGER.info("Reading from csv file DodgyData.csv");

        //List<Transaction> transactions = ReadCSVFile.getTransactionsFromCSV("DodgyData.csv");
        List<Transaction> transactions = ReadingJSONFile.getTransactionsFromJSON("DodgyData.csv");
        LOGGER.info("Cycling through Transactions from CSV file");
        for (Transaction transaction : transactions) {
            LOGGER.debug("Date: " + transaction.getDate() +
                            " From: " + transaction.getFromAccount() +
                            " To: " + transaction.getToAccount() +
                            " Narrative: " + transaction.getNarrative() +
                            " Amount: " + transaction.getAmount());
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
            System.out.println(transaction.getDate() + " : " + transaction.getFromAccount() +
                                                       " owes " + transaction.getToAccount() +
                                                       " for " + transaction.getNarrative() +
                                                       " " + NumberFormat.getCurrencyInstance().format(transaction.getAmount()));
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
