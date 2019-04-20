package com.sarthak;
import java.util.*;
import java.io.*;

public class BankersAlgorithm {

	static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		int resources,processes;
		System.out.println("Enter the number of resources");
		resources = sc.nextInt();
		System.out.println("Enter the number of processes");
		processes = sc.nextInt();
		
		//Required Data structures
		int available[] = new int[resources];
		int allocation[][] = new int[processes][resources];
		int max[][] = new int[processes][resources];
		int need[][] = new int[processes][resources];
		
		boolean finish[] = new boolean[processes];
		//initialize all the processes to false
		for(int i=0;i<processes;i++) {
			finish[i] = false;
		}
		
		//Accepting available vector
		
		System.out.println("Enter the available Vector");
		for(int i=0;i<resources;i++) {
			
			available[i] = sc.nextInt();
		}
		
		System.out.println("Enter the MAX matrix");
		acceptMatrix(max,processes,resources);
		
		System.out.println("Enter the allocation matrix");
		acceptMatrix(allocation,processes,resources);
		
		//Calculating the need matrix
		
		need = calculateNeed(max,allocation,processes,resources);
		
		int safeSequence[] = new int[processes];
		int count=0;	//For all processes
		
		while(count < processes) {
			
			boolean found=false;
			for(int i=0;i<processes;i++) {
				
				//If processes is not finished
				if(finish[i] == false) {
					int j;	
					//Check if need for all resources of this processes < available
					for(j=0;j<resources;j++) {
						if(need[i][j] > available[j]) {
							break;
						}
					}
					//If resources are satisfied then add that process to safeSequence
					if(j == resources) {
						for(int k=0;k<resources;k++) 
								available[k] += allocation[i][k];
						
						finish[i] = true;
						safeSequence[count++] = i;
						found = true;	//Disable terminating condition
					
					}
					
				}
				
				
			}
			
			if(found == false) {
				System.out.println("System is not in safe state");
				break;
			}
		}
		
		System.out.println("SAFE SEQUENCE");
		for(int i=0;i<processes-1;i++) 
			System.out.print(safeSequence[i]+"--->");
		
		System.out.print(safeSequence[processes-1]);
		
	}
	
	public static int[][] calculateNeed(int max[][],int allocation[][],int r,int c){
		
		int need[][] = new int[r][c];
		for(int i=0;i<r;i++) {
			for(int j=0;j<c;j++) {
				need[i][j] = max[i][j] - allocation[i][j];
			}
		}
		return need;
	}
	
	public static void acceptMatrix(int matrix[][],int r,int c) {
		
		for(int i=0;i<r;i++) {
			for(int j=0;j<c;j++) {
				
				matrix[i][j] = sc.nextInt();
			}
		}
	}
	
	public static void displayMatrix(int matrix[][],int r,int c) {
		
		for(int i=0;i<r;i++) {
			for(int j=0;j<c;j++) {
				
				System.out.print(matrix[i][j]+"\t");
			}
			System.out.print("\n");	
		}
	}
	
	
}


