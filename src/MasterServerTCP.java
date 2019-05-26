import java.io.*;
import java.net.*;
import java.util.Arrays;

public class MasterServerTCP {
	
	private static final int PORT = 1398;
	
	
	public static double main(long numWorkers, long numSteps, double step) throws IOException {
		
		//create a socket listening 
		ServerSocket connectionSocket = new ServerSocket(PORT);
		System.out.println("Server is listening to port: " + PORT);
		
		Socket dataSockets[] = new Socket[(int) numWorkers] ; 
		Double sums[] = new Double[(int) numWorkers] ;
		
		// find how many steps to calculate depending on worker 
		long steps_to_calculate = (long) (numSteps/numWorkers) ;
		int i = 0 ; 
		while (true) {	

			//accept a connection 
			dataSockets[i]=	connectionSocket.accept();
			System.out.println("Received request from " + dataSockets[i].getInetAddress());
			
			
			
			if (i==numWorkers-1) // if it is  the last worker change steps to calculate accordingly 
				steps_to_calculate =  (long) (numSteps - (steps_to_calculate * (numWorkers-1) )) ; 
   
					
			long initialstep = steps_to_calculate * i ;
			
			// start a thread to wait the answer of a worker 
			ServerThread sthread = new ServerThread(dataSockets[i],sums,initialstep,steps_to_calculate,i,step);
			sthread.start();
			
			// if all the workers started break
			if (i == numWorkers-1)
				break ;
			
			i ++ ;
		}
		
		// wait & yield  until all workers send their sums 
		while(Arrays.asList(sums).contains(null)) {
			Thread.yield();
		}
		
		// summarize workers' sums 
		double sum = 0.0  ; 
		for(int j = 0 ; j < sums.length; j++)
			sum += sums[j] ; 
		
		connectionSocket.close();
		return sum ;
		
			
		
	}

}
