%{
	#include<stdio.h>
	#include"y.tab.h"
	
%}

%%
[\t ]	;
am|is|are|have|were|was|can|will|would|could|should|go|goes|run	{printf("%s==>VERB",yytext);return (VERB);}
very|simply|gently	{printf("%s==>ADVERB",yytext);return (ADVERB);}
and|or|also|so|but|if|then	{printf("%s==>CONJUNCTION",yytext);return (CONJUNCTION);}
fast|good|honest|sad {printf("ADJECTIVE\t==>%s\n",yytext);return (ADJECTIVE);}
I|He|she|we|they|you|this {printf("PRONOUN\t==>%s\n",yytext);return (PRONOUN);}
in|on|to {printf("PREPOSITION\t==>%s\n",yytext);return (PREPOSITION);}
[a-zA-Z]+	{printf("%s==>NOUN",yytext);return (NOUN);}
.	;

%%

int yywrap(){
	return 1;
}
