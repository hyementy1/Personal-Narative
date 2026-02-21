import org.code.theater.*;
import org.code.media.*;

public class TheaterRunner {
 
  public static void main(String[] args) {

    /*
     * 2D array of ImageFilter objects.
     * Rows represent categories: culture, music, food/drinks.
     */
    ImageFilter[][] images = {
      { new ImageFilter("ArmenianFlag.jpg"), new ImageFilter("Noravank.jpg") },
      { new ImageFilter("kamancheh.jpg"), null },
      { new ImageFilter("Tan.jpg"), new ImageFilter("Lahmajo.jpg") }
    };

    /* Instantiate the two Scene subclasses */
    SceneOne scene1 = new SceneOne(images);
    SceneTwo scene2 = new SceneTwo(images);

    /* Draw each scene before playback */
    scene1.drawScene();
    scene2.drawScene();

    /* Play scenes in order as a single animation */
    Theater.playScenes(scene1, scene2);
  }
}
