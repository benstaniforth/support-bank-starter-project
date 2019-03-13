package training.supportbank;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static Bank supportBank = new Bank();

    private static final Logger LOGGER = LogManager.getLogger();
    public static void main(String args[]) throws IOException {
        LOGGER.info("SupportBank starting up!");
        LOGGER.info("Creating a new bank");

        boolean switchEnd = false;

        while (!switchEnd) {
            String input = requestString("What would you like to do. You can choose from: List All Accounts, List [Account], Import File");
            if (input.equalsIgnoreCase("list all accounts")) {
                listAllAccounts();
            } else if (input.contains("list")) {
                listSingleAccount(input.substring(5));
            } else if (input.startsWith("Import File")) {
                importFile(input.substring("Import File ".length()));
            } else if (input.equalsIgnoreCase("end")) {
                switchEnd = true;
            }
        }
    }


    private static void listAllAccounts() {
        List <Account> listOfAccounts = supportBank.getAccounts();
        for (int i = 0; i < listOfAccounts.size(); i++) {
            Account individualAccount = listOfAccounts.get(i);
            System.out.println("Name: " + individualAccount.getName() +
                               " Balance: " + individualAccount.getBalance());
        }
    }


    private static void listSingleAccount(String accountName) {
        Account individualsAccount = supportBank.accountWithName(accountName);
        for (Transaction transaction : individualsAccount.getTransactions()) {
            System.out.println(transaction.getDate() + " : " + transaction.getFromAccount() +
                                                       " owes " + transaction.getToAccount() +
                                                       " for " + transaction.getNarrative() +
                                                       " " + NumberFormat.getCurrencyInstance().format(transaction.getAmount()));
        }
    }


    private static void importFile (String filename) throws IOException {
        List<Transaction> transactions = new ArrayList<>();
        try {
            if (filename.endsWith(".csv")) {
                transactions = ReadCSVFile.getTransactionsFromCSV(filename);
            } else if (filename.endsWith(".json")) {
                transactions = ReadJSONFile.getTransactionsFromJSON(filename);
            } else if (filename.endsWith(".xml")) {
//            transactions = ReadCSVFile.getTransactionsFromCSV(filename);
            } else {
                System.out.println("Invalid File type. Please try again with a .csv, .json or .xml");
            }
            loadBank(transactions);
        } catch (FileNotFoundException e) {
            System.out.println("Filename not found. Please enter a valid Filename.");
        }
    }

    private static void loadBank(List <Transaction> transactions) {
        LOGGER.info("Cycling through Transactions from file");
        for (Transaction transaction : transactions) {
            LOGGER.debug("Date: " + transaction.getDate() +
                    " From: " + transaction.getFromAccount() +
                    " To: " + transaction.getToAccount() +
                    " Narrative: " + transaction.getNarrative() +
                    " Amount: " + transaction.getAmount());
            supportBank.includeTransaction(transaction);
        }
    }

    private static String requestString(String msg){
        Scanner scanner = new Scanner(System.in);
        System.out.println(msg);
        System.out.print(">");
        return scanner.nextLine();
    }
}
