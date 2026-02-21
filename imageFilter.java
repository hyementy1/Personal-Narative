import org.code.theater.*;
import org.code.media.*;

public class ImageFilter extends ImagePlus {

private String filename;
  
  public ImageFilter(String filename) {
    super(filename);
    this.filename = filename;
  }

  public void threshold(int value) {
    Pixel[][] pixels = getImagePixels();

    for (int r = 0; r < pixels.length; r++) {
      for (int c = 0; c < pixels[0].length; c++) {
        Pixel p = pixels[r][c];
        int avg = (p.getRed() + p.getGreen() + p.getBlue()) / 3;

        if (avg < value) {
          p.setColor(Color.BLACK);
        } else {
          p.setColor(Color.WHITE);
        }
      }
    }
  }


 public void makeNegative() {
        for (int x = 0; x < this.getWidth(); x++) {
            for (int y = 0; y < this.getHeight(); y++) {
                Pixel pixel = this.getPixel(x, y);
                int newRed = 255 - pixel.getRed();
                int newGreen = 255 - pixel.getGreen();
                int newBlue = 255 - pixel.getBlue();
                pixel.setRed(newRed);
                pixel.setGreen(newGreen);
                pixel.setBlue(newBlue);
            }
        }


 }


 public void applyBlur() {
    Pixel[][] pixels = getImagePixels();

    // traverse starting at (1,1) bc using pixel to the top left
    // need to stop one less so - 1 in for loop condition
    for (int row = 1; row < pixels.length - 1; row++) {
      for (int col = 1; col < pixels[0].length - 1; col++) {
        // call methods to calculate RBG weights
        int weightedRed = calcWeightedRed(pixels, row, col);
        int weightedGreen = calcWeightedGreen(pixels, row, col);
        int weightedBlue = calcWeightedBlue(pixels, row, col);

        // update the RBG with weighted values
        Pixel currentPixel = pixels[row][col];
        currentPixel.setRed(weightedRed);
        currentPixel.setGreen(weightedGreen);
        currentPixel.setBlue(weightedBlue);
      }
    }
    
  }

  /*
   * Returns a weighted red average of the pixels around the specified row and col
   */
  public int calcWeightedRed(Pixel[][] pixels, int row, int col) {
    int avgRed = (pixels[row-1][col-1].getRed() + pixels[row-1][col].getRed() + pixels[row-1][col+1].getRed() +
                  pixels[row][col-1].getRed() + pixels[row][col].getRed() + pixels[row][col+1].getRed() +
                  pixels[row+1][col-1].getRed() + pixels[row+1][col].getRed() + pixels[row+1][col+1].getRed()) / 9;
    return avgRed;
  }

  /*
   * Returns a weighted green average of the pixels around the specified row and col 
   */
  public int calcWeightedGreen(Pixel[][] pixels, int row, int col) {
    int avgGreen = (pixels[row-1][col-1].getGreen() + pixels[row-1][col].getGreen() + pixels[row-1][col+1].getGreen() +
                    pixels[row][col-1].getGreen() + pixels[row][col].getGreen() + pixels[row][col+1].getGreen() +
                    pixels[row+1][col-1].getGreen() + pixels[row+1][col].getGreen() + pixels[row+1][col+1].getGreen()) / 9;
    return avgGreen;
  }

  /*
   * Returns a weighted blue average of the pixels around the specified row and col
   */
  public int calcWeightedBlue(Pixel[][] pixels, int row, int col) {
    int avgBlue = (pixels[row-1][col-1].getBlue() + pixels[row-1][col].getBlue() + pixels[row-1][col+1].getBlue() +
                   pixels[row][col-1].getBlue() + pixels[row][col].getBlue() + pixels[row][col+1].getBlue() +
                   pixels[row+1][col-1].getBlue() + pixels[row+1][col].getBlue() + pixels[row+1][col+1].getBlue()) / 9;
    return avgBlue;
  }
  
   public void saturate(double factor) {
  Pixel[][] pixels = getImagePixels();

  for (int row = 0; row < pixels.length; row++) {
    for (int col = 0; col < pixels[0].length; col++) {
  Pixel p = pixels[row][col];
  int red = p.getRed();
  int green = p.getGreen();
      int blue = p.getBlue();
      int average = (red + green + blue) / 3;
  int adjustedGray = (int)(average + (average - 255) * factor);

      int newRed = 2 * adjustedGray - red;
   int newGreen = 2 * adjustedGray - green;
      int newBlue = 2 * adjustedGray - blue;
      if (newRed > 255) {
        newRed = 255;
      }
      if (newRed < 0) {
        newRed = 0;
      }
      if (newGreen > 255) {
        newGreen = 255;
      }
      if (newGreen < 0) {
        newGreen = 0;
                        }
      if (newBlue > 255) {
        newBlue = 255;
      }
      if (newBlue < 0) {
        newBlue = 0;
      }
 p.setRed(newRed);
    p.setGreen(newGreen);
     p.setBlue(newBlue);
    }
  }
}

   public void colorize() {

  Pixel[][] pixels = getImagePixels();

  for (int row = 0; row < pixels.length; row++) {
    for (int col = 0; col < pixels[0].length; col++) {

      Pixel p = pixels[row][col];

      int r = p.getRed();
      int g = p.getGreen();
      int b = p.getBlue();

      int avg = (r + g + b) / 3;

      if (avg < 85) {
        p.setRed(255);
        p.setGreen(0);
        p.setBlue(0);
      }
      else if (avg < 170) {
        p.setRed(0);
        p.setGreen(255);
        p.setBlue(0);
      }
      else {
        p.setRed(0);
        p.setGreen(0);
        p.setBlue(255);
      }

    }
  }
}

 
  
 public boolean isFoodImage() {
  String name = filename.toLowerCase();
  return name.contains("lahmajo") || name.contains("tan");
}
     
}




