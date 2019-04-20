%{
	#include<stdio.h>
	void yyerror(char *s);
	int yylex();	
%}

%token INT FLOAT CHAR BOOL BOOLVAL CHARVAL NUM ID COMMA SC NL REAL ASSIGN LB RB

%%

s: type1|type2|type3|type4|array
;

type1: INT varlist1 SC NL {printf("VALID INTEGER DECLARATION"); return 0;}
;
type2: FLOAT varlist2 SC NL {printf("VALID FLOAT DECLARATION"); return 0;}
;
type3: CHAR varlist3 SC NL {printf("VALID CHARACTER DECLARATION"); return 0;}
;
type4: BOOL varlist4 SC NL {printf("VALID BOOLEAN DECLARATION"); return 0;}
;
array: INT ID LB RB SC NL	{printf("VALID ARRAY DECLARATION"); return 0;}
;
varlist1: ID|ID COMMA varlist1|ID ASSIGN NUM|ID ASSIGN NUM COMMA varlist1
;
varlist2: ID|ID COMMA varlist2|ID ASSIGN REAL|ID ASSIGN REAL COMMA varlist2
;
varlist3: ID|ID COMMA varlist3|ID ASSIGN CHARVAL|ID ASSIGN CHARVAL COMMA varlist3
;
varlist4: ID|ID COMMA varlist4|ID ASSIGN BOOLVAL|ID ASSIGN BOOLVAL COMMA varlist4
;

%%

void yyerror(char *s){
	printf("ERROR:%s",s);
}

int main(){

	yyparse();
	return 0;
}
