public class CalcPi {

    public static void main(String[] args) {

        long numSteps = 0 ;
        String method = null ; 
        
      
        // parse command line 

        if (args.length > 3) {
			System.out.println("Usage: java CalcPi <number of steps> <method> (if method is distr Or distr&Shared)<number of workers>");
	                System.exit(1);
        }
        try {
        	numSteps = Long.parseLong(args[0]);
        	method = args[1] ;
   
        } catch (NumberFormatException e) {
			System.out.println("argument "+ args[0] +" must be long int");
			System.exit(1);
        }
      
        // start timing 
        long startTime = System.currentTimeMillis(); 
		
        Calculation calculator = null;
        // do computation depending the method 
        	
       if(method.equals("shared")) 
    	    calculator = new CalculationWithSharedMemory(numSteps) ;
       else if(method.equals("distr") )
    	   try {
    		    long numWorkers = Long.parseLong(args[2]);
    		   calculator = new CalculationWithDistributedMemory(numSteps, numWorkers,false) ;
    	   } catch (NumberFormatException e) {
    		   System.out.println("argument "+ args[2] +" must be long int");
   			System.exit(1);
    	   }    	   
       else if(method.equals("seq") )
    	   calculator = new Calculation(numSteps) ;       
       
       
       double pi = calculator.calculate() ; 

        // end timing and print result 
        long endTime = System.currentTimeMillis();
        System.out.printf("sequential program results with %d steps\n", numSteps);
        System.out.printf("computed pi = %22.20f\n" , pi);
        System.out.printf("difference between estimated pi and Math.PI = %22.20f\n", Math.abs(pi - Math.PI));
        System.out.printf("time to compute = %f seconds\n", (double) (endTime - startTime) / 1000);
    }
}
