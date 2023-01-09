import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import org.geojson.Feature;
import weather.Event;
import weather.HourlyForecast;
import weather.WeatherGetter;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

// This class is where it draws everything and handles input

public class Scenes {
    public static int offset = 0;
    public static void drawMainWeather (GraphicsContext pen, int position, double mouseX, double mouseY) {
        pen.setTextAlign(TextAlignment.CENTER);
        pen.setFill(Color.WHITE);
        int localOffset = 0;

        // Displays weather data
        try {
            pen.setFont(Font.font("helvetica", 120));
            pen.fillText((int) weather.WeatherGetter.city.getCurrentconditions().getTemperature().getMeasurement() +
                    "°", 300, position);
            pen.setFont(Font.font("helvetica", 30));
            if (!weather.WeatherGetter.city.getCurrentconditions().getCondition().equals("")) {
                pen.fillText(weather.WeatherGetter.city.getCurrentconditions().getCondition(), 300, position + 42);

            } else pen.fillText("Condition unavailable", 300, position + 39);

            // This is for windchill
            if (weather.WeatherGetter.city.getCurrentconditions().getWindChill() != null) {
                pen.setFont(Font.font("helvetica", 20));
                pen.fillText("Feels like " + weather.WeatherGetter.city.getCurrentconditions().getWindChill().getUnits() + "°", 300, position + 80);
                localOffset += 24;
            }
        } catch (NullPointerException e) {
            pen.setFont(Font.font("helvetica", 36));
            pen.setFill(Color.INDIANRED);
            pen.fillText("Current weather data unavailable.\nCheck for warnings.", 300, position+40);
            localOffset += 22;
        }

        // Displays location data
        if (mouseX > 125 && mouseX < 475 && mouseY < position+85+localOffset && mouseY > position+60+localOffset) pen.setFill(Color.YELLOW);
            else pen.setFill(Color.WHITE);
        pen.setFont(Font.font("helvetica", 20));
        pen.fillText(weather.WeatherGetter.city.getLocation().getName(), 300, position+80+localOffset);

        //Displays time data
        pen.setFont(Font.font("helvetica", 14));
        pen.setFill(Color.WHITE);
        pen.fillText("Last updated " + weather.WeatherGetter.city.getXMLCreation("LOC").getHour() + ":" +
                String.format("%02d", weather.WeatherGetter.city.getXMLCreation("LOC").getMinute()) + " "
                        + weather.WeatherGetter.city.getXMLCreation("LOC").getZone(), 300, position+100+localOffset);
        pen.setFont(Font.font("helvetica", 50));

        // Reset data
        if (mouseX > 15 && mouseX < 55 && mouseY < 45 && mouseY > 10) pen.setFill(Color.YELLOW);
            else pen.setFill(Color.WHITE);
        pen.setTextAlign(TextAlignment.LEFT);
        pen.fillText("⟳", 20, 40);

        // This one is the button for switching units
        if (mouseX > 555 && mouseX < 585 && mouseY < 45 && mouseY > 10) pen.setFill(Color.YELLOW);
            else pen.setFill(Color.WHITE);
        pen.setFont(Font.font("helvetica", 28));
        pen.fillText("C", 560, 37);


        pen.setTextAlign(TextAlignment.CENTER);
        pen.setFont(Font.font("helvetica", 14));

        // Displays warnings
        try {
            if (weather.WeatherGetter.city.getWarnings().getEvents().size() != 0) {
                if (mouseX > 230 && mouseX < 370 && mouseY < 40 && mouseY > 15) pen.setFill(Color.YELLOW);
                    else pen.setFill(Color.INDIANRED);
                pen.fillText(weather.WeatherGetter.city.getWarnings().getEvents().size() + " warning(s) found.", 300, 35);
            }
        } catch (NullPointerException ignored) {}

        // Displays more info
        if (mouseX > 125 && mouseX < 475 && mouseY < 580 && mouseY > 560) pen.setFill(Color.YELLOW);
        else pen.setFill(Color.WHITE);
        pen.fillText("View extra information and forecasted weather.", 300, 575);

        offset = localOffset;
    }

