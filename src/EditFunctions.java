
public class EditFunctions {
    GUI gui;
    
    public EditFunctions(GUI gui) {
        this.gui = gui;
    }
    
    public void undo() {
        
        try {
            gui.undoManager.undo();
        } catch (Exception e) {  // There is nothing to undo
            gui.textArea.setText("");
        }
        
    }
    
    public void redo() {
        try {
            gui.undoManager.redo();
        } catch (Exception e) {  // There is nothing to redo
            
        }
       
    }
    
    /*public void find() {
        if(gui.textArea.getText().length() == 0)
            return;
            
        
    }*/
}
