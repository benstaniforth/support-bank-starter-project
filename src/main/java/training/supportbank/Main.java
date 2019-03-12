package training.supportbank;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String args[]) throws IOException {
        List <Transaction> allTransactions = ReadCSVFile.getTransactionsFromFile("Transactions2014.csv");

        
    }



}
