import java.util.Scanner;


public class Scheduling {

	static int processId[];
	static int arrivalTime[];
	static int burstTime[];
	static int completionTime[];
	static int waitingTime[];
	static int tat[];
	static boolean flag[];
	static int k[];				//Storing copy of burst time for later purpose
	static int i,systemTime = 0,completed = 0;
	static Scanner sc = new Scanner(System.in);
	static int n;
	static float avgwt=0,avgtat = 0;
	static int priority[];
	
	
	public static void main(String[] args) {
		
		int choice;
		System.out.println("Enter the number of processes");
		n = sc.nextInt();
		processId = new int[n];
		arrivalTime = new int[n];
		burstTime = new int[n];
		completionTime = new int[n];
		waitingTime = new int[n];
		tat = new int[n];
		flag = new boolean[n];
		k = new int[n];
		priority  = new int[n];
		//Taking arrival time and burst time of processes from the user
		
				for(int i=0;i<n;i++){
					processId[i] = i;
					System.out.println("Enter the arrival time of "+processId[i]+"th process");
					arrivalTime[i] = sc.nextInt();
					System.out.println("Enter the burst time of "+processId[i]+"th process");
					burstTime[i] = sc.nextInt();
					flag[i] = false;
					waitingTime[i] = 0;
					k[i] = burstTime[i];
					
				}
		
		System.out.println("Enter by which method you want to compute the Average Waiting and Average turn About time:");
		System.out.println("1)FCFS\n2)SJF\n3)Round Robin\n4)Priority");
		System.out.print("Enter your choice");		
		choice = sc.nextInt();
		
		switch(choice){
		
			case 1:
			FCFS();
			break;
			
			case 2:
			SJF();
			break;
			
			case 3:
			roundRobin();
			break;
			
			case 4:
			priority();
			break;
			
			default:
			System.out.println("Wrong Choice");
			break;
		
		}
		
	}
	
	public static void FCFS(){
		

		completionTime[0] = burstTime[0];		
		
		for(int i=1;i<n;i++)
			completionTime[i] = completionTime[i-1]+burstTime[i];
			
		
		for(int i=0;i<n;i++){
			
			tat[i] = completionTime[i]-arrivalTime[i];
			waitingTime[i] = tat[i]-burstTime[i];
			
		}
		for(float i:tat){
			
			avgtat = avgtat+i;
		}
		
		for(float i:waitingTime){
			
			avgwt = avgwt+i;
		}
		
		avgtat /= n;
		avgwt /= n;
		
		System.out.println("Average Turn About Time:"+avgtat);
		System.out.println("Average Waiting Time:"+avgwt);
		
		}
	
	public static void SJF(){
		
		//This loop computes the completion time of all process
		while(true){
			
			if(completed == n)
				break;
			
			int min = 999;
			int c = arrivalTime.length;
			for(i = 0;i<n;i++){
				if(arrivalTime[i] <= systemTime && flag[i] == false && burstTime[i] < min){
					
					min = burstTime[i];
					c = i;
				}
				
			}
			
			if(c == n)
				systemTime++;
			else{
				
				burstTime[c]--;
				systemTime++;
				if(burstTime[c] == 0){
					completionTime[c] = systemTime;
					flag[c] = true;
					completed++;
					
				}
			}
		}
		
		//Computing the TAT and waiting time of the processes
		for(i =0;i<n;i++){
			
			tat[i] = completionTime[i] - arrivalTime[i];
			waitingTime[i] = tat[i] - k[i];
			avgwt += waitingTime[i];
			avgtat += tat[i];
		}
		
		System.out.println("pid  arrival  burst  complete turn waiting");
	    for(i=0;i<n;i++)
	    {
	    	System.out.println(processId[i] +"\t"+ arrivalTime[i]+"\t"+ k[i] +"\t"+ completionTime[i] +"\t"+ tat[i] +"\t"+ waitingTime[i]);
	    }
	    
	    System.out.println("\naverage tat is "+ (avgtat/n));
	    System.out.println("average wt is "+ (avgwt/n));
	}
	
	public static void roundRobin(){
		
		int timeSlice;
		System.out.println("Enter the value of time slice");
		timeSlice = sc.nextInt();
		
		int sum;
		do{
			
			for(i=0;i<n;i++){
				
					if(burstTime[i] > timeSlice){
						
						burstTime[i] -= timeSlice;
						for(int j=0;j<n;j++){
							
							//Increasing the waiting time of other processes
							if(j != i && burstTime[j] != 0){
								
								waitingTime[j] += burstTime[i];
							}
							
						}
						
					}
					else{
						
						burstTime[i] = 0;
						for(int j=0;j<n;j++){
							
							if(j != i && burstTime[j] != 0)
								waitingTime[j] += burstTime[i];
						}
						
					}
				
			}
			
			sum =0;
			for(int k = 0;k<n;k++)
				sum += burstTime[k];
			
		}while(sum != 0);
		
		for(int m=0;m<n;m++)
			tat[m] = waitingTime[m] + k[m];
	
		for(int m:waitingTime){
			
			avgwt += m;
		}
		for(int m:tat){
			
			avgtat += m;
		}
		
		System.out.println("Average waiting time:"+(avgwt/n));
		System.out.println("Average Turn About Time:"+(avgtat/n));
	
	}
	
	public static void priority(){
		
		for(i=0;i<n;i++){
			
				System.out.println("Enter the priority of "+processId[i]+"th process");
				priority[i] = sc.nextInt();
			}
			
			
			//Sorting based on the priority on the processes
			int flag = 0;
			for(int j=0;j<n;j++){
				
				flag = 0;
				for(int k=0;k<n-1;k++){
					
					if(priority[k] > priority[k+1]){
						
						int temp = priority[k];
						priority[k] = priority[k+1];
						priority[k+1] = temp;
						
						temp = burstTime[k];
						burstTime[k] = burstTime[k+1];
						burstTime[k+1] = temp;
						
						int pid = processId[k];
						processId[k] = processId[k+1];
						burstTime[k+1] = pid;
						flag = 1;
						
					}
					
				}
				
				
			}
		
		System.out.println("Priorities");
			
		for(int i=1;i<n;i++)
			completionTime[i] = completionTime[i-1]+burstTime[i];
		
		
		for(int i=0;i<n;i++){
			
			tat[i] = completionTime[i]-arrivalTime[i];
			waitingTime[i] = tat[i]-burstTime[i];
		}
		
		System.out.println("PID	BT	AT	CT	TAT	 WT");
		for(int j=0;j<n;j++) {
	
			System.out.println(processId[j]+"\t"+burstTime[j]+"\t"+arrivalTime[j]+"\t"+completionTime[j]+"    "+tat[j]+"\t"+waitingTime[j]);
		}
		
		avgtat = 0;
		avgwt = 0;
		
		for(int i:tat)
			avgtat = avgtat+i;
		
		for(int i:waitingTime)
			avgwt = avgwt+i;
		
		avgtat /= n;
		avgwt /= n;
		
		System.out.println("Average Turn About Time:"+avgtat);
		System.out.println("Average Waiting Time:"+avgwt);
			
	}
	
}
