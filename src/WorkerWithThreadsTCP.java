import java.io.BufferedReader;
import java.io.*;
import java.net.*;


public class WorkerWithThreadsTCP {
	private static final String HOST = "localhost";
	private static final int PORT = 1398;
	
	
	public static void main(String[] args) throws UnknownHostException, IOException {
		// TODO Auto-generated method stub
					
		try {
			// initialize socket , output , input
			Socket dataSocket = new Socket(HOST, PORT);
					
			InputStream is = dataSocket.getInputStream();
			BufferedReader in = new BufferedReader(new InputStreamReader(is));
			OutputStream os = dataSocket.getOutputStream();
			PrintWriter out = new PrintWriter(os,true);
					       	
			String inmsg, outmsg;
			
			// read parameters 
			inmsg = in.readLine();
			
			String [] arrOfStr = inmsg.split(" ") ; 
			long initialstepOfWorker = Long.parseLong(arrOfStr[0]) ; 
			long initialstepOfThread = initialstepOfWorker ;
			long steps_to_calculate = Long.parseLong(arrOfStr[1]) ;
			double step = Double.parseDouble(arrOfStr[2]) ;
			long numSteps = steps_to_calculate ;
			double sum = 0.0 ;
						
			// get available cores 
			int cores = Runtime.getRuntime().availableProcessors();
			
			
			/* do computation */	  
			linearBarrier Barrier = new linearBarrier(cores+1,Thread.currentThread().getId());
						
			// find how many steps to calculate  
			steps_to_calculate = (long) (numSteps/cores)  ;
			
			// initialize threads and start them
			stepThread Threads[] = new stepThread[cores];
			for (int i=0; i < cores; ++i) {
					    	
				if (i==cores-1) // if it is  the last thread change steps to calculate accordingly 
		    		 steps_to_calculate =  (long) (initialstepOfWorker + numSteps) - initialstepOfThread ; 
		    
				Threads[i] = new stepThread(i, Barrier, step ,initialstepOfThread,steps_to_calculate) ; 
			    Threads[i].start() ; 
			    
			    initialstepOfThread += steps_to_calculate ;
			    
			 }
			
			// wait for all the threads to finish 
			Barrier.barrier();
			 
			// summarize thread's sums 
			for (int i=0; i < cores; ++i) {		    
			    sum += Threads[i].getSum();
			 }
	    	
	    	// send sum to server 
	    	outmsg = Double.toString(sum);
			out.println(outmsg);			
				
				
			dataSocket.close();	
		
					
			
		}
		catch(IOException RERR) {
			System.out.println("RERR") ;
		}
	}
	
}
