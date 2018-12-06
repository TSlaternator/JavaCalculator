/**
 * @author The Slaternator
 * Version 1.0
 * Last edited 15/11/2017
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.BorderFactory;
import java.text.DecimalFormat;

public class CalculatorFrame extends JFrame{
    
    private String currentNumber = "0"; //current number being entered by the user
    private String memoryNumber = "0"; //memory button number
    private String copyNumber = "0"; //copy menuItem number
    private String lastInput = "Nothing"; //used to allow user to overwrite Operator input
    private JPanel menuPanel; //Panel to hold the menu and screen
    private JPanel keyPadPanel; //Panel to hold the key pad
    private JTextField displayScreen; //text field to display output
    private JMenuBar menuBar; //Menu bar to hold the menus
    private JMenu editMenu, viewMenu, helpMenu; //Menus to hold the menuItems
    private JMenuItem paste, standard, scientific; //some menu items which start disabled
    private Calculator casio; //Calculator object (does the maths)
    private DecimalFormat output; //decimal format currently being used (will be set to one of the next two)
    private DecimalFormat nonSeparatedOutput; //format without comma separation
    private DecimalFormat separatedOutput; //format with comma separation
    private boolean overwrite; //if true, entering a number will overwrite the current number
    private GridBagConstraints cons; //constraints to control the layout
    private final int FRAME_WIDTH = 350; //width of the frame
    private final int FRAME_HEIGHT = 208; //height of the frame
    
    /**
     * Constructor to create and fill the calculator frame
     */
    public CalculatorFrame(){
        //sets borders of all MenuBars, MenuItems, PopupMenus and Text Fields to gray / darkGray
        UIManager.put("MenuBar.border", BorderFactory.createLineBorder(Color.darkGray, 1));
        UIManager.put("MenuItem.border", BorderFactory.createLineBorder(Color.black, 1));
        UIManager.put("PopupMenu.border", BorderFactory.createLineBorder(Color.darkGray, 1));
        UIManager.put("TextField.border", BorderFactory.createLineBorder(Color.gray, 1));
        //decimal format types (comma separated, and non comma separated)
        nonSeparatedOutput = new DecimalFormat("#.###########################");
        separatedOutput = new DecimalFormat("#,###.##########################");
        output = separatedOutput;
        //setting up constraints for the GridBagLayout
        cons = new GridBagConstraints();
        cons.fill = GridBagConstraints.BOTH;
        setLayout(new GridBagLayout());
        casio = new Calculator(); //instantiating a calculator
        
        CreateMenuPanel(); //creating and adding the Panel which holds the Menus and Screen
        cons.gridy = 1;
        CreateClearPanel(); //creating and adding the Panel which holds the Clear Buttons
        cons.gridy = 2;
        CreateKeyPadPanel(); //creating and adding the Panel which holds the Key Pad
        setSize(FRAME_WIDTH, FRAME_HEIGHT); 
    }
    
    /**
     * Creates the Menu Panel which holds all of the Menus and the screen, then adds it to the frame
     */
    private void CreateMenuPanel(){
        menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(2, 1));
        menuPanel.setBackground(Color.black);
        JSeparator separator = new JSeparator();
        separator.setBackground(Color.cyan);
        JSeparator separator2 = new JSeparator();
        separator2.setBackground(Color.cyan);
        menuBar = CreateMenuBar();
        //creating and adding Menus
        menuBar.add(editMenu = CreateMenu("Edit"));
        menuBar.add(viewMenu = CreateMenu("View"));
        menuBar.add(helpMenu = CreateMenu("Help"));
        //Creating and adding MenuItems
        editMenu.add(CreateMenuItem("Copy"));
        editMenu.add(paste = CreateMenuItem("Paste"));
        viewMenu.add(standard = CreateMenuItem("Standard"));
        viewMenu.add(scientific = CreateMenuItem("Scientific"));
        viewMenu.add(separator);
        viewMenu.add(CreateMenuItem("Digit Grouping"));
        helpMenu.add(CreateMenuItem("Help Topics"));
        helpMenu.add(separator2);
        helpMenu.add(CreateMenuItem("About Calculator"));
        //Graying out some of the MenuItems
        paste.setEnabled(false);
        standard.setEnabled(false);
        scientific.setEnabled(false);
        menuPanel.add(menuBar); 
        //Creating and adding the output screen
        displayScreen = CreateJTextField();
        displayScreen.setBackground(Color.black);
        displayScreen.setForeground(Color.cyan);
        menuPanel.add(displayScreen);
        //adding this Panel to the frame
        add(menuPanel, cons);
    }
    
    /**
     * Creates and adds a Panel to hold the Clear buttons
     */
    private void CreateClearPanel(){
        JPanel clearPanel = new JPanel();
        clearPanel.setBackground(Color.black);
        clearPanel.setLayout(new GridLayout(1, 4));
        clearPanel.add(CreateJLabel("")); //Dummy label to position the buttons to the right
        clearPanel.add(CreateJButton("BS", "Extra"));
        clearPanel.add(CreateJButton("CE", "Extra"));
        clearPanel.add(CreateJButton("C", "Extra"));
        add(clearPanel, cons);
    }
    
    /**
     * Creates and adds a panel to hold the Key Pad
     */
    private void CreateKeyPadPanel(){
        keyPadPanel = new JPanel();
        keyPadPanel.setBackground(Color.black);
        keyPadPanel.setLayout(new GridLayout(4, 4));
        keyPadPanel.add(CreateJButton("MC", "Memory"));
        keyPadPanel.add(CreateJButton("7", "Insert"));
        keyPadPanel.add(CreateJButton("8", "Insert"));
        keyPadPanel.add(CreateJButton("9", "Insert"));
        keyPadPanel.add(CreateJButton("/", "Operator"));
        keyPadPanel.add(CreateJButton("sqrt", "Extra"));
        keyPadPanel.add(CreateJButton("MR", "Memory"));
        keyPadPanel.add(CreateJButton("4", "Insert"));
        keyPadPanel.add(CreateJButton("5", "Insert"));
        keyPadPanel.add(CreateJButton("6", "Insert"));
        keyPadPanel.add(CreateJButton("*", "Operator"));
        keyPadPanel.add(CreateJButton("%", "Extra"));
        keyPadPanel.add(CreateJButton("MS", "Memory"));
        keyPadPanel.add(CreateJButton("1", "Insert"));
        keyPadPanel.add(CreateJButton("2", "Insert"));
        keyPadPanel.add(CreateJButton("3", "Insert"));
        keyPadPanel.add(CreateJButton("-", "Operator"));
        keyPadPanel.add(CreateJButton("1/x", "Extra"));
        keyPadPanel.add(CreateJButton("M+", "Memory"));
        keyPadPanel.add(CreateJButton("0", "Insert"));
        keyPadPanel.add(CreateJButton("+/-", "Insert"));
        keyPadPanel.add(CreateJButton(".", "Insert"));
        keyPadPanel.add(CreateJButton("+", "Operator"));
        keyPadPanel.add(CreateJButton("=", "Operator"));
        add(keyPadPanel, cons);
    }
    
    /**
     * Creates and returns a JLabel component
     * @param label String shown by the label
     * @return the JLabel component
     */
    private JLabel CreateJLabel(String label){
       JLabel myLabel = new JLabel(label);
       return myLabel;
    }
    
    /**
     * Creates and returns a non editable JTextField component
     * @return the JTextField Component
     */
    private JTextField CreateJTextField(){
        displayScreen = new JTextField("0", 30);
        displayScreen.setEditable(false);
        displayScreen.setHorizontalAlignment(JTextField.RIGHT);
        displayScreen.setSelectionColor(Color.black);
        displayScreen.setSelectedTextColor(Color.orange);
        return displayScreen;
    }
    
    /**
     * Creates and returns a JButton with an actionListener
     * @param label Label to be held by the button
     * @param type Type of the button (Insert/Operator/Memory/Extra)
     * @return the JButton
     */
    private JButton CreateJButton(String label, String type){
        JButton newButton = new JButton(label);
        newButton.setBackground(Color.black);
        //inner class ActionListener for the Insert buttons
        class InsertActionListener implements ActionListener{
            public void actionPerformed (ActionEvent event){
                //checks if it should overwrite the current Number, if so, resets it
                if (overwrite){
                    currentNumber = "0";
                    casio.reset();
                    overwrite = false;
                }
                //if the label is a '.', adds it so long as no .s are already present in the number
                if (label.equals(".")){
                    if (!currentNumber.contains(".")){
                        currentNumber += label;
                        displayScreen.setText(output.format(toDouble(currentNumber)));
                    }
                } //if the label is a '+/-' - changes the sign of the currentNumber
                else if (label.equals("+/-")){
                    currentNumber = displayScreen.getText();
                    //if the screenoutput is not a number, returns an error message
                    if(!validInput())
                        displayScreen.setText("Cannot make symbols negative!");
                    //if not, switches the sign
                    else
                        if(!currentNumber.contains("-")){
                            currentNumber = "-" + currentNumber;
                            displayScreen.setText(output.format(toDouble(currentNumber)));
                        }
                        else{
                            currentNumber = toPositive(currentNumber);
                                displayScreen.setText(output.format(toDouble(currentNumber)));
                        }
                }
                //if the label is a number, adds it to the end of the current Number
                else{
                    currentNumber += label;
                    displayScreen.setText(output.format(toDouble(currentNumber)));
                }
                lastInput = "Number"; //used to allow operators to overwrite each other
                repaint();
            }
        }
        //Inner class ActionListener for the Operator Buttons
        class OperatorActionListener implements ActionListener{
            public void actionPerformed (ActionEvent event){
                //sets overwrite to false so numbers won't reset the current Number
                if (overwrite)
                    overwrite = false;
                //if the last input was an operator, overwrite current operator without calculating anything
                if (lastInput.equals("Operator") && !label.equals("=") && !label.equals("-")){
                    casio.setLastOperator(label);
                    currentNumber = "0";
                    displayScreen.setText(label);
                }
                //if not, and the button pressed was "=" then calculate and display the result
                else if (label.equals("=")){
                    casio.setStartEquation(false);
                    displayScreen.setText(output.format(casio.calculate(toDouble(currentNumber))));
                    overwrite = true;
                    casio.setStartEquation(true);
                }
                //if the button was an "-" and no number has been entered, set the current Number to negative
                else if (label.equals("-")){
                    if(currentNumber.equals("0"))
                        currentNumber = ("-0");
                    //if not, calculates the current sum and sets the next operator to -
                    else{
                        casio.calculate(toDouble(currentNumber));
                        casio.setLastOperator(label);
                        currentNumber = "0";
                        displayScreen.setText(label);
                    }
                }
                //if the button was any other operator, calculates the current sum and sets the next operator
                else{
                    casio.calculate(toDouble(currentNumber));
                    casio.setLastOperator(label);
                    currentNumber = "0";
                    displayScreen.setText(label);
                }
                lastInput = "Operator"; //sets lastInput to operator, to allow consecutive operators to overwrite
                repaint();
            }
        }
        //Inner class ActionListener for Extra buttons
        class ExtrasActionListener implements ActionListener{
            public void actionPerformed (ActionEvent event){
                //if Button is BS, cut off the last digit of the number
                if (label.equals("BS")){
                    currentNumber = displayScreen.getText();
                    if(!validInput())
                        displayScreen.setText("Cannot Backspace Symbols!");
                    else{
                        if (currentNumber.length() > 0){
                            currentNumber = currentNumber.substring(0, currentNumber.length() - 1);
                            if (currentNumber.length() == 0)
                                currentNumber = "0";
                            displayScreen.setText(output.format(toDouble(currentNumber)));
                            casio.setResult(toDouble(currentNumber));
                        }
                    }
                }
                //if Button is CE, reset current Number to 0
                else if (label.equals("CE")){
                    currentNumber = "0";
                    displayScreen.setText(currentNumber);
                }
                //if Button is C, reset the calculator
                else if (label.equals("C")){
                    currentNumber = "0";
                    casio.reset();
                    displayScreen.setText(currentNumber);
                }
                //if Button is sqrt, return the square root of the number on screen
                else if (label.equals("sqrt")){
                    String square = displayScreen.getText();
                    if(!validInput())
                        displayScreen.setText("Cannot square root symbols!");
                    else{
                        if (!square.contains("-")){                        
                            currentNumber = Double.toString(Math.sqrt(toDouble(square)));
                            displayScreen.setText(output.format(toDouble(currentNumber)));
                        }
                        else{
                            currentNumber = "0";
                            displayScreen.setText("Cannot square root negatives!");
                        }
                    }
                }
                //if button is %, return CurrentNumber% of the current result
                else if (label.equals("%")){
                    if(!validInput())
                        displayScreen.setText("Cannot get percentage of symbols!");
                    else{
                        currentNumber = Double.toString((casio.getResult() / 100) * toDouble(currentNumber));
                        displayScreen.setText(output.format(toDouble(currentNumber)));
                    }
                }
                //if button is 1/x, return 1/the number on screen
                else if (label.equals("1/x")){
                    if(!validInput())
                        displayScreen.setText("Cannot divide by symbols!");
                    else{                      
                        currentNumber = Double.toString(1 / toDouble(currentNumber));
                        displayScreen.setText(output.format(toDouble(currentNumber)));                        
                    }
                }
            }
        }
        //Inner Class ActionListener for Memory Buttons
        class MemoryActionListener implements ActionListener{
            public void actionPerformed (ActionEvent event){
                //if the button is MC, resets memory number
                if (label.equals("MC")){
                    memoryNumber = "0";
                }
                //if the button is MR, returns the number from memory
                else if (label.equals("MR")){
                    currentNumber = memoryNumber;
                    displayScreen.setText(output.format(toDouble(currentNumber))); 
                    casio.setResult(toDouble(currentNumber));
                }
                //if the button is MS, saves the currentNumber
                else if (label.equals("MS")){
                    if(!validInput()){
                        displayScreen.setText("Cannot save symbols to memory!");
                        casio.reset();
                    }
                    else
                        memoryNumber = displayScreen.getText();
                }
                //if the button is M+, adds the currentNumber to the memory number 
                else if (label.equals("M+")){  
                    if(!validInput()){
                        displayScreen.setText("Cannot add symbols to memory!");
                        casio.reset();
                    }
                    else
                        memoryNumber = Double.toString(toDouble(memoryNumber) + toDouble(displayScreen.getText()));
                }
            }
        }
        //if the button type is insert, add an Insert action listener and set colour to cyan
        if (type.equals("Insert")){
            ActionListener insertListener = new InsertActionListener();
            newButton.addActionListener(insertListener);
            newButton.setForeground(Color.cyan);
        }
        //if the button type is operator, add an operator action listener and set colour to orange
        else if (type.equals("Operator")){
            ActionListener operatorListener = new OperatorActionListener();
            newButton.addActionListener(operatorListener);
            newButton.setForeground(Color.orange);
        }
        //if the button type is memory, add an memory action listener and set colour to green
        else if (type.equals("Memory")){
            ActionListener memoryListener = new MemoryActionListener();
            newButton.addActionListener(memoryListener);
            newButton.setForeground(Color.green);
        }
        //if the button type is extra, add an extra action listener and set colour to magenta
        else if (type.equals("Extra")){
            ActionListener extrasListener = new ExtrasActionListener();
            newButton.addActionListener(extrasListener);
            newButton.setForeground(Color.magenta);
        }
        
        return newButton;
    }
    
    /**
     * Creates and returns a JMenuBar
     * @return the JMenuBar component
     */
    private JMenuBar CreateMenuBar(){
        JMenuBar newMenuBar = new JMenuBar();
        newMenuBar.setBackground(Color.black);
        return newMenuBar;
    }
    
    /**
     * Creates and returns a JMenu
     * @param label label of the menu
     * @return the JMenu Component
     */
    private JMenu CreateMenu(String label){
        JMenu newMenu = new JMenu(label);
        newMenu.setForeground(Color.cyan);
        newMenu.setBackground(Color.black);
        return newMenu;
    }
    
    /**
     * Creates and returns a JMenuItem with an ActionListener
     * @param label label of the menuItem
     * @return the JMenuItem Component
     */
    private JMenuItem CreateMenuItem(String label){
        JMenuItem newMenuItem = new JMenuItem(label);
        newMenuItem.setForeground(Color.cyan);
        newMenuItem.setBackground(Color.black);
        //InnerActionListener class to add an actionListener
        class MenuActionListener implements ActionListener{
            public void actionPerformed (ActionEvent event){
                //if the MenuItem is Copy, copies the number on screen into memory and enables the paste button
                if (label.equals("Copy")){
                    if(!validInput())
                        displayScreen.setText("Cannot copy symbols!");
                    else{
                        copyNumber = displayScreen.getText();
                        paste.setEnabled(true);
                    }
                }
                //if the MenuItem is Paste, pastes the number from memory
                else if (label.equals("Paste")){
                    currentNumber = copyNumber;
                    displayScreen.setText(output.format(toDouble(currentNumber))); 
                }
                //if the MenuItem is Digit Grouping, switches the format method to include/excuse commas
                else if (label.equals("Digit Grouping")){
                    if (output == separatedOutput){
                        output = nonSeparatedOutput;
                    }
                    else{
                        output = separatedOutput;
                    }
                    displayScreen.setText(output.format(toDouble(displayScreen.getText())));
                }
                //if the MenuItem is Help Topics, creates a help topics frame
                else if (label.equals("Help Topics")){
                    JFrame helpFrame = new HelpFrame();
                }
                //if the MenuItem is About, creates an About dialog box
                else if (label.equals("About Calculator")){
                    JOptionPane dialog = new JOptionPane();
                    dialog.showMessageDialog(new JFrame(), "Calculator app\nVer 1.0"
                            + "\nLast Changed: 15/11/2017\nMade by: Tomos Slater");
                }
            }
        }
        ActionListener listener = new MenuActionListener();
        newMenuItem.addActionListener(listener);

        return newMenuItem;
    }
    
    /**
     * Returns a double of a given string
     * @param input String to convert
     * @return the converted double
     */
    private double toDouble(String input){
        double doubleOutput = 0.0;
        String buttonName = "";
        String[] parts;
        int digits;

        for (int i = 0; i < input.length(); i++){
            if (input.charAt(i) != ',')
                buttonName += input.charAt(i);
        }
        //if the String starts with a '.', add a 0 to the start
        if (buttonName.startsWith("."))
            buttonName = "0" + buttonName;
        //if the String ends with a '.', add a 0 to the end
        if (buttonName.charAt(buttonName.length() - 1) == '.')
            buttonName += "0"; 
        //if the String is a decimal, split it into two (split on the '.')
        if (buttonName.contains(".")){
            parts = buttonName.split("\\.");
            digits = parts[1].length();
            //adding the decimal digits
            for (int i = 0; i < digits; i++){
                char currentDigit = parts[1].charAt(i);
                doubleOutput += toDigit(currentDigit) * Math.pow(10, -(i + 1));
            }
        }
        else{
            parts = new String[1];
            parts[0] = buttonName;
        }
        //adding the whole digis
        digits = parts[0].length();
        for (int i = 0; i < digits; i++){
            char currentDigit = parts[0].charAt(i);
            doubleOutput += toDigit(currentDigit) * Math.pow(10, digits - i - 1);
        }
        //if the number is a -, multiply by -1
        if (parts[0].charAt(0) == '-')
            doubleOutput *= -1;
            
        return doubleOutput;
    }
    
    /**
     * converts a character to a double
     * @param number character to convert
     * @return the double
     */
    private double toDigit(char number){
        double digit = 0.0;
        switch (number){
            case '1': digit = 1.0; break;
            case '2': digit = 2.0; break;
            case '3': digit = 3.0; break;
            case '4': digit = 4.0; break;
            case '5': digit = 5.0; break;
            case '6': digit = 6.0; break;
            case '7': digit = 7.0; break;
            case '8': digit = 8.0; break;
            case '9': digit = 9.0; break;
            default: digit = 0.0; break;
        }
        return digit;
    }
    
    /**
     * converts a negative String representation to a positive one
     * @param negative String representing a negative number
     * @return the positive version
     */
    private String toPositive(String negative){
        String positive = "";
        for(int i = 1; i < negative.length(); i++)
            positive += negative.charAt(i);
        return positive;
    }
    
    /**
     * Checks if the input is valid (a  number)
     * @return true if valid, false if not
     */
    private boolean validInput(){
        boolean valid = false;
        String x = displayScreen.getText();
        if (x.contains("."))
            valid = true;
        for (int i = 0; i < 10; i++){
            if (x.contains("" + i))
                valid = true;
        }
                    
        if (!valid){
            currentNumber = "0";
        }
        
        return valid;
    }
}
