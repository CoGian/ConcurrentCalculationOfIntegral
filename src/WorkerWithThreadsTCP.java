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
			long initialstep = Long.parseLong(arrOfStr[0]) ; 
			long steps_to_calculate = Long.parseLong(arrOfStr[1]) ;
			double step = Double.parseDouble(arrOfStr[2]) ;
			
			double sum = 0.0 ;
			// do computation 	
	    	for(long i= initialstep ; i < initialstep + steps_to_calculate ; i ++  ) {
	    	  
	            double x = ((double)i+0.5)*step;
	            sum += 4.0/(1.0+x*x);            
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
