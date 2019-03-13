package training.supportbank;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.spi.LoggerRegistry;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.DataFormatException;

public class ReadCSVFile {
    private static final Logger LOGGER = LogManager.getLogger();
    public static List<Transaction> getTransactionsFromFile (String filename) throws IOException {

        LOGGER.info("About to read CSV File");

        List<String> csvFile = new ArrayList<>(Files.readAllLines(Paths.get(filename)));
        List<Transaction> allTransactions = new ArrayList<>();

        LOGGER.info("About to convert individual lines");

        for (int i = 1; i < csvFile.size(); i++) {
            Transaction currentTransaction = convertLineToTransaction(csvFile.get(i));

            if (currentTransaction != null) {
                allTransactions.add(currentTransaction);
            }
        }

        return allTransactions;

    }


    private static Transaction convertLineToTransaction(String line) {

        String[] csvLine = line.split(",");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        try {
            Transaction individualTransaction = new Transaction(LocalDate.parse(csvLine[0], formatter),
                    csvLine[1], csvLine[2], csvLine[3], new BigDecimal(csvLine[4]));
            LOGGER.debug(line);
            return individualTransaction;
        }

        catch (NumberFormatException e){
            LOGGER.error("NumberFormatException on part of line: '" + csvLine[4] + "' - Should be converted into BigDecimal. Line skipped");
            System.out.println("invalid data found in line: " + line + ". Transaction skipped. please check data and re run");
            return null;
        }

        catch (DateTimeException e) {
            LOGGER.error("DateTimeException on part of line '" + csvLine[0] + "' - Should be in date in format dd/MM/yyyy. Line skipped");
            System.out.println("invalid data found in line: " + line + ". Transaction skipped. please check data and re run");
            return null;
        }
    }

}
