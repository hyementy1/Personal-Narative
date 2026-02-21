import org.code.theater.*;
import org.code.media.*;

public class SceneTwo extends Scene {

  /*
   * 2D array storing all images shared from TheaterRunner.
   */
  private ImageFilter[][] images;

  /*
   * Constructor receives the shared images array
   * Preconditions: images != null
   * Postconditions: this.images references the passed array
   */
  public SceneTwo(ImageFilter[][] images) {
    this.images = images;
  }

  /*
    drawScene method for SceneTwo.
   */
  public void drawScene() {
    drawFoodImages();   
    drawExtraFilter();  
  }

  /*
    Draws the food/drink images from row 2 of the images array.
    Preconditions: images[2] contains valid ImageFilter objects
    Postconditions: Images are displayed with applied negative filter.
   */
  public void drawFoodImages() {
    for (ImageFilter img : images[2]) {

      clear("white");
      drawText("Favorite Foods", 110, 30);

      /* Draw original image */
      drawImage(img, 0, 50, 400, 350, 0);
      pause(1);

      /* Apply negative filter */
      img.makeNegative();

      /* Draw filtered image */
      drawImage(img, 0, 50, 400, 350, 0);
      pause(1);
    }
  }

  /*
    Draws a Armenian flag image and applies threshold filter.
   */
  public void drawExtraFilter() {
    ImageFilter img = new ImageFilter("ArmenianFlag.jpg");

    clear("white");
    drawText("Armenia", 120, 30);

    /* Draw original image */
    drawImage(img, 0, 50, 400, 350, 0);
    pause(1);

    /* Apply threshold filter */
    img.threshold(120);

    /* Draw filtered image */
    drawImage(img, 0, 50, 400, 350, 0);
    pause(1);
  }
}
