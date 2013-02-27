#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "compiler.h"

#define ASSIGNSTMT 0
#define PRINTSTMT 1
#define IFSTMT 2
#define WHILESTMT 3
#define GOTOSTMT 4
#define NOOPSTMT 5

#define TRUE 1
#define FALSE 0

#define KEYWORDS 14
#define RESERVED 38
#define VAR 1
#define BEGIN 2
#define END 3
#define ASSIGN 4 
#define IF 5
#define WHILE 6 
#define DO 7
#define THEN 8
#define PRINT 9
#define INT 10
#define REAL 11
#define STRING 12
#define BOOLEAN 13
#define TYPE 14
#define PLUS 15
#define MINUS 16
#define DIV 17
#define MULT 18
#define EQUAL 19
#define COLON 20 
#define COMMA 21
#define SEMICOLON 22
#define LBRAC 23
#define RBRAC 24
#define LPAREN 25
#define RPAREN 26
#define NOTEQUAL 27
#define GREATER 28
#define LESS 29
#define LTEQ 30
#define GTEQ 31
#define DOT 32
#define ID 33
#define NUM 34
#define REALNUM 35
#define ERROR 36
#define LBRACE 37
#define RBRACE 38
#define NOOP 39

int tElem;
vNode* symbolTable[20];
char valFlag[] = "";
char *reserved[] = 
	{	"",
		"VAR", 
		"BEGIN", 
		"END", 
		"ASSIGN", 
		"IF", 
		"WHILE", 
		"DO", 
		"THEN", 
		"print", 
        "INT",
		"REAL",
		"STRING",
		"BOOLEAN",
		"TYPE",
		"+",
		"-", 
		"/", 
		"*", 
		"=", 
		":", 
		",", 
		";", 
		"[", 
		"]", 
		"(", 
		")", 
		"<>", 
		">", 
		"<",
		"<=",
		">=",
        ".",
        "ID",
        "NUM",
        "REALNUM",		
        "ERROR",
		"{",
		"}"
	};

int printToken(int ttype)
{
   if (ttype <= RESERVED)
   {   printf("%s\n",reserved[ttype]);
       return 1;
   } else
       return 0; 
}
//---------------------------------------------------------

//---------------------------------------------------------
// Global Variables associated with the next input token
#define MAX_TOKEN_LENGTH 100

char token[MAX_TOKEN_LENGTH];      // token string
int  ttype;                        // token type
int  activeToken = FALSE;                  
int  tokenLength;


int line_no = 1;

//----------------------------------------------------------
int skipSpace()
{
   char c;

   c = getchar(); 
   line_no += (c == '\n');
   while (!feof(stdin) && isspace(c))
   {    c = getchar(); 
        line_no += (c == '\n');
   }

   // return character to input buffer if eof is not reached
   if (!feof(stdin)) 
        ungetc(c,stdin);
}

int isKeyword(char *s)
{
     int i;

     for (i = 1; i <= KEYWORDS; i++)
	if (strcmp(reserved[i],s) == 0)
	   return i;
     return FALSE;
}

// ungetToken() simply sets a flag so that when getToken() is called
// the old ttype is returned and the old token is not overwritten 
// NOTE: BETWEEN ANY TWO SEPARATE CALLS TO ungetToken() THERE MUST BE
// AT LEAST ONE CALL TO getToken()
// CALLING TWO ungetToken() WILL NOT UNGET TWO TOKENS  
void ungetToken()
{
    activeToken = TRUE;
}


int scan_number()
{
	char c;
	
	c = getchar();
	if (isdigit(c))
	{	// First collect leading digits before dot
		// 0 is a nNUM by itself
		if (c == '0')	      
		{	token[tokenLength] = c;
			tokenLength++;
			token[tokenLength] = '\0';
		} else
		{	while (isdigit(c))
			{	token[tokenLength] = c;
				tokenLength++;;
				c = getchar();
			}
			ungetc(c,stdin);
			token[tokenLength] = '\0';
		}

		// Check if leading digits are integer part of a REALNUM
		c = getchar();
		if (c == '.')
		{	c = getchar();
			if (isdigit(c))
			{	token[tokenLength] = '.';
				tokenLength++;
				while (isdigit(c))
				{	token[tokenLength] = c;
					tokenLength++;
					c = getchar();
				}
				token[tokenLength] = '\0';
				if (!feof(stdin)) 
					ungetc(c,stdin);
				return REALNUM;
			} else
			{	ungetc(c, stdin);    // note that ungetc returns characters on a stack, so we first
				c = '.';             // return the second character and set c to '.' and return c again
				ungetc(c,stdin);				                                 
				return  NUM;         
                        }
		} else
		{	ungetc(c, stdin);
			return NUM;
		}
	} else
		return ERROR;   
}


