/**
 * @author The Slaternator
 * Version 1.0
 * Last edited 15/11/2017
 */

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.Color;

public class HelpFrame extends JFrame{

    final int FRAME_WIDTH = 420;
    final int FRAME_HEIGHT = 400;
    private JPanel helpPanel;
    private JFrame helpFrame;
    
    /**
     * Constructor to create the frame
     */
    public HelpFrame(){
        helpFrame = new JFrame();
        helpFrame.setTitle("Help");
        helpFrame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        
        CreateHelpPanel();  
        
        helpFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        helpFrame.setResizable(false);
        helpFrame.setVisible(true);
    }
        
    /**
     * Method to create the contents of the frame
     */
    private void CreateHelpPanel(){
        helpPanel = new JPanel();
        helpPanel.setLayout(new GridLayout(28, 1));
        helpPanel.setBackground(Color.black);
        
        helpPanel.add(CreateJLabel("------------------------------- The Slaternators Casio App -------------------------------"));
        helpPanel.add(CreateJLabel("------------------------ Here's how to use my Calculator App: ------------------------"));
        helpPanel.add(CreateJLabel(""));
        helpPanel.add(CreateJLabel("To enter an Equation press the buttons in the frame"));
        helpPanel.add(CreateJLabel("------------------------------------------------------------------------------------------------------"));
        helpPanel.add(CreateJLabel("+ - / * work as you should expect"));
        helpPanel.add(CreateJLabel("= calculates the current equation"));
        helpPanel.add(CreateJLabel("1/x will divide 1 by the current number on screen"));
        helpPanel.add(CreateJLabel("% returns the number to current number% of the last number entered"));
        helpPanel.add(CreateJLabel("sqrt returns the square root of the current number on screen"));
        helpPanel.add(CreateJLabel("+/- switches the sign of the current number on screen"));
        helpPanel.add(CreateJLabel("------------------------------------------------------------------------------------------------------"));
        helpPanel.add(CreateJLabel("BS clears the last digit entered"));
        helpPanel.add(CreateJLabel("CE clears the last number entered"));
        helpPanel.add(CreateJLabel("C clears the entire equation"));
        helpPanel.add(CreateJLabel("------------------------------------------------------------------------------------------------------"));
        helpPanel.add(CreateJLabel("MS saves the current number into memory"));
        helpPanel.add(CreateJLabel("MR recalls the number in memory"));
        helpPanel.add(CreateJLabel("MC clears the number in memory"));
        helpPanel.add(CreateJLabel("M+ adds the current number to the number in memory"));
        helpPanel.add(CreateJLabel("------------------------------------------------------------------------------------------------------"));
        helpPanel.add(CreateJLabel("File > Copy copies the current number into memory"));
        helpPanel.add(CreateJLabel("File > Paste pastes the current number from memory"));
        helpPanel.add(CreateJLabel("-- the M number, and file > Copy memory number are separate --"));
        helpPanel.add(CreateJLabel("View > Digit Grouping changes format from 1,000 to 1000 and vise versa"));
        helpPanel.add(CreateJLabel("Help > Help Topics comes here!"));
        helpPanel.add(CreateJLabel("Help > About returns an about dialog box"));
        helpPanel.add(CreateJLabel("-----------------------------------------------------------------------------------------------------"));
        helpFrame.add(helpPanel);
    }
    
    /**
     * Creates and returns a JLabel
     * @param label the String of the label
     * @return the JLabel Component
     */
    private JLabel CreateJLabel(String label){
        JLabel newLabel = new JLabel(label);
        newLabel.setForeground(Color.cyan);
        newLabel.setBackground(Color.black);
        return newLabel;
    }
}
