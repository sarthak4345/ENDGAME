%{


%}

DIGIT [0-9]
NUMBER {DIGIT}+
REAL	{DIGIT}*[.]{NUMBER}
TEXT	[a-zA-Z]
KEYWORDS	"static"|"class"
DATATYPE	"int"|"float"|"double"|"long"|"void"|"String"
CONDITIONAL	"if"|"else"|"else if"|"switch"|case
ITERATIVE	"for"|"while"|"do"
PREPROCESSOR	"import"[^\n]*";"
SC		";"
IDENTIFIER	{TEXT}({TEXT}|{DIGIT}|"_")*
ARTH_OP		"+"|"-"|"*"|"/"|"%"
LOGICAL_OP	"&&"|"||"|"!"|"!="
REL_OP		"<"|">"|"<="|">="|"=="
UNARY_OP	"++"|"--"
ACCESS		"public"|"protected"|"private"
FUNCTION	{ACCESS}{DATATYPE}{IDENTIFIER}"("({DATATYPE}{IDENTIFIER})*")"

%%
[ \n\t]+ ;
{PREPROCESSOR}		{printf("%s===>PREPROCESSOR\n",yytext);}	
{CONDITIONAL}		{printf("%s===>CONDITIONAL\n",yytext);}
{ITERATIVE}		{printf("%s===>ITERATIVE\n",yytext);}
{DATATYPE}		{printf("%s===>DATATYPE\n",yytext);}
{ACCESS}		{printf("%s===>ACCESS SPECIFIER\n",yytext);}
{KEYWORDS}		{printf("%s===>KEYWORD\n",yytext);}
{IDENTIFIER}		{printf("%s==>IDENTIFIER\n",yytext);}
{SC}			{printf("%s===>DELIIMETER\n",yytext);}
{REAL}			{printf("%s===>REAL CONSTANT\n",yytext);}
{NUMBER}		{printf("%s===>INTEGER CONSTANT\n",yytext);}
{ARTH_OP}		{printf("%s===>ARTHEMATIC OPERATOR\n",yytext);}
{REL_OP}		{printf("%s===>REALTIONAL OPERATOR\n",yytext);}
{UNARY_OP}		{printf("%s===>UNARY OPERATOR\n",yytext);}
"=" {printf("%s\t==> ASSIGNMENT OP\n",yytext);}
"{"  {printf("%s\t==> BLOCK BEGIN\n",yytext);}
"}"  {printf("%s\t==> BLOCK END\n",yytext);}
"("  {printf("%s\t==> PARANTHESIS BEGIN\n",yytext);}
")"  {printf("%s\t==> PARENTHESIS END\n",yytext);}
. ;
%%

int yywrap(){
	return 1;
}

int main(int argc,char* argv[]){

	yyin = fopen(argv[1],"r");
	yylex();
	fclose(yyin);
}	
