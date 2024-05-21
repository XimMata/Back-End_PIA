package com.backend.fcfm.PIA.Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateMapper {

    public static Date parseDate(String dateStr) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        try {
            return formatter.parse(dateStr);
        } catch (ParseException e) {
            return null;
        }

    }

}