int scan_id_or_keyword()
{
	int ttype;
	char c;

	c = getchar();
	if (isalpha(c))
	{	while (isalnum(c))
		{	token[tokenLength] = c;
			tokenLength++;
			c = getchar();
		}
		if (!feof(stdin)) 
			ungetc(c,stdin); 
             
		token[tokenLength] = '\0';		                
		ttype = isKeyword(token); 
		if (ttype == 0) 
			ttype = ID;
		return ttype;
	} else
		return ERROR;
}                            
   

int getToken()
{	char c;
 
       if (activeToken)
       { activeToken = FALSE;
         return ttype;
       }   // we do not need an else because the function returns in the body 
           // of the if
   
       skipSpace();   
       tokenLength = 0;
       c = getchar();
       switch (c)
       {   case '.': return DOT;
           case '+': return PLUS;
           case '-': return MINUS;
           case '/': return DIV;
           case '*': return MULT;
           case '=': return EQUAL;
           case ':': return COLON;
           case ',': return COMMA;
           case ';': return SEMICOLON;
           case '[': return LBRAC;
           case ']': return RBRAC;
           case '(': return LPAREN;
           case ')': return RPAREN;
	   case '{': return LBRACE;
	   case '}': return RBRACE;
           case '<':
                      c = getchar();
                       if (c == '=')
                          return LTEQ;
                       else
                       if (c == '>')
                          return NOTEQUAL;
                       else
                       {
                          ungetc(c,stdin);
                          return LESS;
                       }
           case '>': 
                        c = getchar();
                        if (c == '=')
                           return GTEQ;
                        else
                        {
                           ungetc(c, stdin);
                           return GREATER;
                        }
           
           default :
			if (isdigit(c))
			{	ungetc(c,stdin);
				return scan_number();
			}
			else
			if (isalpha(c))
			{	ungetc(c,stdin);
				return scan_id_or_keyword();
			}
			else
			if (c == EOF)
				return EOF;
			else
				return ERROR;
	}
}


void parse_variables(){
	ttype = getToken();
	if (ttype == VAR){
		ttype = getToken();
		tElem = 0;
		while(ttype != SEMICOLON){
			if(ttype != COMMA){
				symbolTable[tElem] = make_vNode();
				symbolTable[tElem]->id = (char*) malloc(tokenLength+1);
				strcpy(symbolTable[tElem]->id,token);
				symbolTable[tElem]->value = 0;
				tElem++;
				ttype = getToken();
			} else {
				ttype = getToken();
			}
		}
	}
}

vNode* add_stValue(char* s){
	int i;
	vNode* v;
	i = atoi(s);
	v = make_vNode();
	v->id = valFlag;
	v->value = i;
	symbolTable[tElem] = v;
	tElem++;
	return v;	
}

vNode* find_var(char* c){
	int i;
	for(i=0; i<tElem; i++){
		if(strcmp(symbolTable[i]->id,c) == 0){
			return symbolTable[i];
		}
	}
}

sNode* find_tail(sNode* s){
	while(s->next != NULL){
		s = s->next;
	}
	return s;
}

aNode* assign_stmt(){
	int i;
	aNode* a;
	a = make_aNode();
	ttype = getToken();
	a->lvalue = find_var(token);
	ttype = getToken();
	ttype = getToken();
	if(ttype == NUM){
		a->op1 = add_stValue(token);
		ttype = getToken();
		if(ttype == SEMICOLON){
			ungetToken();
			return a;
		}else{
			a->operator = ttype;
			ttype = getToken();
			if(ttype == NUM){
				a->op2 = add_stValue(token);
				return a;
			}else{
				a->op2 = find_var(token);
				return a;
			}
		}
	}else if(ttype == ID){
		a->op1 = find_var(token);
		ttype = getToken();
		if(ttype != SEMICOLON){
			a->operator = ttype;
			ttype = getToken();
			if(ttype == NUM){
				a->op2 = add_stValue(token);
				return a;
			}else{
				a->op2 = find_var(token);
				return a;
			}
		}else{
			ungetToken();
			return a;
		}
	}else{
		printf("assignment error. No go my friend :(\n");
	}
}

