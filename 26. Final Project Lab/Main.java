import java.awt.Font;
import java.io.InputStream;

public class Main {
  public static void main(String[] a) throws Exception {
    String fName = "Pokemon_GB[1].ttf";
    InputStream is = Main.class.getResourceAsStream(fName);
    Font font = Font.createFont(Font.TRUETYPE_FONT, is);

  }
}
