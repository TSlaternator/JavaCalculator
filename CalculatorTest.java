/**
 *
 * @author The Slaternator
 */
public class CalculatorTest {
    
    public static void main(String[] args){
        
        Calculator casio = new Calculator();
        
        System.out.println("Testing Calculator Class");
        
        //Testing simple addition:
        System.out.println("\nTest1: Calculate 1 + 2");
        casio.calculate(1);
        casio.setLastOperator("+");
        casio.calculate(2);
        System.out.println("Result = " + casio.getResult());
        System.out.println(casio.toString());
        
        //Testing multiple additions:
        System.out.println("\nTest2: Calculate 1 + 2 + 3");
        casio.reset();
        casio.calculate(1);
        casio.setLastOperator("+");
        casio.calculate(2);
        casio.setLastOperator("+");
        casio.calculate(3);
        System.out.println("Result = " + casio.getResult());
        System.out.println(casio.toString());
        
        //testing addition followed by multiplication
        System.out.println("\nTest3: calculate 1 + 2 * 3");
        casio.reset();
        casio.calculate(1);
        casio.setLastOperator("+");
        casio.calculate(2);
        casio.setLastOperator("*");
        casio.calculate(3);
        System.out.println("Result = " + casio.getResult());
        System.out.println(casio.toString());
        
        //testing division
        System.out.println("\nTest4: calculate 8 / 2");
        casio.reset();
        casio.calculate(8);
        casio.setLastOperator("/");
        casio.calculate(2);
        System.out.println("Result = " + casio.getResult());
        System.out.println(casio.toString());
        
        //testing subtraction
        System.out.println("\nTest5: calculate 4 - 2");
        casio.reset();
        casio.calculate(4);
        casio.setLastOperator("-");
        casio.calculate(2);
        System.out.println("Result = " + casio.getResult());
        System.out.println(casio.toString());
        
        //testing negative numbers
        System.out.println("\nTest6: calculate -2 + 2");
        casio.reset();
        casio.calculate(-2);
        casio.setLastOperator("+");
        casio.calculate(2);
        System.out.println("Result = " + casio.getResult());
        System.out.println(casio.toString());
        
    }
}
