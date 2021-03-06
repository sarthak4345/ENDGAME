package com.sarthak;
import java.util.*;
import java.io.*;

public class LRU {

	public static void RunLRU() {
		
		
		Scanner sc = new Scanner(System.in);
		int capacity;
		
		System.out.println("Enter the size of frame");
		capacity = sc.nextInt();
		
		System.out.println("Enter the number of pages");
		int n = sc.nextInt();
		
		int pages[] = new int[n];
		System.out.println("Enter the string of pages");
		for(int i=0;i<n;i++) 
			pages[i] = sc.nextInt();
		int pageFaults = 0;
		int pageHits = 0;
		
		
		HashSet<Integer> frame = new HashSet<>();		//For storing each page in the frame, and check whether it is present or not for page fault
		HashMap<Integer,Integer> indexes = new HashMap<>();		//Storing LRU indexes of the pages
		
		
		for(int i=0;i<n;i++) {
			
			//If the set can hold more pages 
			if(frame.size() < capacity) {
				
				//If the page is not present in the frame then add it
				if(!frame.contains(pages[i])) {
					frame.add(pages[i]);
					pageFaults++;
					indexes.put(pages[i],i);
				}else{
					pageHits++;
					indexes.put(pages[i],i);
				}
				
				//Store the LRU index of that page
				
			}
			//If the frame is full then we have to remove LRU page
			else {
				
				if(!frame.contains(pages[i])) {
					
					int lru = Integer.MAX_VALUE;
					int val = Integer.MIN_VALUE;
					//Checking index of each page present in the frame and removing the page having lowest index i.e LRU
					
					Iterator itr = frame.iterator();
					while(itr.hasNext()) {
						
						int temp = (int) itr.next();
						if(indexes.get(temp) < lru){
							lru = indexes.get(temp);
							val = temp;				//Stores the page value
						}
					}
					//After looping through the frame we will get the LRU page in val
					frame.remove(val);
					frame.add(pages[i]);
					pageFaults++;
					indexes.put(pages[i],i);
				}else{
					
					pageHits++;
					indexes.put(pages[i],i);
				}
				
				
				
			}
			
		}
		
		System.out.println("Number of page fault:"+pageFaults);
		System.out.println("Number of page Hits:"+pageHits);
		
	}

}
