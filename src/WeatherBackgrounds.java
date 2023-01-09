import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import weather.WeatherGetter;

import java.awt.*;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;

public class WeatherBackgrounds {
    public static void generate (Window window, GraphicsContext pen, int iconCode, boolean flash) {
        switch (iconCode) {
            case 0: // Clear
                break;
            case 1: // Mainly Sunny
                break;
            case 2: // Partly Cloudy
                overcast(pen, window, 0, 50, dayNight(), 0.07);
                break;
            case 3: // Mostly cloudy
                overcast(pen, window, 50, 50, dayNight(), 0.07);
                break;
            case 4:
                break;
            case 5:
                break;
            case 6: // Light rain shower
                overcast(pen, window, 50, 50, dayNight(), 0.07);
                rain(window, pen, 5, 7, WEIGHT.LIGHT, Color.grayRgb(255, 0.5));
                break;
            case 7: // Light rain shower and flurries
                overcast(pen, window, 50, 50, dayNight(), 0.07);
                rain(window, pen, 5, 7, WEIGHT.LIGHT, Color.grayRgb(255, 0.5));
                snow(window, pen, 2, WeatherBackgrounds.WEIGHT.LIGHT);
                break;
            case 8: // Light flurries
                overcast(pen, window, 50, 50, dayNight(), 0.07);
                snow(window, pen, 2, WeatherBackgrounds.WEIGHT.LIGHT);
                break;
            case 9:
                break;
            case 10: // Cloudy
                overcast(pen, window, 100, 50, dayNight(), 0.07);
                break;
            case 11: // Precipitation / squalls / light precipitation
                overcast(pen, window, 50, 50, dayNight(), 0.07);
                rain(window, pen, 5, 7, WEIGHT.LIGHT, Color.grayRgb(255, 0.5));
                break;
            case 12: // Rain shower, light rain and drizzle, rain
                overcast(pen, window, 50, 50, dayNight(), 0.07);
                rain(window, pen, 5, 7, WEIGHT.LIGHT, Color.grayRgb(255, 0.5));
                break;
            case 13: // Rain and drizzle, heavy rain, etc
                overcast(pen, window, 50, 50, dayNight(), 0.5);
                rain(window, pen, 5, 7, WEIGHT.HEAVY, Color.grayRgb(255, 0.5));
                break;
            case 14: // Freezing version of 13
                overcast(pen, window, 50, 50, dayNight(), 0.5);
                rain(window, pen, 5, 7, WEIGHT.LIGHT, Color.grayRgb(255, 0.5));
                break;
            case 15: // Combination weather
                overcast(pen, window, 50, 50, dayNight(), 0.5);
                rain(window, pen, 5, 7, WEIGHT.LIGHT, Color.grayRgb(255, 0.5));
                snow(window, pen, 5, WeatherBackgrounds.WEIGHT.LIGHT);

                break;
            case 16: // Snow
                overcast(pen, window, 50, 50, dayNight(), 0.5);
                snow(window, pen, 5, WeatherBackgrounds.WEIGHT.LIGHT);
                break;
            case 17: // FLurries
                overcast(pen, window, 50, 50, dayNight(), 0.5);
                snow(window, pen, 2, WeatherBackgrounds.WEIGHT.LIGHT);
                break;
            case 18: //Heavy snow / flurries
                overcast(pen, window, 50, 50, dayNight(), 0.5);
                snow(window, pen, 5, WeatherBackgrounds.WEIGHT.HEAVY);
                break;
            case 19: //Thunderstorms
                overcast(pen, window, 100, 50, dayNight(), 0.5);
                rain(window, pen, 4, 5, WEIGHT.LIGHT, Color.grayRgb(255, 0.5));
                thunder(pen, flash);
                break;
            case 20: //
                break;
            case 21: //
                break;
            case 22: //
                break;
            case 23: // Haze
                overcast(pen, window, 200, 50, Color.PINK, 0.07);
                break;
            case 24: // Fog, ice fog, mist
                fog(window);
                break;
            case 25: // Drifting snow
                window.setBackground(dayNight());
                blowingParticles(pen, Color.WHITESMOKE);
                break;
            case 26: // Ice crystals
                break;
            case 27: // Snow pellets, ice pellets, hail
                overcast(pen, window, 50, 50, dayNight(), 0.5);
                snow(window, pen, 10, WeatherBackgrounds.WEIGHT.HEAVY);
                break;
            case 28: // Grains, drizzle
                overcast(pen, window, 50, 50, dayNight(), 0.5);
                rain(window, pen, 4, 5, WEIGHT.LIGHT, Color.grayRgb(255, 0.5));
                break;
            case 29:
                break;
            case 30: // Clear (night)
                window.setBackground(dayNight());
                break;
            case 31: // Mostly clear
                window.setBackground(dayNight());
                break;
            case 32: // Partly cloudy (night)
                overcast(pen, window, 50, 50, Color.NAVY, 0.07);
                break;
            case 33: // Mostly cloudy (night)
                overcast(pen, window, 100, 50, Color.MIDNIGHTBLUE, 0.07);
                break;
            case 34:
                break;
            case 35:
                break;
            case 36: // Light rain shower (night)
                overcast(pen, window, 100, 50, Color.MIDNIGHTBLUE, 0.07);
                rain(window, pen, 5, 7, WEIGHT.LIGHT, Color.grayRgb(255, 0.5));
                break;
            case 37: // LRS with flurries (night)
                overcast(pen, window, 100, 50, Color.MIDNIGHTBLUE, 0.07);
                rain(window, pen, 5, 7, WEIGHT.LIGHT, Color.grayRgb(255, 0.5));
                snow(window, pen, 2, WeatherBackgrounds.WEIGHT.LIGHT);
                break;
            case 38: // Light flurries (night)
                overcast(pen, window, 100, 50, Color.MIDNIGHTBLUE, 0.07);
                snow(window, pen, 2, WeatherBackgrounds.WEIGHT.LIGHT);
                break;
            case 39: // Thunderstorm (night)
                overcast(pen, window, 100, 50, Color.MIDNIGHTBLUE, 0.07);
                rain(window, pen, 5, 7, WEIGHT.HEAVY, Color.grayRgb(255, 0.5));
                thunder(pen, flash);
                break;
            case 40: // Blowing snow
                window.setBackground(dayNight());
                blowingParticles(pen, Color.WHITE);
                break;
            case 41: // Funnel cloud
                overcast(pen, window, 100, 50, Color.MIDNIGHTBLUE, 0.07);

                break;
            case 42: // Tornado
                break;
            case 43: // Windy
                window.setBackground(dayNight());
                break;
            case 44: // Smoke
                window.setBackground(dayNight());
                blowingParticles(pen, Color.DARKGRAY);
                break;
            case 45: // Dust devils, dust storm, volcanic ash, etc
                window.setBackground(dayNight());
                blowingParticles(pen, Color.KHAKI);
                break;
            case 46: // Thunder hail
                snow(window, pen, 10, WeatherBackgrounds.WEIGHT.HEAVY);
                thunder(pen, flash);
                break;
            case 47: // Thunder dust
                window.setBackground(dayNight());
                blowingParticles(pen, Color.KHAKI);
                thunder(pen, flash);
                break;
            case 48: // Waterspout
                break;
        }
    }

