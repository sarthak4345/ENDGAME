#include<stdio.h>
#include<sys/types.h>		//For fork
#include<unistd.h>			//For execu family
#include<stdlib.h>		//For pid_t

int main(){

	int choice;
	pid_t pid;
	pid_t ppid;
	
	printf("1)PS\n2)fork\n3)Join\n4)exec\n5)Wait\nEnter Choice");
	scanf("%d",&choice);
	switch(choice){
	
		case 1:
		system("ps");
		break;
		
		case 2:
		pid = fork();
		if(pid == 0){
			pid = getpid();			//Getting the id of the process
			ppid = getppid();		//Getting parent Id of the process
			printf("\nChild Process with Id:%d",pid);
			printf("\nParent of this process have ID:%d",ppid);
		}else{
			
			printf("\nParent Process having child with ID:%d",pid);
			pid = getpid();
			printf("\nProcess Id of this process is:%d",pid);
			
		}
		break;
		
		case 3:
		printf("Joined files  a.txt and b.txt\n");
		system("join a.txt b.txt");
		break;
		
		case 4:{
		
			char *args[] = {"./EXEC",NULL};
			execv(args[0],args);			//First argument is the path of the executable file to be executed
			printf("WILL NOT BE EXECUTED!");
			break;
		}
		
		case 5:{
		
			if(fork() > 0){
				printf("\nI was waiting for my child to complete its execution");
				wait(NULL);
				printf("\nNow Waiting Finish.I'm back!");
			
			}else{
			
				printf("\nMy parent is waiting until my execution\nI'll see you then\nBYE BYE");
				exit(0);
			}
		
		}
		
		break;
			
	}
}
