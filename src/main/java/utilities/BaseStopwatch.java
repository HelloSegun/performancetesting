package utilities;

public class BaseStopwatch {
	
	private long start;
	
	
	public void startTime() {
		this.start = System.currentTimeMillis();
	}
	
	
	public double elapsedTime() {
		long now = System.currentTimeMillis();
		return (now - start) / 1000.0;
	}
	
	
}