    // Day / night
    public static Color dayNight () { // I wish that we could just compare hours but because this doesn't fit neatly into UTC it would break without
        try {
            if (LocalDateTime.now(ZoneOffset.UTC).isAfter(WeatherGetter.city.getRiseSet().getRiseSet("sunrise").toLocalDateTime()) &&
                    LocalDateTime.now(ZoneOffset.UTC).isBefore(WeatherGetter.city.getRiseSet().getRiseSet("sunset").toLocalDateTime())) {
                return Color.SKYBLUE;
            } else if (LocalDateTime.now(ZoneOffset.UTC).getHour() == WeatherGetter.city.getRiseSet().getRiseSet("sunrise").getHour() ||
                    LocalDateTime.now(ZoneOffset.UTC).getHour() == WeatherGetter.city.getRiseSet().getRiseSet("sunset").getHour()) {
                return Color.rgb(116, 130, 179);
            } else {
                return Color.MIDNIGHTBLUE;
            }

        } catch (NullPointerException e) {
            return Color.rgb(116, 130, 179);
        }
    }

    // Fog
    private static Color skyGray = Color.rgb(178, 202, 204);

    public static void fog (Window window) {
        window.setBackground(skyGray);
    }

    // Snow flurries etc. Most of the following will be a method along with an accompanying class + ArrayList
    // These classes are for keeping track of a particle's position and velocity and angle
    // ArrayList for convenience

