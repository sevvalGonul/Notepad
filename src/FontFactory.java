
public class FontFactory {
  public static IFont createFont(String name, int size, boolean bold, boolean italic) {  // factory method
    if (name.equals("Arial")) {
      return new ArialFont(size, bold, italic);
    } else if (name.equals("Times New Roman")) {
      return new TimesNewRomanFont(size, bold, italic);
    } else {
      throw new IllegalArgumentException("Invalid font name");
    }
  }
}