    public static void mainWeatherClicked (double mouseX, double mouseY, double position) throws IOException {
        if (mouseX > 15 && mouseX < 55 && mouseY < 45 && mouseY > 10) weather.WeatherGetter.refresh();
        else if (mouseX > 555 && mouseX < 585 && mouseY < 45 && mouseY > 10) System.out.println("Units are in celsius");
        else if (mouseX > 125 && mouseX < 475 && mouseY < 580 && mouseY > 560) WeatherApp.setScene("other");
        else if (mouseX > 125 && mouseX < 475 && mouseY < position+85+offset && mouseY > position+60+offset) WeatherApp.setScene("location");
        else if (mouseX > 230 && mouseX < 370 && mouseY < 40 && mouseY > 15 && weather.WeatherGetter.city.getWarnings() != null) WeatherApp.setScene("warnings");
    }

    public static final int otherPosition = 95; // So that I can adjust more easily
    public static void drawOther (GraphicsContext pen, double mouseX, double mouseY) {
        pen.setTextAlign(TextAlignment.CENTER);
        pen.setFont(Font.font("helvetica", 14));
        if (mouseX > 230 && mouseX < 370 && mouseY < 40 && mouseY > 15) pen.setFill(Color.YELLOW);
        else pen.setFill(Color.WHITE);
        pen.fillText("Go back home.", 300, 35);

        if (mouseX > 125 && mouseX < 475 && mouseY < 580 && mouseY > 560) pen.setFill(Color.YELLOW);
        else pen.setFill(Color.WHITE);
        pen.fillText("View 7 day forecast.", 300, 575);

        pen.setTextAlign(TextAlignment.LEFT);
        pen.setFont(Font.font("helvetica", 48));

        pen.setFill(Color.WHITE);
        pen.fillText("Extra information", 25, otherPosition + 220);

        pen.fillText("24 hour forecast", 25, otherPosition);

        // This does the little wirebox thing below the extra info, except for the vertical lines on the top
        pen.setStroke(Color.WHITE);
        pen.strokeLine(25, otherPosition + 440, 575, otherPosition + 440);
        pen.strokeLine(25, otherPosition +230, 575, otherPosition +230);
        pen.strokeLine(25, 335 + otherPosition, 575, 335 + otherPosition);
        pen.strokeLine(299.5, otherPosition + 440, 299.5, 335 + otherPosition);
        pen.strokeLine(208, 335 + otherPosition, 208, otherPosition +230);
        pen.strokeLine(390, 335 + otherPosition, 390, otherPosition +230);

        // This does the forecast box's horizontal lines
        pen.strokeLine(25, otherPosition + 10, 575, otherPosition + 10);
        pen.strokeLine(25, otherPosition + 175, 575, otherPosition + 175);
        pen.strokeLine(25, otherPosition + 65, 575, otherPosition + 65);
        pen.strokeLine(25, otherPosition + 120, 575, otherPosition + 120);

        // This does vertical
        pen.strokeLine(93.75, otherPosition + 10, 93.75, otherPosition + 175);
        pen.strokeLine(162.5, otherPosition + 10, 162.5, otherPosition + 175);
        pen.strokeLine(231.25, otherPosition + 10, 231.25, otherPosition + 175);
        pen.strokeLine(300, otherPosition + 10, 300, otherPosition + 175);
        pen.strokeLine(368.75, otherPosition + 10, 368.75, otherPosition + 175);
        pen.strokeLine(437.5, otherPosition + 10, 437.5, otherPosition + 175);
        pen.strokeLine(506.25, otherPosition + 10, 506.25, otherPosition + 175);


        // This displays the forecast data in the forecast box thing
        pen.setTextAlign(TextAlignment.CENTER);
        pen.setFont(Font.font("helvetica", 14));
        for (int y = 0; y != 3; y++) {
            for (int x = 0; x != 8; x++) {
                HourlyForecast hourlyForecast = WeatherGetter.city.getHourlyforecastgroup().getForecasts().get(x + y * 8);
                pen.fillText("" + String.format("%02d", getTime(hourlyForecast.getDateTimeUTC()).getHour()) + ":"
                        + String.format("%02d", getTime(hourlyForecast.getDateTimeUTC()).getMinute()), x * 68.75 + 59.375, y * 55 + otherPosition + 30);
                Text text = new Text();
                text.setText(" " + hourlyForecast.getTemperature().getMeasurement() + "°");
                double size = text.getLayoutX() + 20;
                pen.fillText((int) hourlyForecast.getTemperature().getMeasurement() + "°", x * 68.75 + 59.375 - size / 2, y * 55 + otherPosition + 45);
                try {
                    pen.drawImage(WeatherGetter.getIcon(hourlyForecast.getIconCode()), x * 68.75 + 54.375 + size / 2, y * 55
                            + otherPosition + 40, 20, WeatherGetter.getIcon(hourlyForecast.getIconCode()).getHeight() * 20
                            / WeatherGetter.getIcon(hourlyForecast.getIconCode()).getWidth());
                } catch (IOException ignored) {
                }
            }
        }

        // This displays the extra information
        pen.setFont(Font.font("helvetica", 20));
        try {
            pen.fillText("Humidity", 116.5, otherPosition + 260);
            pen.fillText("Dewpoint", 300, otherPosition + 260);
            pen.fillText("Visibility", 482.5, otherPosition + 260);
            pen.fillText("Pressure", 162.5, otherPosition + 365);
            pen.fillText("Winds", 437.5, otherPosition + 365);
            pen.setFont(Font.font("helvetica", 60));
            pen.fillText((int) WeatherGetter.city.getCurrentconditions().getHumidity().getMeasurement() +
                    "%", 116.5, otherPosition + 320); // Humidity
            pen.fillText((int) WeatherGetter.city.getCurrentconditions().getDewpoint().getMeasurement() +
                    "°", 300, otherPosition + 320); // dewpoint
            pen.fillText((int) WeatherGetter.city.getCurrentconditions().getVisibility().getMeasurement()
                            + " " + WeatherGetter.city.getCurrentconditions().getVisibility().getUnits(),
                    482.5, otherPosition + 320); // visibility
            pen.fillText(WeatherGetter.city.getCurrentconditions().getPressure().getMeasurement() +
                            WeatherGetter.city.getCurrentconditions().getPressure().getUnits(), 162.5,
                    otherPosition + 425); // Pressure
            pen.fillText((int) WeatherGetter.city.getCurrentconditions().getWind().getSpeed() +
                            WeatherGetter.city.getCurrentconditions().getWind().getSpeedUnits() + " " +
                            WeatherGetter.city.getCurrentconditions().getWind().getDirection(), 437.5,
                    otherPosition + 425); // Wind
        } catch (NullPointerException e) {

        }
    }

