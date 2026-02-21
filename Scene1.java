import org.code.theater.*;
import org.code.media.*;

public class SceneOne extends Scene {

  private ImageFilter[][] images;

  /*
   * Constructor for SceneOne.
   * Preconditions: images != null
   * Postconditions: this.images references the passed array
   */
  public SceneOne(ImageFilter[][] images) {
    this.images = images;
  }

  /*
  drawScene method for SceneOne.
   */
  public void drawScene() {
    drawCultureImages();  
    drawHobbyImages();    
  }

  /*
 applies a colorize filter.
   * Preconditions: images[0] contains valid ImageFilter objects
   * Postconditions: Images displayed with colorized effect.
   */
  public void drawCultureImages() {
    for (ImageFilter img : images[0]) {
      clear("white");
      drawText("My Culture", 120, 30);
      /* Draw original image */
      drawImage(img, 0, 50, 400, 350, 0);
      pause(1);
      /* aply colorize filter */
      img.colorize();
      /* Draw filtered image */
      drawImage(img, 0, 50, 400, 350, 0);
      pause(1);
    }
  }

  /*
   * blur filter to each image.
   * Preconditions: images[1] may contain null elements
   * Postconditions: Images displayed with blur effect applied.
   */
  public void drawHobbyImages() {
    for (ImageFilter img : images[1]) {
      if (img != null) { /* Skip null to prevent errors */
        clear("white");
        drawText("My Music", 130, 30);
        drawImage(img, 0, 50, 400, 350, 0);
        pause(1); 
        img.applyBlur();
        drawImage(img, 0, 50, 400, 350, 0);
        pause(1);
      }
    }
  }
}
