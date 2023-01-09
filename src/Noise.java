import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Noise extends ProcessingFX {
    public static void main(String[] args) {
        launch(args);
    }
    // Noise instance

    // Testing the noise
    public void setup(GraphicsContext pen) {

    }

    FastNoiseLite noise = new FastNoiseLite();
    float xOffset = (float) Math.random() * 10;
    float yOffset = (float) Math.random() * 10;
    float startingY = 0;
    float startingX = 0;

    public void draw(GraphicsContext pen) {
        window.setBackground(Color.BLUE);
        pen.clearRect(0, 0, window.getWidth(), window.getHeight());
        xOffset += 0.25;
        yOffset += 0.25;

        startingX = xOffset;
        startingY = yOffset;

        noise.SetNoiseType(FastNoiseLite.NoiseType.Perlin);
        for (int x = -10; x <= window.getWidth() + 10; x += 5) {
            startingX += 2;
            startingY = yOffset;
            for (int y = -10; y <= window.getHeight() + 10; y += 5){
                startingY += 2;
                float thisNoise = (noise.GetNoise(startingX, startingY) + 1)/2;
                pen.setFill(Color.GRAY);
                pen.fillOval(x, y, 20, 20);
                pen.setFill(Color.gray((thisNoise * 150+100)/255, (thisNoise * 135+90)/255));
                pen.fillOval(x, y, 20, 20);
            }
        }
    }
}
