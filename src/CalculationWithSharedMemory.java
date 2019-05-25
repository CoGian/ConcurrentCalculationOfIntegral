

public class CalculationWithSharedMemory {
	
	private long numSteps ;
	public  double sum = 0.0;
	
	public CalculationWithSharedMemory(long numSteps) {
		this.numSteps = numSteps;
	} 
	
	public double calculate() {
		
		// get available cores 
		int cores = Runtime.getRuntime().availableProcessors();
       
		double step = 1.0 / (double)numSteps;
		 
		/* do computation */	  
		linearBarrier Barrier = new linearBarrier(cores+1,Thread.currentThread().getId());
					
		// initialize threads and start them
		stepThread Threads[] = new stepThread[cores];
		for (int i=0; i < cores; ++i) {
			Threads[i] = new stepThread(i, Barrier, step ,numSteps,cores) ; 
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
