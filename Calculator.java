public class Calculator
{
   private double result;        // Holds accumulator value
   private String lastOperator;  // Stores last operator entered
   private boolean start;        // Indicates start of a computation
   private boolean startEquation; //Indicates start of an equation
   
   public Calculator()
   {
      result = 0;
      lastOperator = "=";
      start = true;
   }
   
   public void reset(){
      result = 0;
      lastOperator = "=";
      start = true;
      startEquation = true;
   }
   
   public double getResult()
   {
      return result;
   }
   
   public void setResult(double result){
       this.result = result;
   }
   
   public boolean getStart()
   {
      return start;
   }
   
   public void setStart(boolean start)
   {
      this.start = start;
   }
   
   public void setStartEquation(boolean startEquation){
       this.startEquation = startEquation;
   }
   
   public String getLastOperator()
   {
      return lastOperator;
   }
   
   public void setLastOperator(String operator)
   {
      lastOperator = operator;
   }
   
   /**
    * Carries out the pending calculation.
    * @param x the value to be accumulated with the prior result.
    */
    public double calculate(double x)
    {
        if (start){
            result = x;
            start = false;
            startEquation = false;
        }
        else if (startEquation){
            startEquation = false;
        }
        else if (!startEquation){
            if (lastOperator.equals("+")) result += x;
            else if (lastOperator.equals("-")) result -= x;
            else if (lastOperator.equals("*")) result *= x;
            else if (lastOperator.equals("/")) result /= x;
            else if (lastOperator.equals("1/x")) result = 1/x;
        }
        return result;  
    }
   
   
   /**
    * Returns string representation of calculator state.
    * @param return calculator state
    */
   public String toString()
   {
      return getClass().getSimpleName() + 
      "[result = " + result + ", lastOperator = " + lastOperator
         + ", start = " + start + "]";
   }
}
