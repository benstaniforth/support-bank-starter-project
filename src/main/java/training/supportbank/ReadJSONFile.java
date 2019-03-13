package training.supportbank;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

public class ReadJSONFile {
    private static final Logger LOGGER = LogManager.getLogger();
    public static List<Transaction> getTransactionsFromJSON (String fileName) throws FileNotFoundException {
        try {
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.registerTypeAdapter(LocalDate.class, (JsonDeserializer<LocalDate>) (jsonElement, type, jsonDeserializationContext) ->
                    LocalDate.parse(jsonElement.getAsString(), DateTimeFormatter.ofPattern("yyyy-MM-dd"))
            );
            Gson gson = gsonBuilder.create();
            JsonReader reader = new JsonReader(new FileReader(fileName));
            Transaction[] data = gson.fromJson(reader, Transaction[].class);
            return Arrays.asList(data);
        } catch (JsonSyntaxException e) {
            if (e.getCause() instanceof NumberFormatException) {
                LOGGER.error("NumberFormatException on part of line - Should be converted into BigDecimal. Line skipped");
                System.out.println("invalid data found in line. Transaction skipped. please check data and re run");
            }
            throw e;
        } catch (DateTimeParseException e) {
            LOGGER.error("DateTimeException on part of line - Should be in date in format yyyy-MM-dd. Line skipped");
            System.out.println("invalid data found in line. Transaction skipped. please check data and re run");
            throw e;
        }
    }
}
