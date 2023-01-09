package weather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

@JsonIgnoreProperties({"format"})
public class HourlyForecast {
    @JacksonXmlProperty(localName = "dateTimeUTC")
    private String dateTimeUTC;
    @JacksonXmlProperty(localName = "condition")
    private String condition;
    @JacksonXmlProperty(localName = "temperature")
    private Temperature temperature;
    @JacksonXmlProperty(localName = "lop")
    private Pop lop;
    @JacksonXmlProperty(localName = "windChill")
    private WindChill windChill;
    @JacksonXmlProperty(localName = "humidex")
    private Humidex humidex;
    @JacksonXmlProperty(localName = "wind")
    private Wind wind;
    @JacksonXmlProperty(localName = "iconCode")
    private IconCode iconCode;

    public int getIconCode() {
        return iconCode.getCode();
    }

    public String getDateTimeUTC() {
        return dateTimeUTC;
    }

    public String getCondition() {
        return condition;
    }

    public Temperature getTemperature() {
        return temperature;
    }

    public Pop getLop() {
        return lop;
    }

    public WindChill getWindChill() {
        return windChill;
    }

    public Humidex getHumidex() {
        return humidex;
    }

    public Wind getWind() {
        return wind;
    }
}
