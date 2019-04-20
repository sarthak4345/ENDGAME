#include<stdio.h>
#include <sys/types.h>
#include<unistd.h>	//for execu family
#include<stdlib.h>

int main(){

	int choice;
	char cont;
	pid_t pid;
	
	do{
		printf("Enter which command you want to execute\n");
		printf("1)ps\n2)fork\n3)join\n4)exec family\n5)Wait demo\nchoice:");
		scanf("%d",&choice);
	
	
	switch(choice){
	
			case 1:
			system("ps");		//Gives the information about which processes are currently running
			break;
		
			case 2:
			pid = fork();		//Creates another copy of the process i.e a.out
			
/*
	Fork returns the pid of child process to the parent process. So when fork() > 0 that means we are running the parent process.As the child don't need to know its own 		pid so when fork() == 0 that means we are running the child process	
*/			
			if(pid == 0){
				
				pid = getpid();
				printf("In Child Process.Id:%d",pid);		
				
			}else{
				pid = getppid();
				printf("In Parent Process.Id:%d",pid);
			}
			
			break;
			
			case 3:
			printf("Joined files a.txt and b.txt\n");
			system("join a.txt b.txt");
			break;
		
			/*
				exec family to used to execute another process i.e it can be used to run one program from another program.
				Its syntax is:
				int execv(const char *path, char *const argv[]);
				file = executable file name to be executed
			*/		
			case 4:{
				char *args[] = {"./EXEC",NULL};
				execv(args[0],args);
				/*	
					All statements are ignored after execv() call as this whole  
       				 	process(C3.c) is replaced by another process (EXEC.c) 
        			*/
				break;
			
			}
			/*
				If any process has more than one child processes, then after calling wait(), parent process has to be in wait state if no child terminates. 
			*/
			
			case 5:
			
			if(fork() == 0){
				int c;
				printf("Hello from child\n");
				printf("Do you want to terminate child process\n(1/0)");
				scanf("%d",&c);
				if(c == 1)
					exit(0);
				else
					printf("Still in child process\nParent process in waiting state\n");
					
			}else{
			
				wait(NULL);				//Parent process is in the waiting state until the child process exit from process
				printf("Child terminated\n");
				printf("Hello from parent\n");
			}
			break;
			
			default:
			printf("Enter correct choice");
			break;
	
		}
			printf("Do you want to conitue\n");
			scanf("%s",&cont);	
	}while(cont == 'y' || cont == 'Y');

}
