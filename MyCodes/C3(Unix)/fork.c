#include<stdio.h>
#include <sys/types.h>		//INcludes fork

int main(){

	pid_t pid;
	pid = fork();
	if(pid>0){
		printf("Parent\n");
		printf("Created Child Process:%d\n",pid);
	}
	else{
		pid = getpid();
		printf("Child\t");
		printf("Id:%d",pid);
	}
}
