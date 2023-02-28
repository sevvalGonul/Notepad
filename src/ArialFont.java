
import java.awt.Font;

class ArialFont implements IFont {  // Concrete Product
  private int size;
  private boolean bold;
  private boolean italic;

  public ArialFont(int size, boolean bold, boolean italic) {
    this.size = size;
    this.bold = bold;
    this.italic = italic;
  }

  @Override
  public java.awt.Font getFont() {
    int style = Font.PLAIN;
    if (bold) style |= Font.BOLD;
    if (italic) style |= Font.ITALIC;
    return new java.awt.Font("Arial", style, size);
  }
}
