package weather;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class Pressure extends Measurement {
    @JacksonXmlProperty(localName = "change")
    private double change;
    @JacksonXmlProperty(localName = "tendency")
    private String tendency;
}
