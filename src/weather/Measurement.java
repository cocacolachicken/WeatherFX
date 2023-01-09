package weather;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.dataformat.xml.annotation.*;

import java.util.ArrayList;
import java.util.List;

// @Todo Work on encapsulation, as it's messy here

public class Measurement {
    @JacksonXmlText
    private double measurement;
    @JacksonXmlProperty(localName = "unitType")
    private String unitType;
    @JacksonXmlProperty(localName = "units")
    private String units;
    @JacksonXmlProperty(localName = "class")
    private String category;

    public double getMeasurement() {
        return measurement;
    }

    public String getUnitType() {
        return unitType;
    }

    public String getUnits() {
        return units;
    }

    public String getCategory () {
        return category;
    }
}

// Temperature

class Temperatures {
    @JacksonXmlProperty(localName = "temperature")
    private Temperature prediction;
    @JacksonXmlProperty(localName = "textSummary")
    private TextSummary textSummary;

    public String returnTextSummary () {
        return textSummary.getTextSummary();
    }
}

class AlmanacTemperature extends Measurement {
    @JacksonXmlProperty(localName = "period")
    private String period;
    @JacksonXmlProperty(localName = "year")
    private int year;
}

// Atmospheric measures

class OtherVisib {
    @JacksonXmlProperty(localName = "cause")
    private String cause;
    @JacksonXmlProperty(localName = "textSummary")
    private TextSummary textSummary;

    public String returnTextSummary () {
        return textSummary.getTextSummary();
    }
}

class Visibility extends Measurement {
    @JacksonXmlProperty(localName = "otherVisib")
    private OtherVisib otherVisib;
    @JacksonXmlProperty(localName = "windVisib")
    private WindVisib windVisib;
}

class WindVisib {
    @JacksonXmlProperty(localName = "cause")
    private String cause;
    @JacksonXmlProperty(localName = "textSummary")
    private TextSummary textSummary;

    public String returnTextSummary () {
        return textSummary.getTextSummary();
    }
}

class Humidex {
    @JacksonXmlProperty(localName = "unitType")
    private String unitType;
}

// Wind

@JsonIgnoreProperties("")
class Speed {
    private double speed;
    @JacksonXmlProperty(localName = "unitType")
    private String unitType;
    @JacksonXmlProperty(localName = "units")
    private String units;



    @JsonSetter("speed") // This is here because sometimes the speeds are listed like "calm"
    public void setSpeed (String s) {
        speed = Double.parseDouble(s);
    }

    public double getSpeed () {return speed;}
    public String getUnits () {return units;}
}

class Gust {
    @JacksonXmlText
    private double speed;
    @JacksonXmlProperty(localName = "unitType")
    private String unitType;
    @JacksonXmlProperty(localName = "units")
    private String units;
}

class Winds {
    @JacksonXmlProperty(localName="wind")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<Wind> wind;
    @JacksonXmlProperty(localName = "textSummary")
    private TextSummary textSummary;

    public String returnTextSummary () {
        return textSummary.getTextSummary();
    }

    public List<Wind> getWind () {
        return wind;
    }
}

// Sun
class UV {
    @JacksonXmlProperty(localName="category")
    private String category;
    @JacksonXmlProperty(localName="index")
    private double index;
    @JacksonXmlProperty(localName="textSummary")
    private String textSummary;
}

// Precipitation

class Precipitation {
    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "precipType")
    private List<PrecipType> precipTypes = new ArrayList<>();
    @JacksonXmlProperty(localName = "accumulation")
    private Accumulation accumulation;
    @JacksonXmlProperty(localName = "textSummary")
    private TextSummary textSummary;

    public String returnTextSummary () {
        return textSummary.getTextSummary();
    }
}

class PrecipType {
    @JacksonXmlText
    private String type;
    @JacksonXmlProperty(localName = "start")
    private int start;
    @JacksonXmlProperty(localName = "end")
    private int end;
}

class AlmanacPrecipitation extends Measurement {
    @JacksonXmlProperty(localName = "class")
    private String category;
    @JacksonXmlProperty(localName = "period")
    private String period;
    @JacksonXmlProperty(localName = "year")
    private int year;
}

class Pop {
    @JacksonXmlText
    private double pop;
    @JacksonXmlProperty(localName = "units")
    private String units;
    @JacksonXmlProperty(localName = "category")
    private String category;
}

class Accumulation {
    @JacksonXmlProperty(localName = "name")
    private String name;
    @JacksonXmlProperty(localName = "amount")
    private Measurement amount;
}