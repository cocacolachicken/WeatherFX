package weather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CurrentConditions {
    @JacksonXmlProperty(localName = "station")
    private Station station;
    @JacksonXmlProperty(localName = "condition")
    private String condition;
    @JacksonXmlProperty(localName = "dateTime")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<DateTime> observation = new ArrayList<>();
    @JacksonXmlProperty(localName = "wind")
    private Wind wind;
    @JacksonXmlProperty(localName = "temperature")
    private Temperature temperature;
    @JacksonXmlProperty(localName = "dewpoint")
    private Measurement dewpoint;
    @JacksonXmlProperty(localName = "windChill")
    private WindChill windChill;
    @JacksonXmlProperty(localName = "pressure")
    private Pressure pressure;
    @JacksonXmlProperty(localName = "visibility")
    private Measurement visibility;
    @JacksonXmlProperty(localName = "relativeHumidity")
    private Measurement humidity;
    @JacksonXmlProperty(localName = "iconCode")
    private IconCode iconCode;

    public int getIconCode() {
        return iconCode.getCode();
    }

    public Station getStation() {
        return station;
    }

    public String getCondition() {
        return condition;
    }

    public Wind getWind() {
        return wind;
    }

    public Temperature getTemperature() {
        return temperature;
    }

    public Measurement getDewpoint() {
        return dewpoint;
    }

    public WindChill getWindChill() {
        return windChill;
    }

    public Pressure getPressure() {
        return pressure;
    }

    public Measurement getVisibility() {
        return visibility;
    }

    public Measurement getHumidity() {
        return humidity;
    }

    // Lists of DateTime to map

    private Map<String, DateTime> dateTime;

    public DateTime getObservation(String str) {
        if (dateTime == null) {
            dateTime = new HashMap<>();
            for (DateTime x : observation) {
                if (x.getZone().equals("UTC"))
                    dateTime.put(x.getZone(), x);
                else dateTime.put("LOC", x); // Local Time Zone
            }
        }
        return dateTime.get(str);
    }
}


