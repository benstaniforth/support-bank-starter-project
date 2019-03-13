package training.supportbank;

import java.time.LocalDate;

public class ReadXMLFile {

    private static LocalDate convertDate (int numberOfDays) {
        LocalDate day0 = LocalDate.of(1899,12,30);
        return day0.plusDays(numberOfDays);
    }
}
