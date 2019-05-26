
public class Calculation {
	protected long numSteps ;
	private double sum = 0.0;
	
	public Calculation(long numSteps) {
		this.numSteps = numSteps;
	} 

	public double calculate() {
		// TODO Auto-generated method stub
		double step = 1.0 / (double)numSteps;
        /* do computation */
        for (long i=0; i < numSteps; ++i) {
            double x = ((double)i+0.5)*step;
            sum += 4.0/(1.0+x*x);
        }
        
        double pi = sum * step;	
        
        return pi ;
	}

}
