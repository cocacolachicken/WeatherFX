package weather;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Warnings {
    @JacksonXmlProperty(localName = "url")
    private String url;
    @JsonIgnore
    private List<Event> events = new ArrayList<>();

    @JsonSetter("event")
    public void setList(Event event) {
        events.add(event);
    }

    public List<Event> getEvents () {
        return events;
    }

    public String getUrl() {
        return url;
    }
}



