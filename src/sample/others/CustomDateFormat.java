package sample.others;

import javafx.scene.control.DatePicker;
import javafx.util.StringConverter;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

public class CustomDateFormat {

    /**  date time formatter  */
    /**
     * we use set converter because of reformat date picker format
     */

    public final List<String> pattern = Arrays.asList("dd/MM/yyyy");

    public void formatingDate(DatePicker datePicker) {
        datePicker.setConverter(new StringConverter<LocalDate>() {
            @Override
            public String toString(LocalDate date) {
                if (date != null) {
                    for (String pattern : pattern) {
                        try {
                            return DateTimeFormatter.ofPattern(pattern).format(date);
                        } catch (DateTimeException dte) {
                        }
                    }
                    System.out.println("Format Error");
                }
                return "";
            }

            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    for (String pattern : pattern) {
                        try {
                            return LocalDate.parse(string, DateTimeFormatter.ofPattern(pattern));
                        } catch (DateTimeParseException dtpe) {
                        }
                    }
                    System.out.println("Parse Error");
                }
                return null;
            }
        });

    }
}
