package weather;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;

import java.util.ArrayList;
import java.util.List;

public class WindChill {
    @JacksonXmlText
    private double units;
    @JacksonXmlProperty(localName = "unitType")
    private String unitType;
    @JacksonXmlProperty(localName = "calculated")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<Calculated> calculated = new ArrayList<>();
    @JacksonXmlProperty(localName = "frostbite")
    private String frostbite;
    @JacksonXmlProperty(localName = "textSummary")
    private TextSummary textSummary;

    public String returnTextSummary () {
        return textSummary.getTextSummary();
    }


    public String getUnitType() {
        return unitType;
    }

    public List<Calculated> getCalculated() {
        return calculated;
    }

    public String getFrostbite() {
        return frostbite;
    }

    public double getUnits () {return units;}
}
class Calculated extends Measurement {
    @JacksonXmlProperty(localName = "index")
    private int index;
}