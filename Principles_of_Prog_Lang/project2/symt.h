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
#include "dh_syntax.h"

typedef struct symbol{
	char* name;
	int isType;
	int typeNum;
} sym_t;

int type_dec = TRUE; 	/*type/var declaration mode*/
sym_t s_table[200];		/*symbol_table holds 200 symbols*/
int symbol_count = 4;	/*num of symbols in table*/
int new_type = 14;		/*for creation of new type numbers*/

void error_check(int i){
	 printf("ERROR CODE %d\n",i);
	 exit(0);
}

sym_t make_symbol(char* id, int type, int typeN){
	sym_t newSym;
	newSym.name = id;
	newSym.isType = type;
	newSym.typeNum = typeN;
	return newSym;
}

void st_lookup(struct symbol* s){
	int i,j,k;
	int found = FALSE;
	for(i=0;i<symbol_count;i++){
		if(strcmp(s->name,s_table[i].name)== 0){
			found = TRUE;
			s->typeNum = s_table[i].typeNum;
		
		}
	}
	if(found != TRUE){
		if(s->typeNum == 0){
			s->typeNum = new_type;
			new_type++;
		}	
		s_table[symbol_count] = *s;
		symbol_count++;
	}
}

void symt_init(){
	sym_t intSym;
	sym_t realSym;
	sym_t boolSym;
	sym_t stringSym;
	
	intSym.name = "INT";
	intSym.isType = TRUE;
	intSym.typeNum = 10;
	
	realSym.name = "REAL";
	realSym.isType = TRUE;
	realSym.typeNum = 11;
	
	stringSym.name = "STRING";
	stringSym.isType = TRUE;
	stringSym.typeNum = 12;
	
	boolSym.name = "BOOLEAN";
	boolSym.isType = TRUE;
	boolSym.typeNum = 13;
	
	s_table[0] = intSym;
	s_table[1] = realSym;
	s_table[2] = stringSym;
	s_table[3] = boolSym;
}

/*---------------------------------------------------------------------
  Symbolizes explicit types and variables utilizing a similar method 
  to the print functions found in semantic.c 
---------------------------------------------------------------------*/
void type_var(struct declNode* dn){
	if(dn->type_decl_section != NULL)
		sym_type_section(dn->type_decl_section);
	if(dn->var_decl_section != NULL){
		type_dec = FALSE;
		sym_var_section(dn->var_decl_section);	
	}
	type_dec = TRUE;	
}

void sym_var_section(struct var_decl_sectionNode* vds)
{
	if (vds->var_decl_list != NULL)
		sym_var_list(vds->var_decl_list);
}

void sym_var_list(struct var_decl_listNode* vdl)
{
	sym_var(vdl->var_decl);
	if (vdl->var_decl_list != NULL)
		sym_var_list(vdl->var_decl_list);	
}

void sym_var(struct var_declNode* v)
{	
	sym_t temp;
	int id_type;
	if(v->type_name->type == ID){
		temp = make_symbol(v->type_name->id, type_dec, 0);
		st_lookup(&temp);
		id_type = temp.typeNum;
	}else{
		id_type = v->type_name->type;
	}
	sym_id_list(v->id_list, id_type);
}

void sym_type_section(struct type_decl_sectionNode* tds)
{
	if (tds->type_decl_list != NULL)
		sym_type_list(tds->type_decl_list);
}

void sym_type_list(struct type_decl_listNode* tdl)
{
	sym_type(tdl->type_decl);
	if (tdl->type_decl_list != NULL)
		sym_type_list(tdl->type_decl_list);	
}

void sym_type(struct type_declNode* t)
{	
	sym_t temp;
	int id_type;
	if(t->type_name->type == ID){
		temp = make_symbol(t->type_name->id, type_dec, 0);
		st_lookup(&temp);
		id_type = temp.typeNum;
	}else{
		id_type = t->type_name->type;
	}
	sym_id_list(t->id_list, id_type);
}

void sym_id_list(struct id_listNode* idl, int typeN){
	sym_t temp;
	temp = make_symbol(idl->id,type_dec,typeN);
	st_lookup(&temp);
	if(idl->id_list != NULL){
		sym_id_list(idl->id_list,typeN);
	}
}
	
	
/*---------------------------------------------------------------------
  Checks assignments utilizing a similar method 
  to the print functions found in semantic.c 
---------------------------------------------------------------------*/
#define PRIMARY 0
#define EXPR 1

void check_body(struct bodyNode* body){
	check_stmt_list(body->stmt_list);
}

void check_stmt(struct stmtNode* stmt)
{
	if (stmt->stmtType == ASSIGN)
		check_assign_stmt(stmt->assign_stmt);
	else 
		check_while_statement(stmt->while_stmt);
}

void check_stmt_list(struct stmt_listNode* slist)
{
	check_stmt(slist->stmt);
	if (slist->stmt_list != NULL)
		check_stmt_list(slist->stmt_list);
}

void check_while_statement(struct while_stmtNode* while_stmt){
	check_stmt_list(while_stmt->body->stmt_list);
}

void check_assign_stmt(struct assign_stmtNode* astmt)
{
	sym_t temp;
	int t;
	temp = make_symbol(astmt->id,0,0);
	st_lookup(&temp);
	if(temp.typeNum == new_type-1){
		t = check_expression_prefix(astmt->expr);
		if(temp.typeNum > 13 && t != -1)
			change_type(temp.typeNum, t); 
	}
}

int check_expression_prefix(struct exprNode* expr)
{
	sym_t temp;
	if (expr->tag == EXPR)
	{
		int i,j;
		i = check_expression_prefix(expr->leftOperand);
		j = check_expression_prefix(expr->rightOperand);
		if(i == j)
			return i;
		else
			return -1;
	} else
	if (expr->tag == PRIMARY)
	{
		if (expr->primary->tag == ID){
			temp = make_symbol(expr->primary->id,0,0);
			st_lookup(&temp);
			return ID;
			}
		else
		if (expr->primary->tag == NUM)
			return NUM;
		else
		if (expr->primary->tag == REALNUM)
			return REALNUM;

	}
}





	
