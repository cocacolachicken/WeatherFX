package weather;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@JacksonXmlRootElement(localName = "dateTime")
public class DateTime {
    @JacksonXmlProperty(isAttribute = true)
    private String name;
    @JacksonXmlProperty(isAttribute = true)
    private String zone;
    @JacksonXmlProperty(isAttribute = true)
    private String UTCOffset;
    @JacksonXmlProperty(localName = "year")
    private int year;
    @JacksonXmlProperty(localName = "month")
    private Month month;
    @JacksonXmlProperty(localName = "day")
    private Day day;
    @JacksonXmlProperty(localName = "hour")
    private int hour;
    @JacksonXmlProperty(localName = "minute")
    private int minute;
    @JacksonXmlProperty(localName = "timeStamp")
    private String timeStamp;
    @JacksonXmlProperty(localName = "textSummary")
    private String textSummary;

    public String getName() {
        return name;
    }

    public String getZone() {
        return zone;
    }

    public String getUTCOffset() {
        return UTCOffset;
    }

    public int getYear() {
        return year;
    }

    public String getMonthName() {
        return month.getName();
    }

    public int getMonth () {
        return month.getMonth();
    }

    public int getDay() {
        return day.getDay();
    }

    public String getDayName() {
        return day.getName();
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public String getTextSummary() {
        return textSummary;
    }


    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
    public static DateTimeFormatter formatterWithSeconds = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
    public LocalDateTime toLocalDateTime () {
        try {
            LocalDateTime a = LocalDateTime.parse(timeStamp, formatter);
            return a;
        } catch (DateTimeParseException e) {
            LocalDateTime a = LocalDateTime.parse(timeStamp, formatterWithSeconds);
            return a;
        }
    }
}

class Month {
    @JacksonXmlText
    private Integer month;
    @JacksonXmlProperty(isAttribute = true, localName = "name")
    private String name;

    public Integer getMonth() {
        return month;
    }

    public String getName() {
        return name;
    }
}

class Day {
    @JacksonXmlText
    private Integer day;
    @JacksonXmlProperty(isAttribute = true, localName = "name")
    private String name;

    public Integer getDay() {
        return day;
    }

    public String getName() {
        return name;
    }
}