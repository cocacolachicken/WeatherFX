package weather;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;

public class IconCode {
    @JacksonXmlText
    private int x;
    @JacksonXmlProperty(localName = "format")
    private String format;

    public int getCode () {
        return x;
    }
}
