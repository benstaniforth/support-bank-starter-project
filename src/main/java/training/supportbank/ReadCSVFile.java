package training.supportbank;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.spi.LoggerRegistry;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ReadCSVFile {
    private static final Logger LOGGER = LogManager.getLogger();
    public static List<Transaction> getTransactionsFromFile (String filename) throws IOException {
        LOGGER.info("About to read CSV lines");
        List<String> csvFile = new ArrayList<>(Files.readAllLines(Paths.get(filename)));
        List<Transaction> allTransactions = new ArrayList<>();
        LOGGER.info("About to convert individual lines");
        for (int i = 1; i < csvFile.size(); i++) {
            allTransactions.add(convertLineToTransaction(csvFile.get(i)));
        }

        return allTransactions;

    }


    private static Transaction convertLineToTransaction(String line) {

        String[] csvLine = line.split(",");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LOGGER.debug(line);
        Transaction individualTransaction = new Transaction(LocalDate.parse(csvLine[0], formatter),
                csvLine[1], csvLine[2], csvLine[3], new BigDecimal(csvLine[4]));

        return individualTransaction;
    }

}
