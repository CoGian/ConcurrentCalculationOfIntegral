
public class stepThread extends Thread {
	
	private int threadID;
    private linearBarrier myBarrier;
    private double step ; 
    private long numSteps ; 
    public  double sum = 0.0;
    public int cores ; 
    
    public stepThread(int tid, linearBarrier bar, double step, long numSteps, int cores) {
    	this.threadID = tid;
    	this.myBarrier = bar;
    	this.step = step ; 
    	this.numSteps = numSteps ; 
    	this.cores = cores ;
    }
    
    
    public void run() {
    	
    	
    	// find how many steps to calculate depending on thread id 
    	long steps_to_calculate ;
    	
		if (threadID<cores-1) // if it is not the last thread 
    		 steps_to_calculate  = (long) (numSteps/cores) ; 
    	else 
    		 steps_to_calculate =  (long) (numSteps - (((long) (numSteps/cores)) * (cores-1) )) ; 
    
    			
    	long initialstep = ((long) (numSteps/cores)) * threadID ;
		
    	
    	
    	//do computation 	
    	for(long i= initialstep ; i < initialstep + steps_to_calculate ; i ++  ) {
    	  
            double x = ((double)i+0.5)*step;
            sum += 4.0/(1.0+x*x);            
    	}

    	// wait for others to finish 
    	myBarrier.barrier();
    }


	public double getSum() {
		return sum;
	}
    
    
}
