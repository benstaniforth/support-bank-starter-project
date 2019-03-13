package training.supportbank;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.stream.JsonReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.LocalDate;
import java.util.List;

public class ReadingJSONFile {
    public static List<Transaction> getTransactionsFromJSON (String fileName) throws FileNotFoundException {

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LocalDate.class, (JsonDeserializer<LocalDate>) (jsonElement, type, jsonDeserializationContext) ->
                // Convert jsonElement to a LocalDate here...
        );
        Gson gson = gsonBuilder.create();
        Gson gson = new Gson();
        JsonReader reader = new JsonReader(new FileReader(fileName));
        Transaction[] data = gson.fromJson(reader, Transaction[].class);
        System.out.println(data);
    }
}
