
public class Process {
	public String id;
    public double burstTime;
    public double arrivalTime;
    public double waitingTime;
    public double turnaroundTime;
    public double responseTime = -1;
    public double remainingTime;
    public int roundCount = 0;

    // FCFS, SJF, SRTF
    public Process(String id, double burstTime, double arrivalTime) {
        this.id = id;
        this.burstTime = burstTime;
        this.arrivalTime = arrivalTime;
        this.remainingTime = burstTime;
    }
    
    // Round Robin
    public Process(String id, double burstTime) {
        this.id = id;
        this.burstTime = burstTime;
        this.remainingTime = burstTime;
    }
}