    static int snowFrames = 0;

    public static void snow (Window window, GraphicsContext pen, int speed, WEIGHT w) {
        int amount = 0;
        if (w == WEIGHT.LIGHT) amount = 100;
        if (w == WEIGHT.HEAVY) amount = 300;
        if (snowflakes.size() < amount && snowFrames == 0) {
            snowflakes.add(new Snowflake(Math.random() * 600, 0, Math.random() * 10 - 5, speed));
            snowFrames = 3;
        } else snowFrames --;
        pen.setFill(Color.ANTIQUEWHITE);
        for (Snowflake s : snowflakes) {
            s.updatePosition();
            s.drawSnowflake(pen);
        }
    }

    public static ArrayList<Snowflake> snowflakes = new ArrayList<>();

    public static class Snowflake {
        private double x;
        private double y;
        private double angle;
        private double speed;

        public Snowflake (double x1, double y1, double ang, double spe) {
            x = x1;
            y = y1;
            angle = ang;
            speed = spe;
        }

        public void updatePosition () {
            x += speed * Math.sin(Math.toRadians(angle));
            y += speed * Math.cos(Math.toRadians(angle));
            if (x > 600 && Math.signum(Math.cos(Math.toRadians(angle))) == -1) {
                x = 0;
            } else if (x < 0 && Math.signum(Math.cos(Math.toRadians(angle))) == 1) {
                x = 600;
            }
            if (y > 610) {
                y = -10;
                x = Math.random() * 600;
                angle = Math.random() * 10 - 5;
            }
        }

        public void drawSnowflake (GraphicsContext pen) {
            pen.fillOval(x - 2, y - 2, 4, 4);
        }
    }

    // Rain
    public enum WEIGHT {LIGHT, HEAVY}

    public static void rain (Window window, GraphicsContext pen, int speed, int height, WEIGHT w, Color color) {
        int amount = 0;
        if (w == WEIGHT.LIGHT) amount = 150;
        if (w == WEIGHT.HEAVY) amount = 300;
        if (raindrops.size() < amount) {
            raindrops.add(new Raindrop(Math.random() * 600,-10, Math.random() * 2 + 10));
        }
        pen.setStroke(color);
        pen.setLineWidth(2);
        for (Raindrop raindrop : raindrops) {
            raindrop.update(600);
            raindrop.draw(pen, height);
        }
    }

    public static ArrayList<Raindrop> raindrops = new ArrayList<>();

    public static class Raindrop {
        private double x;
        private double y;
        private double speed;

        public Raindrop (double x1, double y1, double spe) {
            x = x1;
            y = y1;
            speed = spe;
        }

        public void update (int windowY) {
            y += speed;
            if (y > windowY + 10) {
                y = -10;
                x = Math.random() * 600;
            }
        }

        public void draw (GraphicsContext pen, double height) {
            pen.strokeLine(x-1, y+height/2.0, x-1, y-height/2.0);
        }
    }