    static String pattern = "yyyyMMddHHmm";
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
    public static ZonedDateTime getTime (String date) {
        LocalDateTime localDateTime = LocalDateTime.from(formatter.parse(date));
        Timestamp timestamp = Timestamp.valueOf(localDateTime);
        ZonedDateTime zoneOfUTC = localDateTime.atZone(ZoneId.of("UTC"));
        ZonedDateTime zoneOfLocal = zoneOfUTC.withZoneSameInstant(ZoneId.of(getUTCOffset(Double.parseDouble(
                WeatherGetter.city.getXMLCreation("LOC").getUTCOffset()))));
        return zoneOfLocal;
    }

    public static String getUTCOffset (double s) {
        return "UTC-"+ String.format("%02d", Math.abs((int) s)) + ":" + String.format("%02d", Math.abs((int) (s % 1 * 60)));
    }

    public static void otherClick (double mouseX, double mouseY) {
        if (mouseX > 230 && mouseX < 370 && mouseY < 40 && mouseY > 15) WeatherApp.setScene("main");
    }

    // This is for the method below
    private static String search = "";
    public static void addSearch (char ch) {
        if (search.length() <= 20)
        search += ch;
        searchResults = WeatherGetter.searchCities(search, WeatherGetter.getCities());
        listItemPosition = 60;
    }

    public static void backspaceSearch () {
        if (search.length() != 0)
        search = search.substring(0, search.length()-1);
        searchResults = WeatherGetter.searchCities(search, WeatherGetter.getCities());
        listItemPosition = 60;
    }
    public static String getSearch () {
        return search;
    }

    // persistent variables.
    private static List<Feature> searchResults;
    private static int listItemPosition = 60;

    public static void scroll (double x) {
        if (listItemPosition + x <= 60 && Math.signum(listItemPosition) != -1) {
            listItemPosition += x;
        } else if (listItemPosition + x > 70 - searchResults.size() * 25 && Math.signum(listItemPosition) != 1) {
            listItemPosition += x;
        } else if (listItemPosition + x > 60 && Math.signum(listItemPosition) != -1) {
            listItemPosition = 60;
        } else if (listItemPosition + x < 70 - searchResults.size() * 25 && Math.signum(listItemPosition) != 1) {
            listItemPosition = 70 - searchResults.size() * 25;
        }
    }

