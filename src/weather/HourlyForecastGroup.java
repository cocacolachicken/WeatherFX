package weather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HourlyForecastGroup {
    @JacksonXmlProperty(localName = "dateTime")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<DateTime> forecastIssue = new ArrayList<>();
    private List<HourlyForecast> forecasts = new ArrayList<>();

    // This one only gets one hourlyForecast tag if it's not like this
    @JsonSetter(value = "hourlyForecast")
    public void setForecast (HourlyForecast hourlyForecast) {
        this.forecasts.add(hourlyForecast);
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

    public List<HourlyForecast> getForecasts() {
        return forecasts;
    }
}

