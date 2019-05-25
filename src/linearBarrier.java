
public class linearBarrier {
	public long arrived = 0;
	private int totalThreads;
	private boolean waiting = true;
	private long mainThreadID ;
	
	public linearBarrier (int cores, long mainThreadID) {
		this.totalThreads = cores;
		this.mainThreadID = mainThreadID ;
        }		

	public synchronized void barrier()
	{
		arrived++;
		if (arrived == totalThreads) waiting = false;
		while (waiting)	{
			try  {
				wait();
			}
			catch (InterruptedException e) {};
		}
		arrived--;
		
		// if this is the main Thread and all the others threads hasn't finished  wait 
		while(mainThreadID == Thread.currentThread().getId() && arrived != 0 ) {
			try {
				wait() ;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
		
		if (arrived == 0) waiting = true;
		notifyAll();
	}

}
