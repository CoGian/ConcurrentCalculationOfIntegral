public class NumIntSeq {

    public static void main(String[] args) {

        long numSteps = 0;
        
        
      
        /* parse command line */

        if (args.length != 1) {
		System.out.println("arguments:  number_of_steps");
                System.exit(1);
        }
        try {
		numSteps = Long.parseLong(args[0]);
        } catch (NumberFormatException e) {
		System.out.println("argument "+ args[0] +" must be long int");
		System.exit(1);
        }
      	
        /* start timing */
        long startTime = System.currentTimeMillis();

        
        /* do computation */
        
  
       CalculationWithSharedMemory calculator = new CalculationWithSharedMemory(numSteps) ;
       
       
       
       double pi = calculator.calculate() ; 

        /* end timing and print result */
        long endTime = System.currentTimeMillis();
        System.out.printf("sequential program results with %d steps\n", numSteps);
        System.out.printf("computed pi = %22.20f\n" , pi);
        System.out.printf("difference between estimated pi and Math.PI = %22.20f\n", Math.abs(pi - Math.PI));
        System.out.printf("time to compute = %f seconds\n", (double) (endTime - startTime) / 1000);
    }
}
