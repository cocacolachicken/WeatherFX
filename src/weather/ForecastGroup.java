package weather;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ForecastGroup {
    @JacksonXmlProperty(localName = "dateTime")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<DateTime> forecastIssue = new ArrayList<>();
    @JacksonXmlProperty(localName = "regionalNormals")
    private RegionalNormals regionalNormals;
    private List<Forecast> forecasts = new ArrayList<>();

    @JsonSetter(value = "forecast")
    public void addForecast (Forecast forecast) {
        this.forecasts.add(forecast);
    }

    // Lists of DateTime to map

    private Map<String, DateTime> dateTime;

    public DateTime getForecastIssue (String str) {
        if (dateTime == null) {
            dateTime = new HashMap<>();
            for (DateTime x : forecastIssue) {
                if (x.getZone().equals("UTC"))
                    dateTime.put(x.getZone(), x);
                else dateTime.put("LOC", x); // Local Time Zone
            }
        }
        return dateTime.get(str);
    }

    public RegionalNormals getRegionalNormals() {
        return regionalNormals;
    }

    public List<Forecast> getForecasts() {
        return forecasts;
    }
}

