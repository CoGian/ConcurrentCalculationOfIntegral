import java.io.IOException;

public class CalculationWithDistributedMemory extends Calculation {
	
	private long numSteps ;
	private long numWorkers ;
	private double sum = 0.0;
	
	public CalculationWithDistributedMemory(long numSteps, long numWorkers) {
		super(numSteps) ;
		this.numSteps = numSteps;
		this.numWorkers = numWorkers;
	}
	
	public double calculate() {
		
		double step = 1.0 / (double)numSteps;
		
		// start a thread as a server	
		  new Thread(new Runnable() {
	            @Override
	            public void run() {
	                try {
						sum = MasterServerTCP.main(numWorkers,numSteps,step);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	            }
	        }).start();
		
		  // start multiple threads as workers 
		  for(int i =0 ; i<numWorkers ; i ++) {
			  new Thread(new Runnable() {
		            @Override
		            public void run() {
		                try {
		                	WorkerTCP.main(null); 		                		
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		            }
		        }).start();
			  
		  }	  
		  
		// wait and yield until the return send the sum 
		while(sum == 0.0 ) {
			Thread.yield();
		}
		
		double pi = sum * step;	
		
		return pi ;	
		
		
	}
	

}
