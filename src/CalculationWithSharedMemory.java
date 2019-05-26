

public class CalculationWithSharedMemory extends Calculation{
	
	private double sum = 0.0;
	
	public CalculationWithSharedMemory(long numSteps) {
		super(numSteps) ;
	} 
	
	public double calculate() {
		
		// get available cores 
		int cores = Runtime.getRuntime().availableProcessors();
       
		double step = 1.0 / (double)numSteps;
		 
		/* do computation */	  
		linearBarrier Barrier = new linearBarrier(cores+1,Thread.currentThread().getId());
					
		// find how many steps to calculate  
		long steps_to_calculate = (long) (numSteps/cores)  ;
		
		// initialize threads and start them
		stepThread Threads[] = new stepThread[cores];
		for (int i=0; i < cores; ++i) {
				    	
			if (i==cores-1) // if it is  the last thread change steps to calculate accordingly 
	    		 steps_to_calculate =  (long) (numSteps - (steps_to_calculate * (cores-1) )) ; 
	    
	    			
	    	long initialstep = steps_to_calculate * i ;
			Threads[i] = new stepThread(i, Barrier, step ,initialstep,steps_to_calculate) ; 
		    Threads[i].start() ; 
		    
		 }
		
		// wait for all the threads to finish 
		Barrier.barrier();
		 
		// summarize thread's sums 
		for (int i=0; i < cores; ++i) {		    
		    sum += Threads[i].getSum();
		 }
		
				
	       
		double pi = sum * step;	
			
		return pi ;	
			       
		
	}
	

}