cNode* condition_stmt(){
	cNode* c;
	c = make_cNode();
	ttype = getToken();
	if(ttype == ID){
		c->op1 = find_var(token);
		ttype = getToken();
		c->operator = ttype;
		ttype = getToken();
		if(ttype == NUM){
			c->op2 = add_stValue(token);
		}else{
			c->op2 = find_var(token);
		}
		ttype = getToken();
		if(ttype == LBRACE){
			ungetToken();
			c->trueBranch = create_program_body();
			return c;
		}else{
			printf("condition error. Oh no :(\n");
		}
	}
}

iNode* if_stmt(){
	iNode* i;
	i = make_iNode();
	
	ttype = getToken();
	if(ttype == IF){
		i->condition = condition_stmt();
		return i;
	}
	else{
		printf("if error. That sucks :(\n");
	}
}
	
wNode* while_stmt(){
	wNode* w;
	w = make_wNode();
	
	ttype = getToken();
	if(ttype == WHILE){
		w->condition = condition_stmt();
		return w;
	}
	else{
		printf("while error. bummer :(\n");
	}
}

pNode* print_stmt(){
	pNode* p;
	p = make_pNode();
	ttype = getToken();
	ttype = getToken();
	if(ttype == ID){
		p->id = find_var(token);
		return p;
	}else{
		printf("print error. How sad :(\n");
	}
}

sNode* stmt_(){
	sNode* s;
	sNode* noop;
	sNode* temp;
	sNode* temp2;
	gNode* g;
	s = make_sNode();
	ttype = getToken();
	if(ttype == ID){
		ungetToken();
		s->sType = ASSIGNSTMT;
		s->assign_st = assign_stmt();
		return s;
	}else if(ttype == WHILE){
		ungetToken();
		s->sType = WHILESTMT;
		s->while_st = while_stmt();
		noop = make_sNode();
		g = make_gNode();
		g->target = s;
		temp = find_tail(s->while_st->condition->trueBranch);
		temp2 = make_sNode();
		temp2->sType = GOTOSTMT;
		temp2->goto_st = g;
		temp->next = temp2;
		s->while_st->condition->falseBranch = noop;
		s->next = noop;
		noop->sType = NOOPSTMT;
		return s;
	}else if(ttype == IF){
		ungetToken();
		s->sType = IFSTMT;
		s->if_st = if_stmt();
		noop = make_sNode();
		temp = find_tail(s->if_st->condition->trueBranch);
		temp->next = noop;
		s->if_st->condition->falseBranch = noop;
		s->next = noop;
		noop->sType = NOOPSTMT;
		return s;
	}else if(ttype == PRINT){
		ungetToken();
		s->sType = PRINTSTMT;
		s->print_st = print_stmt();
		return s;
	}
}

sNode* stmt_list(){
	sNode* s;
	sNode* s1;
	sNode* noop;
	sNode* temp;
	sNode* temp2;
	gNode* g;
	s = make_sNode();
	ttype = getToken();
	if(ttype == ID){
		ungetToken();
		s->sType = ASSIGNSTMT;
		s->assign_st = assign_stmt();
	}else if(ttype == WHILE){
		ungetToken();
		s->sType = WHILESTMT;
		s->while_st = while_stmt();
		noop = make_sNode();
		g = make_gNode();
		g->target = s;
		temp = find_tail(s->while_st->condition->trueBranch);
		temp2 = make_sNode();
		temp2->sType = GOTOSTMT;
		temp2->goto_st = g;
		temp->next = temp2;
		s->while_st->condition->falseBranch = noop;
		s->next = noop;
		noop->sType = NOOPSTMT;
	}else if(ttype == IF){
		ungetToken();
		s->sType = IFSTMT;
		s->if_st = if_stmt();
		noop = make_sNode();
		temp = find_tail(s->if_st->condition->trueBranch);
		temp->next = noop;
		s->if_st->condition->falseBranch = noop;
		s->next = noop;
		noop->sType = NOOPSTMT;
	}else if(ttype == PRINT){
		ungetToken();
		s->sType = PRINTSTMT;
		s->print_st = print_stmt();
	}
	
		ttype = getToken();

	if(ttype == SEMICOLON){
		ttype = getToken();
		if(ttype != RBRACE){
			ungetToken();
			s1 = stmt_list();
			s->next = s1;
			return s;
		}else{
			ungetToken();
			return s;
		}
	}else if(ttype == RBRACE){
		ttype = getToken();
		if(ttype != RBRACE){
			ungetToken();
			s1 = stmt_list();
			s->next->next = s1;
			return s;
		}else{
			ungetToken();
			return s;
		}
	}else{
		ungetToken();
		s1 = stmt_list();
		s->next->next = s1;
		return s;
	}
		
}

