%{
	#include<stdio.h>
	#include"y.tab.h"
%}

DIGIT	[0-9]
REAL	{DIGIT}+[.]{DIGIT}*
LETTER	[a-zA-Z]
ASSIGN	"="

%%
[\t ]	;
int	{printf("%s===>DATATYPE\n",yytext); return(INT);}
float	{printf("%s===>DATATYPE\n",yytext); return(FLOAT);}
char	{printf("%s===>DATATYPE\n",yytext); return(CHAR);}
boolean	{printf("%s===>DATATYPE\n",yytext); return(BOOL);}
true|false	{printf("%s===>BOOLVAL\n",yytext); return(BOOLVAL);}
['][^\n\t][']	{printf("%s===>CHARVAL\n",yytext); return(CHARVAL);}
{REAL}		{printf("%s===>REAL\n",yytext); return (REAL);}
{DIGIT}+	{printf("%s===>NUM\n",yytext); return (NUM);}
[a-zA-Z]+[a-zA-Z0-9]*	{printf("%s===>ID\n",yytext); return (ID);}
","	{printf("%s==>COMMA\n",yytext); return (COMMA);}
";"	{printf("%s===>SC\n",yytext); return (SC);}
{ASSIGN}	{printf("%s===>ASSIGN\n",yytext); return (ASSIGN);}
\n	return NL;
"["	{printf("%s===>LB\n",yytext); return (LB);}
"]"	{printf("%s===>RB\n",yytext); return (RB);}
.	;
 
%%

int yywrap(){
	return 1;
}
