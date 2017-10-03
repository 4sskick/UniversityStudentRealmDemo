package nightroomcreation.id.realmdemo2.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by iand on 02/10/17.
 */

public class DateFormater {
    private static String DATE_PATTERN = "dd/MM/yyyy";

    public static String convertDateToString(Date date){
        return new SimpleDateFormat(DATE_PATTERN).format(date);
    }
}
