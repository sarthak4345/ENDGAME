package round_robin;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class round_robin {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner sc = new Scanner(System.in);
		
		Queue<Integer> queue = new LinkedList<>();
		
		System.out.println("Enter num of processes: ");
		int num_of_process = sc.nextInt();
		
		int[] pid = new int[num_of_process];		
		int[] at = new int[num_of_process];
		int[] bt = new int[num_of_process];
		int[] rt = bt;
		int[] comp_time = new int[num_of_process];
		int q = -1;
		
 		System.out.println("Enter process' id: ");
		for(int i = 0;i<num_of_process;i++)
		{
			pid[i] = sc.nextInt();
		}
		
		System.out.println("Enter Arrival Times: ");
		for(int i = 0;i<num_of_process;i++)
		{
			at[i] = sc.nextInt();
		}
		
		System.out.println("Enter Burst timw: ");
		for(int i = 0;i<num_of_process;i++)
		{
			bt[i] = sc.nextInt();
		}
		
		System.out.println("Enter quantum:");
		q=sc.nextInt();
		
		
		int current_time = 0;
		
		//sort wrt arrival time
		for(int i = 0;i<num_of_process-1;i++)
		{
			for(int j = 0;j<num_of_process-1;j++)
			{
				if(at[j]>at[j+1])
				{
					int temparr = at[j];
					at[j] = at[j+1];
					at[j+1] = temparr;
					
					int tempb = bt[j];
					bt[j] = bt[j+1];
					bt[j+1] = tempb;
					
					int tempid = pid[j];
					pid[j] = pid[j+1];
					pid[j+1] = tempid;
				}
			}
		}
		
		///0th index process now is the first process to occur because it has least at
		int selected_process = 0;
		current_time = current_time+at[selected_process];
		
		int num_of_complete_process = 0;
		
		//add selected process to queue
		queue.add(selected_process);
	
		while(num_of_complete_process!=num_of_process)	
		{
			if(rt[selected_process]>q)
			{
				current_time = current_time + q;
				rt[selected_process] = rt[selected_process] - q;
			}
			else
			{
				++num_of_complete_process;
				current_time = current_time + rt[selected_process];
				rt[selected_process] = 0;
				comp_time[selected_process] = current_time;
				System.out.println("For process "+pid[selected_process]+" completion time: "+comp_time[selected_process]);
			}
			
			//to add processes to queue 
			for(int p = 0;p<num_of_process;p++)
			{
				//the current process is not to be added directly to queue it has to be added only when all those process which arrived while selected one was executing are added to queue 
				if(p==selected_process)
				{
					continue;
				}
				//add the pth process to queue only if it has arrived while the current process was executing
				if((current_time-at[p])>=0 && (!queue.contains(p)))
				{
					if(rt[p]>0)
					{
					//add at tail of queue
					queue.add(p);
					}
				}
			}
			
			//the current process is at the head od queue, hence remove it
 			queue.poll();
 			//if the selected process' remaintime is not yet 0 add it to tail of queue
			if(rt[selected_process]!=0)
			{
				queue.add(selected_process);
			}
			///if queue is not empty then update selected process to new head
			if(!queue.isEmpty())
			{
				selected_process = queue.peek();
			}
		}
		for(int i = 0;i<selected_process;i++)
			{
				System.out.println("For process "+pid[i]+" completion time: "+comp_time[i]);
			}
	}

}
