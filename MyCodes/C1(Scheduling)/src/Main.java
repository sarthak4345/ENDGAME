import java.util.*;
import java.io.*;


public class Main {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the number of processes");
		int numOfProcess = sc.nextInt();
		int [] processes = new int[numOfProcess];
		int burstTime[] = new int[numOfProcess];
		int processId[] = new int[numOfProcess];
		
		System.out.println("Enter Process Id and Burst Time of each processs respectively");
		
		for(int i = 0;i<numOfProcess;i++) {
			processId[i] = sc.nextInt();
			burstTime[i] = sc.nextInt();
		}
		
		System.out.println("1)FCFS\n2)SJF\n3)PRIORITY\n4)ROUND ROBIN(AT=0)\nEnter Choice");
		int choice = sc.nextInt();
		
		switch(choice) {
		
			case 1:{
				int arrivalTime[] = new int[numOfProcess];
				System.out.println("Enter Arrival time");
				for(int i=0;i<numOfProcess;i++)
					arrivalTime[i] = sc.nextInt();
				FCFS f = new FCFS(processId,burstTime,arrivalTime);
				f.schedule();
				break;
			}
			
			case 2:{
				int arrivalTime[] = new int[numOfProcess];
				System.out.println("Enter Arrival time");
				for(int i=0;i<numOfProcess;i++)
					arrivalTime[i] = sc.nextInt();
				SJF sjf = new SJF(processId,burstTime,arrivalTime);
				sjf.schedule();
				break;
		
		}
			
			case 3:{
				int arrivalTime[] = new int[numOfProcess];
				System.out.println("Enter Arrival time");
				for(int i=0;i<numOfProcess;i++)
					arrivalTime[i] = sc.nextInt();
				int priority[] = new int[numOfProcess];
				System.out.println("Enter the priorities");
				for(int i=0;i<numOfProcess;i++)
					priority[i] = sc.nextInt();
				Priority p = new Priority(processId,burstTime,arrivalTime,priority);
				p.schedule();
			}
			
			case 4:
				System.out.println("Enter the Quantum Time");
				int quantum = sc.nextInt();
				RR r  = new RR(processId,burstTime,quantum);
				r.schedule();
				break;
		
	}

}
}