    public static void drawLocation (GraphicsContext pen, double mouseX, double mouseY) {
        // Displays results
        try {
            for (int x = 0; x != searchResults.size(); x++) {
                pen.setFill(Color.WHITE);
                if (WeatherGetter.getSelectedCity().equals(searchResults.get(x))) pen.setFill(Color.INDIGO);
                if (mouseX > 15 && mouseX < 230 && mouseY > listItemPosition + x * 25 && mouseY < listItemPosition + 20 + x * 25) pen.setFill(Color.YELLOW);
                pen.fillText(searchResults.get(x).getProperties().get("English Names") + ", " + searchResults.get(x).getProperties().get("Province Codes"), 20, listItemPosition + 16 + x * 25);

            }
        } catch (NullPointerException ignored) {
            if (searchResults != null)
            for (int x = 0; x != searchResults.size(); x++) {
                pen.setFill(Color.WHITE);
                if (mouseX > 15 && mouseX < 230 && mouseY > listItemPosition + x * 25 && mouseY < listItemPosition + 20 + x * 25) pen.setFill(Color.YELLOW);
                pen.fillText(searchResults.get(x).getProperties().get("English Names") + ", " + searchResults.get(x).getProperties().get("Province Codes"), 20, listItemPosition + 16 + x * 25);
            }
        }
        // Clears results if it is near the search
        // pen.clearRect(0, 0, 600, 50);

        // Displays search
        if (!search.equals("")) {
            pen.setFill(Color.WHITE);
            pen.setTextAlign(TextAlignment.CENTER);
            pen.setFont(Font.font("helvetica", 50));
            pen.fillText(search, 300, 50);
        } else {
            pen.setFill(Color.rgb(255, 255, 255, 0.5));
            pen.setTextAlign(TextAlignment.CENTER);
            pen.setFont(Font.font("helvetica", 50));
            pen.fillText("Please type ...", 300, 50);
        }
        pen.setTextAlign(TextAlignment.LEFT);
        pen.setFont(Font.font("helvetica", 14));
        pen.setFill(Color.WHITE);
    }

    public static void locationClick (double mouseX, double mouseY) {
        try {
            for (int x = 0; x != searchResults.size(); x++) {
                if (mouseX > 15 && mouseX < 230 && mouseY > listItemPosition + x * 25 && mouseY < listItemPosition + 16 + x * 25) {
                    WeatherGetter.changeSelectedCity(searchResults.get(x));
                    WeatherGetter.refresh();
                    WeatherApp.setScene("main");
                }
            }
        } catch (NullPointerException | IOException ignored) {
            ignored.printStackTrace();
        }
    }

    static int warningOffset = 70;
    public static void drawWarnings (GraphicsContext pen, double mouseX, double mouseY) {
        pen.setFont(Font.font("helvetica", 14));
        pen.setTextAlign(TextAlignment.CENTER);
        if (mouseX > 230 && mouseX < 370 && mouseY < 40 && mouseY > 15) pen.setFill(Color.YELLOW);
            else pen.setFill(Color.WHITE);
        pen.fillText("Go back.", 300, 35);
        WeatherGetter.city.getWarnings().getEvents();
        warningOffset = 70;
        pen.setFill(Color.WHITE);
        pen.setTextAlign(TextAlignment.LEFT);
        for (int x = 0; x != WeatherGetter.city.getWarnings().getEvents().size(); x++) {
            pen.setFont(Font.font("helvetica", 36));
            pen.fillText(WeatherGetter.city.getWarnings().getEvents().get(x).getDescription(), 25, x * 100 + 90);
            pen.setFont(Font.font("helvetica", 18));
            pen.fillText("Issued " + WeatherGetter.city.getWarnings().getEvents().get(x).getDateTime("LOC").getTextSummary(), 25,  x * 100 + 120);
            warningOffset += 100;
        }
        if (mouseX > 25 && mouseX < 350 && mouseY < warningOffset + 5 && mouseY > warningOffset - 20) pen.setFill(Color.YELLOW);
        else pen.setFill(Color.WHITE);
        pen.setFont(Font.font("helvetica", 24));
        pen.fillText("Click here to view full info.", 25, warningOffset);
    }

    public static void warningsClick (double mouseX, double mouseY) throws URISyntaxException, IOException {
        if (mouseX > 230 && mouseX < 370 && mouseY < 40 && mouseY > 15) WeatherApp.setScene("main");
        else if (mouseX > 25 && mouseX < 350 && mouseY < warningOffset + 5 && mouseY > warningOffset - 20)
            java.awt.Desktop.getDesktop().browse(new URI(WeatherGetter.city.getWarnings().getUrl()));
    }
}
