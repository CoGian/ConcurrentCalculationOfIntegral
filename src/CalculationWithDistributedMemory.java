import java.io.IOException;

public class CalculationWithDistributedMemory extends Calculation {
	
	private long numWorkers ;
	private double sum = 0.0;
	private boolean shared  ; 
	
	public CalculationWithDistributedMemory(long numSteps, long numWorkers, boolean shared) {
		super(numSteps) ;
		this.numWorkers = numWorkers;
		this.shared = shared ; 
	}
	
	public double calculate() {
		
		double step = 1.0 / (double)numSteps;
		
		// use threads to automate the process of starting a server and multiple clients 
		
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
		
		if(!shared) {
			  // start multiple threads as workers, who don't use their own threads
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
		}else {
			  // start multiple threads as workers, who  use their own threads
			  for(int i =0 ; i<numWorkers ; i ++) {
				  new Thread(new Runnable() {
			            @Override
			            public void run() {
			                try {
			                	WorkerWithThreadsTCP.main(null); 		                		
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
			            }
			        }).start();
			  }
		}
		
		  
		// wait and yield until the server send the sum 
		while(sum == 0.0 ) {
			Thread.yield();
		}
		
		double pi = sum * step;	
		
		return pi ;	
		
		
	}
	

}