    // Clouds
    static FastNoiseLite noise = new FastNoiseLite();
    static float xOffset = (float) Math.random() * 5;
    static float yOffset = (float) Math.random() * 5;
    static float startingY = 0;
    static float startingX = 0;

    public static void overcast (GraphicsContext pen, Window window, int base, int range, Color color, double speed) {
        pen.clearRect(0, 0, window.getWidth(), window.getHeight());
        xOffset += speed;
        yOffset += speed;

        startingX = xOffset;
        startingY = yOffset;

        noise.SetNoiseType(FastNoiseLite.NoiseType.Perlin);
        for (int x = -10; x <= window.getWidth() + 10; x += 5) {
            startingX += 2;
            startingY = yOffset;
            for (int y = -10; y <= window.getHeight() + 10; y += 5){
                startingY += 2;
                float thisNoise = (noise.GetNoise(startingX, startingY) + 1)/2;
                pen.setFill(color);
                pen.fillOval(x, y, 20, 20);
                pen.setFill(Color.gray((thisNoise * range+base)/255, (thisNoise * 135+90)/255));
                pen.fillOval(x, y, 20, 20);
            }
        }
    }


    // Misc, usually reuseable
    static int frameCount = 0;
    static int thunderboltFrames = 0;
    static int maxThunderboltFrames = 0;
    static boolean cooldown = false;

    public static void thunder (GraphicsContext pen, boolean flash) {
        if (!flash) return;
        if (frameCount == 0 && thunderboltFrames == 0 && !cooldown) {
            maxThunderboltFrames = (int) (Math.random() * 10 + 10);
            thunderboltFrames = maxThunderboltFrames;
            cooldown = true;
        } else if (thunderboltFrames != 0) {
            thunderboltFrames--;
            pen.setFill(Color.gray(1, (thunderboltFrames * 1.0)/maxThunderboltFrames));
            pen.fillRect(0, 0, 600, 600);
        } else if (frameCount == 0) {
            frameCount = (int) (Math.random() * 100 + 300);
            cooldown = false;
        }
        else frameCount --;
    }

    public static int particleFrames = 0;
    public static void blowingParticles (GraphicsContext pen, Color particleColor) {
        if (particles.size() != 50 && particleFrames == 0) {
            particles.add(new Particle(0, Math.random() * 600, 5));
            particleFrames = 10;
        } else if (particles.size() != 50) particleFrames --;
        pen.setFill(particleColor);
        for (Particle p : particles) {
            p.updateParticle();
            p.drawParticle(pen);
        }
    }
    public static ArrayList<Particle> particles = new ArrayList<>();


    public static class Particle {
        private double x;
        private double y;
        private double angle;
        private double speed;
        private int frames;

        public Particle (double x1, double y1, double spe) {
            x = x1;
            y = y1;
            angle = Math.random() * 10 - 5;
            speed = spe;
            frames = (int) (Math.random() * 300 + 300);
        }

        public void updateParticle () {
            x += speed * Math.cos(Math.toRadians(angle));
            y += speed * Math.sin(Math.toRadians(angle));
            if (x >= 610) x = -10;
            frames --;
            if (frames == 0) {
                angle = Math.random() * 10 - 5;
                frames = (int) (Math.random() * 300 + 300);
            }
        }

        public void drawParticle (GraphicsContext pen) {
            pen.fillRect(x - 3, y - 3, 6, 6);
        }
    }

    public static void whirlwind (GraphicsContext pen, Color tornadoColor) {

    }

    // Clouds
    public enum FREQUENCY {CLEAR, PARTLY, MOSTLY}

    public static void generateClouds (Window window, GraphicsContext pen, FREQUENCY frequency) {
        if (FREQUENCY.CLEAR == frequency) {
            // Do nothing
        } else if (frequency == FREQUENCY.PARTLY) {
            // Implement
        } else if (frequency == FREQUENCY.MOSTLY) {
            // implement
        }
    }

    // Special weathers
}
