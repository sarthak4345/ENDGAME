public class SJF {

	private int [] pid;
	private int[] bt;
	private int[] at;
	private int[] ct;
	private int[] tat;
	private int[] wt;
	private int systemTime;		//For keeping the track of time for different arrival time
	private boolean [] completed;	//Marking whether the process is completed or not
	private int totalCompleted;	//Keeping count of total completed process
	private int numberOfProcess;
	
	public SJF(int []pid,int []bt,int [] at) {
		this.pid = pid;
		this.bt = bt;
		this.at = at;
		ct = new int[pid.length];
		tat = new int[pid.length];
		wt = new int[pid.length];
		systemTime = 0;
		completed = new boolean[pid.length];
		for(int i=0;i<pid.length;i++)
			completed[i] = false;
		totalCompleted = 0;
		numberOfProcess = pid.length;
		
	}
	
	int i;
	public void schedule() {
		
		int copy[] = new int[numberOfProcess];
		for(int k=0;k<numberOfProcess;k++) {
			copy[k] = bt[k];
		}
	while(true) {
		
		
		//Terminating condition
		if(totalCompleted == numberOfProcess)
			break;
		
		int min = Integer.MAX_VALUE;		//Setting the burstTime at max
		int c = numberOfProcess;		
		for(i=0;i<numberOfProcess;i++) {
			
			if(at[i] <= systemTime && completed[i] == false && bt[i] < min) {
				min = bt[i];
				c = i;
			}
			
		}
		
		//This condition checks that if no process is available at current system time then we just have to increase the
		//system time so that the next process can come
		if(c == numberOfProcess)
			systemTime++;
		else {
			
			bt[c]--;
			systemTime++;
			min--;
			//Checking whether the process is completed or not
			if(bt[c] == 0) {
				ct[c] = systemTime;
				completed[c] = true;
				totalCompleted++;
			}
		}
	}
	
	//After exiting the above loop we will have the completion time of all the process
	//Now compute tat and wt
	System.out.println("PID	BT	AT	CT	TAT	 WT");
	for(int j=0;j<numberOfProcess;j++) {
		tat[j] = ct[j] - at[j];
		wt[j] = tat[j] - copy[j];
		System.out.println(pid[j]+"\t"+copy[j]+"\t"+at[j]+"\t"+ct[j]+"\t"+tat[j]+"\t"+wt[j]);
	}

	}
}
	
