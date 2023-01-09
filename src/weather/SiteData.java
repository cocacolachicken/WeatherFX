package weather;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;

import javax.xml.soap.Text;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


// LOC stands for local time zone
// This is for Jackson to deserialize the XML into a POJO. I learned a lot yet I'm still very confused by it all

public class SiteData {
    @JacksonXmlProperty(isAttribute = true, localName = "noNamespaceSchemaLocation")
    private String noNamespaceSchemaLocation;
    private String license;
    @JacksonXmlProperty(localName = "dateTime")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<DateTime> xmlCreation = new ArrayList<>();
    private Location location;
    @JacksonXmlProperty(localName = "warnings")
    private Warnings warnings;
    @JacksonXmlProperty(localName = "currentConditions")
    private CurrentConditions currentconditions;
    @JacksonXmlProperty(localName = "forecastGroup")
    private ForecastGroup forecastGroup;
    @JacksonXmlProperty(localName = "hourlyForecastGroup")
    private HourlyForecastGroup hourlyforecastgroup;
    @JacksonXmlProperty(localName = "yesterdayConditions")
    private YesterdayConditions yesterdayconditions;
    @JacksonXmlProperty(localName = "riseSet")
    private RiseSet riseSet;
    @JacksonXmlProperty(localName = "almanac")
    private Almanac almanac;

    // Lists of DateTime to map

    private Map<String, DateTime> dateTime;

    public DateTime getXMLCreation (String str) {
        if (dateTime == null) {
            dateTime = new HashMap<>();
            for (DateTime x : xmlCreation) {
                if (x.getZone().equals("UTC"))
                    dateTime.put(x.getZone(), x);
                else dateTime.put("LOC", x); // Local Time Zone
            }
        }
        return dateTime.get(str);
    }

    public String getLicense() {return license;}
    public Location getLocation() {return location;}
    public Warnings getWarnings() {return warnings;}
    public CurrentConditions getCurrentconditions() {return currentconditions;}
    public ForecastGroup getForecastgroup() {return forecastGroup;}
    public HourlyForecastGroup getHourlyforecastgroup() {return hourlyforecastgroup;}
    public YesterdayConditions getYesterdayconditions() {return yesterdayconditions;}
    public RiseSet getRiseSet() {return riseSet;}
    public Almanac getAlmanac() {return almanac;}
}

class Name {
    @JacksonXmlText
    private String name;
    @JacksonXmlProperty(localName = "code")
    private String code;
    @JacksonXmlProperty(localName = "lat")
    private String lat;
    @JacksonXmlProperty(localName = "lon")
    private String lon;

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public String getLat() {
        return lat;
    }

    public String getLon() {
        return lon;
    }
}

class Station {
    @JacksonXmlText
    private String name;
    @JacksonXmlProperty(localName = "code")
    private String code;
    @JacksonXmlProperty(localName = "lat")
    private String lat;
    @JacksonXmlProperty(localName = "lon")
    private String lon;

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public String getLat() {
        return lat;
    }

    public String getLon() {
        return lon;
    }
}

class RegionalNormals {
    public String textSummary;
    @JacksonXmlProperty(localName = "temperature")
    @JacksonXmlElementWrapper(useWrapping = false)
    public List<Temperature> highLow = new ArrayList<>();
}

@JsonIgnoreProperties({"iconCode"})
class AbbreviatedForecast {
    @JacksonXmlProperty(localName = "pop")
    public Pop pop;
    @JacksonXmlProperty(localName = "textSummary")
    private TextSummary txt;

    public String returnTextSummary () {
        return txt.getTextSummary();
    }
}

@JsonIgnoreProperties({""})
class TextSummary {
    @JacksonXmlText
    private String textSummary;
    public String getTextSummary() {
        return textSummary;
    }
}

class YesterdayConditions {
    @JacksonXmlProperty(localName = "temperature")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<Temperature> highLow;
    @JacksonXmlProperty(localName = "precip")
    private Measurement precip;

    private Map<String, Temperature> temperatures;

    public Temperature returnHighOrLow (String s) { // For the sake of readability
        if (temperatures == null) {
            temperatures = new HashMap<>();
            for (Temperature t : highLow) {
                temperatures.put(t.getCategory(), t);
            }
        }
        return temperatures.get(s);
    }

    public Measurement getPrecip() {
        return precip;
    }
}

class Almanac {
    @JacksonXmlProperty(localName = "temperature")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<AlmanacTemperature> temperature;
    @JacksonXmlProperty(localName = "precipitation")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<AlmanacPrecipitation> precipitation;
    @JacksonXmlProperty(localName = "pop")
    private Pop pop;
}


