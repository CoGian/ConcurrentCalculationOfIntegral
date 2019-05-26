
public class stepThread extends Thread {
	
	private int threadID;
    private linearBarrier myBarrier;
    private double step ; 
    public  double sum = 0.0;
    long initialstep ; 
    long steps_to_calculate ; 
    
    public stepThread(int tid, linearBarrier bar, double step, long initialstep, long steps_to_calculate) {
    	this.threadID = tid;
    	this.myBarrier = bar;
    	this.step = step ; 
    	this.initialstep = initialstep ; 
    	this.steps_to_calculate = steps_to_calculate ;
    }
    
    
    public void run() {    	    	 	
		    	
    	
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
