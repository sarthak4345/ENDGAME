%{
	#include<stdio.h>
	void yyerror(char *s);
	int yylex();
	FILE *yyin;
%}

%token VERB PRONOUN NOUN PREPOSITION CONJUNCTION ADVERB ADJECTIVE

%%

s:simple|compound
;

simple: subject VERB object	{printf("SIMPLE SENTENCE");}
;
compound: subject VERB object CONJUNCTION subject VERB object	{printf("COMPOUND SENTENCE");}
;

subject: NOUN|PRONOUN
;

object: NOUN|ADJECTIVE NOUN|ADVERB NOUN|PREPOSITION NOUN
;


%%

void yyerror(char*s){

	printf("ERROR:%s",s);
}

int main(){

	yyin = fopen("input.txt","r");
	yyparse();
	fclose(yyin);
	return 0;
}
