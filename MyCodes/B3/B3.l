%{
	int nlines=0,nchar=0,nwords=0;

%}

%%

\n	{nlines++;}
[^ \n\t]+	{nwords++;nchar+=yyleng;}
.	{nchar++;}

%%

int yywrap(){
	return 1;
}

int main(){

	//FILE *yyin;
	yyin = fopen("input.txt","r");
	
	yylex();
	printf("LINES:%d\nWords:%d\nCHARACTERS:%d",nlines,nwords,nchar);
	return 0;
}
