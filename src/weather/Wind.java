package weather;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class Wind {
    @JacksonXmlProperty(localName = "speed")
    private Speed speed;
    @JacksonXmlProperty(localName = "gust")
    private Gust gust;@JacksonXmlProperty(localName = "direction")
    private String direction;
    @JacksonXmlProperty(localName = "bearing")
    private Measurement bearing;
    @JacksonXmlProperty(localName = "index")
    private int index;
    @JacksonXmlProperty(localName = "rank")
    private String rank;

    public double getSpeed() {
        return speed.getSpeed();
    }

    public Gust getGust() {
        return gust;
    }

    public String getSpeedUnits () {return speed.getUnits();}

    public String getDirection() {
        return direction;
    }

    public Measurement getBearing() {
        return bearing;
    }

    public int getIndex() {
        return index;
    }

    public String getRank() {
        return rank;
    }
}
