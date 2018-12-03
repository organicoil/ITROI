package ua.nure.publisher.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

/**
 * Tag to convert date to readable format
 *
 */
public class DateFormat extends TagSupport {
    private String month;
    private String date;
    public int doStartTag() throws JspException {
        try
        {
            JspWriter out = pageContext.getOut();
            int m = Integer.parseInt(month);
            LocalDate localDate;
            if (date != null) {
                localDate = LocalDate.parse(date);
            } else {
                localDate = LocalDate.now();
            }
            localDate = localDate.plusMonths(m);
            out.write(localDate.getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH) + " " + localDate.getYear());
        } catch(Exception e) {
            throw new JspException(e.getMessage());
        }
        return SKIP_BODY;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
