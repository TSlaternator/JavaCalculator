/**
 * @author The Slaternator
 * Version 1.0
 * Last edited 15/11/2017
 */

import javax.swing.JFrame;

public class CalculatorViewer {
    
    public static void main(String[] args){
        
        //creating my frame
        JFrame frame = new CalculatorFrame();
        
        //setting up the frames attributes
        frame.setTitle("Casio.exe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
        
    }
}
