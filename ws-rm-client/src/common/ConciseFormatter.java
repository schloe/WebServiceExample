package common;

import java.util.logging.LogRecord;
import java.util.logging.SimpleFormatter;

public class ConciseFormatter extends SimpleFormatter {
    public synchronized String format(LogRecord record) {
        String longForm = super.format(record);
        String shortForm = longForm.indexOf("INFO: ") > 0
                           ? longForm.substring(longForm.indexOf("INFO: ") + 6)
                           : longForm;
        return shortForm;
    }
}
