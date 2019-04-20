
public class RR {

	private int[] pid;
	private int[] bt;
	private int[] ct;
	private int[] tat;
	private int[] wt;
	private int[] rt;
	private int quantum;
	private int numberOfProcess;
	
	public RR(int [] pid,int []bt,int quantum) {
		
		this.pid = pid;
		this.bt = bt;
		this.quantum = quantum;
		numberOfProcess = pid.length;
		ct = new int[numberOfProcess];
		tat = new int[numberOfProcess];
		wt = new int[numberOfProcess];
		rt = new int[numberOfProcess];
	}

	public void schedule() {
		
		int systemTime = 0;
		for(int i=0;i<numberOfProcess;i++)
			rt[i] = bt[i];
		
		int totalCompleted = 0;
		while(totalCompleted != numberOfProcess) {
			
			boolean done = true;
			for(int i=0;i<numberOfProcess;i++) {
				
				//Task is pending
				if(rt[i] > 0) {
					
					done = false;
					if(rt[i] > quantum) {
						rt[i] -= quantum;
						systemTime += quantum;
					}else {
						
						++totalCompleted;
						systemTime += rt[i];
						rt[i] = 0;
						ct[i] = systemTime;
						tat[i] = ct[i];		//Since no arrival time
						wt[i] = tat[i]-bt[i];		
					}
					
				}
				
			}
			if(done == true)
				break;
			
		}
		
		System.out.println("PID	BT	CT	TAT	 WT");
		for(int j=0;j<numberOfProcess;j++)
			System.out.println(pid[j]+"\t"+bt[j]+"\t"+ct[j]+"\t"+tat[j]+"\t"+wt[j]);
		
	}
	
}