sNode* create_program_body(){
	sNode* first;
	sNode* mainList;
	
	first = make_sNode();
	ttype = getToken();
	if(ttype == LBRACE){
		ttype = getToken();
		if (ttype == WHILE || ttype == IF){
			ungetToken();
			first = stmt_list();
		}else{
			ungetToken();	
			first = stmt_();
		}
		ttype = getToken();
		if(ttype == SEMICOLON){
			ttype = getToken();
			if(ttype != RBRACE){
				ungetToken();
				mainList = stmt_list();
				ttype = getToken();
				if(ttype == RBRACE){
					ungetToken();
					first->next = mainList;
					return first;
				}
			}else{
				ungetToken();
				return first;
			}
		}else{
			ungetToken();
			return first;
		}
	}
	printf("creation error. Uh oh :(\n");
}

void printTest(sNode* program){
	printf("Symbol elements = %d\n", tElem);
	int test = 0;
	int i;
	for(i=0; i<tElem; i++){
		printf("Element %d id = ", i);
		printf("%s value = %d\n", symbolTable[i]->id, symbolTable[i]->value);
	}
	while (program->next != NULL){
		test++;
		program = program->next;
	}
	printf("Program execution nodes: %d\n", test+1);
}

void execute_program(sNode* s){
	int i = 0;
	while(s != NULL){
		switch(s->sType){
			case ASSIGNSTMT:
				switch(s->assign_st->operator){
					case PLUS: 
						i=(s->assign_st->op1->value)+(s->assign_st->op2->value);
						s->assign_st->lvalue->value = i;
						break;
					case MINUS:
						i=(s->assign_st->op1->value)-(s->assign_st->op2->value);
						s->assign_st->lvalue->value = i;
						break;
					case DIV:
						i=(s->assign_st->op1->value)/(s->assign_st->op2->value);
						s->assign_st->lvalue->value = i;
						break;
					case MULT:
						i=(s->assign_st->op1->value)*(s->assign_st->op2->value);
						s->assign_st->lvalue->value = i;
						break;
					default:
						i = s->assign_st->op1->value;
						s->assign_st->lvalue->value = i;
				}
				s = s->next;
				break;
				
			case PRINTSTMT: 
				printf("%d\n", s->print_st->id->value);
				s = s->next;
				break;
			
			case IFSTMT:
				switch(s->if_st->condition->operator){
				 case GREATER:
				  
 if((s->if_st->condition->op1->value)>(s->if_st->condition->op2->value)){
				  		s = s->if_st->condition->trueBranch;
				  		break;
				  }else{
				  		s = s->if_st->condition->falseBranch;
				  		break;
				  }
				 case LESS:
				 
  if((s->if_st->condition->op1->value)<(s->if_st->condition->op2->value)){
				  		s = s->if_st->condition->trueBranch;
				  		break;
				  }else{
				  		s = s->if_st->condition->falseBranch;
				  		break;
				  }
				 case NOTEQUAL:
				 
 if((s->if_st->condition->op1->value)!=(s->if_st->condition->op2->value)){
				  		s = s->if_st->condition->trueBranch;
				  		break;
				  }else{
				  		s = s->if_st->condition->falseBranch;
				  		break;
				  }
				}
				break;
				
			case WHILESTMT:
				switch(s->while_st->condition->operator){
				 case GREATER:
				  
 if((s->while_st->condition->op1->value)>(s->while_st->condition->op2->value)){
				  		s = s->while_st->condition->trueBranch;
				  		break;
				  }else{
				  		s = s->while_st->condition->falseBranch;
				  		break;
				  }
				 case LESS:
				 
 if((s->while_st->condition->op1->value)<(s->while_st->condition->op2->value)){
				  		s = s->while_st->condition->trueBranch;
				  		break;
				  }else{
				  		s = s->while_st->condition->falseBranch;
				  		break;
				  }
				 case NOTEQUAL:
				 
 if((s->while_st->condition->op1->value)!=(s->while_st->condition->op2->value)){
				  		s = s->while_st->condition->trueBranch;
				  		break;
				  }else{
				  		s = s->while_st->condition->falseBranch;
				  		break;
				  }
				}
				break;
				
			case GOTOSTMT:
				s = s->goto_st->target;
				break;
			
			case NOOPSTMT:
				s = s->next;
				break;
			
			default: printf("Error. Statement Type not recognized.");
					 exit(1);
		}
	}
}

main(){
	sNode* program;
	parse_variables();
	program = create_program_body();
	execute_program(program);
	printf("Finished!");
	
	
	return 0;
}/*end main*/



