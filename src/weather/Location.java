package weather;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class Location {
    @JacksonXmlProperty(localName = "continent")
    private String continent;
    @JacksonXmlProperty(localName = "country")
    private String country;
    @JacksonXmlProperty(localName = "province")
    private String province;
    @JacksonXmlProperty(localName = "name")
    private Name city;
    @JacksonXmlProperty(localName = "region")
    private String region;

    public String getContinent() {
        return continent;
    }

    public String getCountry() {
        return country;
    }

    public String getProvince() {
        return province;
    }

    public String getLat() {
        return city.getLat();
    }

    public String getLon() {
        return city.getLon();
    }

    public String getName() {
        return city.getName();
    }

    public String getCode() {
        return city.getCode();
    }

    public String getRegion() {
        return region;
    }
}
