import java.io.*;
import java.net.*;

public class ServerThread extends Thread{
	
	private Socket dataSocket;
	private InputStream is;
   	private BufferedReader in;
	private OutputStream os;
   	private PrintWriter out;
   	private Double[] sums ; 
   	long initialstep ; 
   	long steps_to_calculate ;
   	int workerID ;
   	double step ;
   	
	public ServerThread(Socket socket, Double[] sums, long initialstep, long steps_to_calculate, int i, double step) {
		// TODO Auto-generated constructor stub
		
		dataSocket = socket;
		this.sums = sums ; 
		this.initialstep = initialstep ; 
		this.steps_to_calculate = steps_to_calculate ; 
		workerID =i ;
		this.step = step ;
  		try {
			is = dataSocket.getInputStream();
			in = new BufferedReader(new InputStreamReader(is));
			os = dataSocket.getOutputStream();
			out = new PrintWriter(os,true);
  		}catch (IOException e) {
			// TODO: handle exception
  			System.out.println("I/O Error " + e);
		}
  		 		 		
  		
	}
	
	
	public void run(){
   		String inmsg = null, outmsg;
		
		try {		
			
			// send parameters to worker 
			outmsg = Long.toString(initialstep) + ' ' + Long.toString(steps_to_calculate) + " " + Double.toString(step) ; 
			
			while (!outmsg.equals("EXIT")) { //keep reading while sum was not sent by worker 
				out.println(outmsg);
				inmsg = in.readLine();
				if (inmsg != null)
					outmsg = "EXIT";		
			}
			
			
			sums[workerID] = Double.parseDouble(inmsg) ; 
			dataSocket.close();	

		} catch (IOException e)	{		
	 		System.out.println("I/O Error " + e);
		}
	}
	
	
	

}
