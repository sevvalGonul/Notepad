
import java.awt.FileDialog;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
public class FileFunctions {
    GUI gui;
    String fileName;
    String fileAddress;
    
    public FileFunctions(GUI gui){
        this.gui = gui   ;
    }
    
    public void newFile(){
        gui.textArea.setText("");
        gui.window.setTitle("New");
        fileName = null;
        fileAddress = null;
    }
    
    public void open() {
        FileDialog fd = new FileDialog(gui.window, "Open", FileDialog.LOAD);
        fd.setVisible(true);
        
        if(fd.getFile() != null) {  // If a file is choosen
            fileName = fd.getFile();
            fileAddress = fd.getDirectory();
            gui.window.setTitle(fileName);
        }
        
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileAddress + fileName));  // In order to read contents of the selected file
            gui.textArea.setText("");  // Clear the text area
            String line = null;
            while((line = br.readLine()) != null) {  // Read the selected file line by line
                gui.textArea.append(line + "\n");  // append the readed line to the end of the text area
            }
            br.close();
        } catch (Exception e) {
            System.out.println("FILE NOT OPENED");
        }
    }
    
    public void save() {
        if(fileName == null) {  // It's a new file
            saveAs();
        }
        else {  // Updating an existing file, override it
            try {
            FileWriter fw = new FileWriter(fileAddress + fileName);
            fw.write(gui.textArea.getText());
            gui.window.setTitle(fileName);
            fw.close();
            } catch (Exception e) {
                System.out.println("Something went wrong");
            }
        }
    }
    
    public void saveAs() {
        FileDialog fd = new FileDialog(gui.window, "Save", FileDialog.SAVE);
        fd.setVisible(true);
        
        if(fd.getFile() != null) {
            fileName = fd.getFile();
            fileAddress = fd.getDirectory();
            gui.window.setTitle(fileName);
        }
        
        try {  // File writer will take what is written in text area and write it to the file
            FileWriter fw = new FileWriter(fileAddress + fileName);
            fw.write(gui.textArea.getText());
            fw.close();
        } catch (Exception e) {
            System.out.println("Something went wrong");
        }
    }
    
    public void exit() {
        System.exit(0);
    }
}
