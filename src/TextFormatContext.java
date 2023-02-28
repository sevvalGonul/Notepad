
import javax.swing.JTextArea;


public class TextFormatContext {
  private TextFormatStrategy strategy;

  public void setTextFormatStrategy(TextFormatStrategy strategy) {
    this.strategy = strategy;
  }

  public void formatText(JTextArea textArea) {
    strategy.formatText(textArea);
  }
}
