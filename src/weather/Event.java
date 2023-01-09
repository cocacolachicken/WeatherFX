package weather;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Event {
    @JacksonXmlProperty(localName = "type")
    private String type;
    @JacksonXmlProperty(localName = "priority")
    private String priority;
    @JacksonXmlProperty(localName = "description")
    private String description;
    @JacksonXmlProperty(localName = "dateTime")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<DateTime> dateTime = new ArrayList<>();

    public String getType() {
        return type;
    }

    public String getPriority() {
        return priority;
    }

    public String getDescription() {
        return description;
    }// Lists of DateTime to map

    private Map<String, DateTime> map;

    public DateTime getDateTime(String str) {
        if (map == null) {
            map = new HashMap<>();
            for (DateTime x : dateTime) {
                if (x.getZone().equals("UTC"))
                    map.put(x.getZone(), x);
                else map.put("LOC", x); // Local Time Zone
            }
        }
        return map.get(str);
    }
}
