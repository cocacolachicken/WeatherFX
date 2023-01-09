package weather;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JacksonXmlRootElement(localName = "riseSet")
public class RiseSet {
    @JacksonXmlProperty(localName = "disclaimer")
    private String disclaimer;
    @JacksonXmlProperty(localName = "dateTime")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<DateTime> dateTime;

    private Map<String, DateTime> map;

    public DateTime getRiseSet (String accessor) {
        if (this.map == null) {
            this.map = new HashMap<>();
            for (DateTime d : dateTime) {
                if (d.getZone().equals("UTC")) {
                    map.put(d.getName(), d);
                }
            }
        }

        return map.get(accessor);
    }
}
