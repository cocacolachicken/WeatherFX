package weather;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;

public class Forecast {
    @JacksonXmlProperty(localName = "period")
    private Period period;
    @JacksonXmlProperty(localName = "textSummary")
    private String textSummary;
    @JacksonXmlProperty(localName = "cloudPrecip")
    private CloudPrecipitation cloudPrecipitation;
    @JacksonXmlProperty(localName = "abbreviatedForecast")
    private AbbreviatedForecast abbreviatedForecast;
    @JacksonXmlProperty(localName = "temperatures")
    private Temperatures temperatures;
    @JacksonXmlProperty(localName = "winds")
    private Winds winds;
    @JacksonXmlProperty(localName = "humidex")
    private Humidex humidex;
    @JacksonXmlProperty(localName = "precipitation")
    private Precipitation precipitation;
    @JacksonXmlProperty(localName = "windChill")
    private WindChill windChill;
    @JacksonXmlProperty(localName = "relativeHumidity")
    private Measurement relativeHumidity;
    @JacksonXmlProperty(localName = "uv")
    private UV uv;
    @JacksonXmlProperty(localName = "visibility")
    private Visibility visibility;
    @JacksonXmlProperty(localName = "snowLevel")
    private TextSummary snowLevel;

    public String getPeriod() {
        return period.getPeriod();
    }

    public String getTextForecast () {
        return period.getTextForecast();
    }

    public String getTextSummary() {
        return textSummary;
    }

    public CloudPrecipitation getCloudPrecipitation() {
        return cloudPrecipitation;
    }

    public AbbreviatedForecast getAbbreviatedForecast() {
        return abbreviatedForecast;
    }

    public Temperatures getTemperatures() {
        return temperatures;
    }

    public Winds getWinds() {
        return winds;
    }

    public Humidex getHumidex() {
        return humidex;
    }

    public Precipitation getPrecipitation() {
        return precipitation;
    }

    public WindChill getWindChill() {
        return windChill;
    }

    public Measurement getRelativeHumidity() {
        return relativeHumidity;
    }

    public UV getUv() {
        return uv;
    }

    public Visibility getVisibility() {
        return visibility;
    }

    public TextSummary getSnowLevel() {
        return snowLevel;
    }
}

class Period {
    @JacksonXmlProperty(localName = "textForecastName")
    private String textForecast;
    @JacksonXmlText
    private String period;

    public String getTextForecast() {
        return textForecast;
    }

    public String getPeriod() {
        return period;
    }
}

class CloudPrecipitation {
    @JacksonXmlProperty(localName = "textSummary")
    private TextSummary txt;
    public String toString () {
        return txt.getTextSummary();
    }
}