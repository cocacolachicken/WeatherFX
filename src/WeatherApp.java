import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import weather.WeatherGetter;

import java.io.IOException;
import java.net.URISyntaxException;

// Note: I was planning to do so much more with this app but because of limited time I couldn't.

public class WeatherApp extends ProcessingFX {
    private static String scene = "location";
    public static final int position = 270;
    public static void setScene (String newScene) {
        scene = newScene;
        System.out.println("Scene changed to " + scene);
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void setup(GraphicsContext pen) {
        try {
            weather.WeatherGetter.visualInitialize();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // Draws a scene according to the scene
    public void draw(GraphicsContext pen) {
        pen.clearRect(0, 0, window.getWidth(), window.getHeight());
        try {
            WeatherBackgrounds.generate(window, pen, WeatherGetter.city.getCurrentconditions().getIconCode(), true);
        } catch (NullPointerException e) {
            window.setBackground(Color.BLACK);
        }
        switch (scene) {
            case "main":
                Scenes.drawMainWeather(pen, position, mouse.x, mouse.y);
                break;
            case "other":
                Scenes.drawOther(pen, mouse.x, mouse.y);
                break;
            case "location":
                Scenes.drawLocation(pen, mouse.x, mouse.y);
                break;
            case "warnings":
                Scenes.drawWarnings(pen, mouse.x, mouse.y);
                break;
        }
    }

    public void mouseReleased () {
        // Handles input depending on the scene
        if (scene.equals("main")) {
            try {
                Scenes.mainWeatherClicked(mouse.x, mouse.y, position);
            } catch (IOException ignored) {
            }
        } else if (scene.equals("other")) {
            Scenes.otherClick(mouse.x, mouse.y);
        } else if (scene.equals("location")) {
            Scenes.locationClick(mouse.x, mouse.y);
        } else if (scene.equals("warnings")) {
            try {
                Scenes.warningsClick(mouse.x, mouse.y);
            } catch (URISyntaxException | IOException ignored) {

            }
        }
    }

    public void mouseWheel() {
        if (scene.equals("location")) Scenes.scroll(mouse.getWheelY());
    }

    public void keyPressed() {
        if (scene.equals("location")) {
            if (key.keyCode == KeyCode.BACK_SPACE) {
                Scenes.backspaceSearch();
            } else if (fitsConstraints(key.key) && !key.shiftPressed) {
                Scenes.addSearch(key.key);
            }
        }
    }
    private static boolean fitsConstraints (int x) {
        if ((x > 64 && x <= 90) || (x > 96 && x <= 122) || x == 32 || x == 45 || x == 46 || x == 39) {
            return true;
        } else return false;
    }
}
