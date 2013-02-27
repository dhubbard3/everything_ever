/*----------------------------------------------------------------------------
Note: the code in this file is not to be shared with anyone or posted online.
(c) Rida Bazzi, 2011


PROJECT 2. SEMANTIC ANALYSIS: HINDLEY_MILNER TYPE CHECKING
==========================================================

     IMPORTANT: 

        - IT WILL TAKE AROUND    20 HOURS    TO FINISH THIS PROJECT
        - THIS PROJECT IS CONCEPTUALLY MORE INVOLVED THAN PROJECT 0 and 
          PROJECT 1

     ASSIGNED: Wednesday October 12 2011
     DUE: Tuesday November 1st 2011 by 11:59pm  
           

I start with a top-down description of the project before getting into
the details of the various pieces.

1. Overview
===========
The goal of this project is to write a semantic checker for a language.
The input to the semantic checker will be a program and the output will
be information about the types of the variables in the program or
an error message if there is type mismatch. This is an illustration:

                       __________________      
                      |                  |     /------> error message
         program ---->| semantic checker |----|
                      |__________________|     \------> type information


The semantic checker enforces semantic rules on the input program. The
rules constrain variable usage: depending on the type of a variable, some
operations on the variable might be allowed or disallowed.

You are provided with an incomplete parser for the language and asked to
complete it and enforce the semantic rules of the language. The parser you 
are provided also builds a parse tree which can be convenient. The
parser you are provided rewrites the input program so that all expressions
are changed to prefix notation. The parse tree building is also not complete!
It does not handle while_stmt and condition

The language and the rules to be enforced are described in the following 
sections.

2. Program
==========

A program consists of two sections: a declaration section (decl) that contains
type and variable declarations and a body section (body) that contains 
statements of the program. The top-level grammar rules for a program are:
 
  (1) program           -> decl body
  (2) decl              -> type_decl_section var_decl_section

In what follows we elaborate on each program section.

3. Types and Variables
======================

3.1 Types
=========
The language has a number of built-in types. These are: INT, REAL, BOOLEAN, 
and STRING.

In addition to the built-in types, the language allows the declaration of
user-defined types. User-defined types are declared in a type_decl_section
(type declaration section). The syntax for type declarations is given by the 
following grammar:

   (3)  type_decl_section -> TYPE type_decl_list 
   (4)  type_decl_section ->                                    // epsilon 
   (5)  type_decl_list    -> type_decl type_decl_list
   (6)  type_decl_list    -> type_decl
   (7)  type_decl         -> id_list COLON type_name SEMICOLON
   (8)  type_name         -> REAL
   (9)  type_name         -> INT
   (10) type_name         -> BOOLEAN
   (11) type_name         -> STRING
   (12) type_name         -> ID

note that id_list in rule (7) is given below with variable declarations.

User-declared types can be explicit or implicit. 

   - Explicit user-declared types are names that have their first appearance 
     in the program as part of id_list of a declaration according to rule (7). 
   - Implicit user-declared types are are not built-in type and not explicit 
     user-declared types, but they appear as type_name in a declaration according 
     to (7) above or (17) below. 

> constraints on type declarations:

        (C1) Declaration of a built-in type should result in 
             a syntax error. This is already handled by the parser 
             that I provide.
        (C2) A user-declared type name should not be declared more than once
             in the TYPE section. The following should hold:
                 - An explicit declared type should not appear
                   in two different id_list(s) or more than once in the same
                   id_list
                 - A implicit user-declared cannot appear in an id_list of
                   a declaration according to (7).
        

3.2 Variables
=============

The language allows the explicit declaration of variables. This is done
in a var_decl_section defined whose syntax is defined as follows:

   (13) var_decl_section  -> VAR var_decl_list
   (14) var_decl_section  ->                                   // epsilon
   (15) var_decl_list     -> var_decl var_decl_list
   (16) var_decl_list     -> var_decl
   (17) var_decl          -> id_list COLON type_name SEMICOLON
   (18) id_list           -> ID COMMA id_list
   (19) id_list           -> ID


Variables are declared explicitly or implicitly. A variable is declared explicitly
if it appears in an id_list in a declaration according to (17). A variable is
declared implicitly if it is not declared expicitly but appears in the program 
body (see below).

> constraints on variable declarations:

        (C3) Redeclaration of a type name as a variable name is not allowed
        (C4) Multiple declarations of the same variable name is not allowed
        (C5) A name that is used as "type_name" in rule (17) should not 
             be declared as a variable (note that a name can appear as
             a type_name without being explicitly declared as a type. This rule
             is the same as rule (C3) but applied to implicitly declared types).


4. Program body
===============

The body of the program consists of a list of statements between two braces.
The grammar rules are:

   (20) body              -> LBRACE stmt_list RBRACE
   (21) stmt_list         -> stmt stmt_list
   (22) stmt_list         -> stmt

The actual work of type checking is done when processing individual statements 
(stmt). These are described next.

5. Statements
=============

A statement can be either an assignment (assign_stmt) or a while statement
(while_stmt). An assign_stmt assigns an expression to a variable. A while
statement has 3 parts: (1) the WHILE keyword, (2) a condition, and (3) a body
(this is a recursive definition). The grammar rules for stmt are the following:

   (23) stmt              -> while_stmt
   (24) stmt              -> assign_stmt
   (25) while_stmt        -> WHILE condition body
   (26) assign_stmt       -> ID EQUAL expr SEMICOLON
   (27) expr              -> term PLUS expr
   (28) expr              -> term MINUS expr
   (29) expr              -> term
   (30) term              -> factor MULT term
   (31) term              -> factor DIV term
   (32) term              -> factor
   (33) factor            -> LPAREN expr RPAREN
   (34) factor            -> NUM
   (35) factor            -> REALNUM
   (36) factor			  -> ID
   (37) condition         -> ID                          
   (38) condition         -> primary relop primary
   (39) primary           -> ID
   (40) primary           -> NUM
   (41) primary           -> REALNUM
   (42) relop             -> GREATER
   (43) relop             -> GTEQ
   (44) relop             -> LESS
   (45) relop             -> NOTEQUAL
   (46) relop             -> LTEQ


> constraints on statements and expressions:

      (C6)  assignment cannot be made between variables of different types
      (C7)  operations (op) cannot be applied to operands of different types
            (but can be applied to operands of same type including STRING 
            and BOOLEAN)
      (C8)  relations operators (relop) cannot be applied to operands of 
            different types (but can be applied to operands of same type
            including STRING and BOOLEAN)
      (C9)  NUM constants are of type INT
      (C10) REALNUM constants are of type REAL
      (C11) the result of primary relop primary is of type BOOLEAN 
      (C12) condition should be of type BOOLEAN (this is automatically 
            satisfied for rule (38))
      (C14) the type of an expression is the same as the type of its operands
            (which must have the same type by (C7))
      (C15) Name that are expilictly or implicitly declared as types should
            not appear in the program body.

5. PROJECT 2 REQUIREMENTS
=========================

The goal is to write a type checker. The type checker will enforce all the
constraints above and the constraints described in this section. In this 
section, I will just describe the constraints. Examples are given in Section 6.


We have the following main rules (MR) to be enforced:

    (MR1)  Structural equivalence is enforced
    (MR2)  Each variable (wether or not it is explicitly declared) has a 
           fixed type that does not change
    (MR3)  Each type name (wether or not it is explicitly declared) is fixed 
           and does not change 
    (MR4)  Each type is assigned an integer 
    (MR5)  INT is assigned 10, REAL is assigned 11, STRING is assigned 12, and 
           BOOLEAN is assigned 13. 
    (MR6)  If a : b; is a type declaration, then #a = #b, where #x is the 
           number assigned to x.
    (MR7)  Types that are implicitly declared are assigned unique numbers 
           when they are introduced. The numbers should be greater than 13.
    (MR8)  Each variable is assigned an integer (type number)
    (MR9)  If a : t; is a variable declaration, then #a = #t, where #x is 
           the number assigned to x.
    (MR10) Variables that are implicitly declared are assigned unique numbers 
           when they are introduced. The numbers should be greater than 13.

WHAT YOUR PROGRAM SHOULD DO?

  Analyze the input program and either
 
         1. Print out semantic error message if there is an error 
    or
         2. For every type and variable, print all the variables and types 
            with the same type number together. The detailed format is given
            below

If there is a semantic error, you should not print any variables or types.

As in all projects in this class, your program should read input from standard 
input, and write output to standard output.

OUTPUT
------

  Error codes:

     - type name declared more than once: ERROR CODE 0 
     - type redeclared as variable: ERROR CODE 1
     - variable declared more than once: ERROR CODE 2
     - type mismatch: ERROR CODE 3

  Non error output:

     - for every type name that is built-in, explicitly or implicitly declared,
       output, in the order they are introduced in the program, all type names 
       (built-in first, then explicit, then implicit) and variable names 
       (explicit or implicit) that have the same integer number. The format is

       typename_1 : typename_2 typename_3 ... typename_n variablename_1 
                    variablename_2  ... variablename_k #
	 
	 - For built-in types the order is according to their type numbers,
	   e.g. 10, 11,... So you would list INT before REAL in the output.

     - if a type name already appears in a list do not create a list for it

     - for every variable name that is implicitly declared, output, in the
       order they are introduced in the program all variable names that have 
       the same integer number. Do not create a list for an implicitly declared 
       variable if it already appears in an earlier list. The format is 

       variablename_1 : variablename_2 variablename_3 ... variablenam_l #

6. EXAMPLES
===========

1.
      { a = b }
   
      - type of b = 14
      - type of b = 15
   
   =>

      we change type of a to 15

Output

      a : b #

2.    VAR           
        a : INT;          // 1
        b : REAL;         // 2
      { a = b }           //3
  
      - type of a = 10
      - type of b = 11
   
   =>

      type mismatch and we stop the semantic checking

Output

     ERROR CODE 3

3.   {  
         while (a)
        {
           b = b+1;
        }
     }

      type of a = 14
      type of b = 15
  => 
      type of a = 13 (BOOLEAN)
      type of b = 10
  

Output

      INT : b #
      BOOLEAN : a # 
      
4.    

     {
       a = 1;
       b = a;
       if (b)
       {
          c = a;
       }
     }

      In this example

      a is INT (first statement)
      b is INT (from second statement)
      b is BOOLEAN (from the condition of the while)

    =>
       semantic error

Output

     ERROR CODE 3

5.   { a = b+c;
       b = 1;
       c = 1.5;
     }

     a, b, and c are the same type (first statement)
     b is INT (second statement) this also makes a and c INT
     type mismatch between INT and REAL (third statement)

    => 
      semantic error

Output

    ERROR CODE 3

6. Detailed example

   01: TYPE
   02:   at : INT;
   03:   bt : at;
   04:   ct : dt;
   05:   et : BOOLEAN;
   06:   ft : STRING;
   07:   gt : REAL;
   08:    
   09: VAR
   10:   av : at;
   11:   bv : bt;
   12:   cv : ct;
   13:   ev : et;
   14:   fv : ft;
   15:   gv : gt;
   16:   hv : t1;
   17:   iv : t1;
   18:   jv : t2
   19:   kv : t2;
   20:   lv : t3;
   21:
   22: {
   23:   av = 1;
   24:   bv = av + lv;
   25:   while jv < iv
   26:   {
   27:      av = av+1;
   28:   }
   29:
   30:   while jv;
   31:   {
   32:      av = av+1;
   33:   }
   34:
   35:   kv = kv+1;
   36:   bv = av;
   37:   cv = 1.0;
   38:   cv = bv;
   39:   mv = nv;
   40: }


    line 02: at : INT;
             #INT = 10              // (MR5)
             #at = 10               // (MR6)
    line 03: bt : at;
             #bt = #at = 10         // (MR6)
    line 04: ct : dt;
             #ct = 14               // implicitly declared type
             #ct = #dt => #dt = 14  // (MR6)
    line 05: et : BOOLEAN;       
             #BOOLEAN = 13          // (MR5)   
             #et = 13               // (MR6)
    line 06: ft : STRING;
             #STRING = 12           // (MR5)
             #ft = 12               // (MR6)
    line 07: gt : REAL;
             #REAL = 11             // (MR5)
             #gt = 11               // (MR6)
    line 10: av : at;
             #av = #at              // (MR9)
             #av = 10
    line 11: bv : bt;
             #bv = #bt              // (MR9)
             #bv = 10
    line 12: cv : ct;
             #cv = #ct              // (MR9)
             #cv = 14               // note that the
                                    // base type is unknown
    line 13: ev : et;      
             #ev = #et              // (MR9)
             #ev = 13               
    line 14: fv : ft;
             #fv = #ft              // (MR9)
             #fv = 12
    line 15: gv : gt; 
             #gv = #gt              // (MR9)
             #gv = 11
    line 16: hv : t1;
             #t1 = 15               // t1 implicitly declared (MR7)
             #hv = #t1 = 15         // (MR9)
    line 17: iv : t1;
             #iv = #t1              // (MR9)
             #iv = 15               // #t1 already set in line 16
    line 18: jv : t2          
             #jv = #t2 = 16         // similar to line 17 
    line 19: kv : t2;
             #kv = #t2
             #kv = 16               // #t2 already set in line 16
    line 20: lv : t3;
             #lv = #t3
             #lv = #t3 = 17         // similar to line 19
    line 23: av = 1;
             #1 = 10             // INT by (C9)
             #av = 10            // from line 10 above
             no mismatch         // no rules violated
    line 24: bv = av + lv;
             #av = 10
             #lv = 17            // from line 20 above
             #lv = 10            // by (C7)
             #bv = 10            // line 11
             no mismatch         // rule (C6) applies
    line 25: while jv < iv
             #jv = 16            // line 18 above
             #iv = 15            // line 17 above
             #jv = #iv = 15      // rule (C8)
             #t2 = 15            // change 16 to 15
             #kv = 15            // change 16 to 15
    line 27: av = av+1;
             #av = 10            // line 10
             #1 = 10             // rule (C9)
             rule (C6) applies
             no mismatch         // no rules violated
    line 30: while jv;
             #jv = 15            // line 25
             #jv = 13            // rule (C12)
             #t1 = 13            // change all 15's to 13's
             #t2 = 13            // for all types and
             #hv = 13            // variables
             #iv = 13            // 
             #kv = 13
    line 32: av = av+1;
             #av = 10            // line 10
             #1 = 10             // rule (C9)
             rule (C6) applies
             no mismatch         // no rules violated
    line 35: kv = kv+1;
             #kv = 13           // line 30
             #1  = 10           // rule 
             #kv = #1           // by rule (C7)
             semantic error     // type mismatch
    line 36: bv = av;
             #av = 10           // line 23
             #bv = 10           // line 11
             no mismatch        // rule (C6) applies
    line 37: cv = 1.0;
             #cv = 14           // line 12 above
             #1.0 = 11          // rule (C10)
             #cv = 11           // rule (C6)
    line 38: cv = bv;           
             #cv = 11           // line 37
             #bv = 10           // line 11
             #cv = #bv          // rule (C6) 
             semantic error     // type mismatch 
    line 39: mv = nv;
             #mv = 18           // (MR10)
             #nv = 19           // (MR10)
             #mv = 18           // (C6)

WHAT TO SUBMIT
==============

You should submit one zip file containing your program file(s).  All your
program files 

IMPORTANT: You should follow the file naming convention in your submission.
           Submissions that do not follow the naming conventions will be 
           penalized.


CHECKLIST
          - compile on general
          - test on general
          - make sure your program reads input from standard input, and writes 
		    output to standard output.
          - submit zip file according to naming convention
          - make sure you write your full name as a comment in every file
            you submit
           

       IMPORTANT: IT WILL TAKE AROUND 20 HOURS TO FINISH THIS PROJECT

----------------------------------------------------------------------------*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "symt.h"

//----------------------------- token types ------------------------------
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

//------------------- reserved words and token strings -----------------------
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
		"PRINT", 
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



/*----------------------------------------------------------------------------
SYNTAX ANALYSIS SECTION
----------------------------------------------------------------------------*/
#define PRIMARY 0
#define EXPR 1

/*-------------------------------UTILITIE---------------------------*/
void syntax_error(char* NT, int line_no)
{
	printf("Syntax error while parsing %s line %d\n", NT, line_no);
}

/*--------------------------------------------------------------------
  PRINTING PARSE TREE
---------------------------------------------------------------------*/
void print_parse_tree(struct programNode* program)
{	
	print_decl(program->decl); 
	print_body(program->body);
}

void print_decl(struct declNode* dec)
{
	if (dec->type_decl_section != NULL)
	{	
		print_type_decl_section(dec->type_decl_section);
	}
	if (dec->var_decl_section != NULL)
	{	
		print_var_decl_section(dec->var_decl_section);
	}
}

void print_body(struct bodyNode* body)
{
	printf("{\n");
	print_stmt_list(body->stmt_list); 
	printf("}\n");
	
}

void print_var_decl_section(struct var_decl_sectionNode* varDeclSection)
{
	printf("VAR\n");
	if (varDeclSection->var_decl_list != NULL)
		print_var_decl_list(varDeclSection->var_decl_list);
}

void print_var_decl_list(struct var_decl_listNode* varDeclList)
{
	print_var_decl(varDeclList->var_decl);
	if (varDeclList->var_decl_list != NULL)
		print_var_decl_list(varDeclList->var_decl_list);	
}

void print_var_decl(struct var_declNode* varDecl)
{	
	print_id_list(varDecl->id_list);
	printf(": ");
	print_type_name(varDecl->type_name);
	printf(";\n");
}

void print_type_decl_section(struct type_decl_sectionNode* typeDeclSection)
{
	printf("TYPE\n");
	if (typeDeclSection->type_decl_list != NULL)
		print_type_decl_list(typeDeclSection->type_decl_list);
}

void print_type_decl_list(struct type_decl_listNode* typeDeclList)
{
	print_type_decl(typeDeclList->type_decl);
	if (typeDeclList->type_decl_list != NULL)
		print_type_decl_list(typeDeclList->type_decl_list);	
}

void print_type_decl(struct type_declNode* typeDecl)
{	
	print_id_list(typeDecl->id_list);
	printf(": ");
	print_type_name(typeDecl->type_name);
	printf(";\n");
}

void print_type_name(struct type_nameNode* typeName)
{
	if (typeName->type != ID)
		printf("%s ", reserved[typeName->type]);
	else
		printf("%s ", typeName->id);
}

void print_id_list(struct id_listNode* idList)
{
	printf("%s ",idList->id);
	if (idList->id_list != NULL)
	{	printf(", ");
		print_id_list(idList->id_list);
	}
}

void print_stmt_list(struct stmt_listNode* stmt_list)
{
	print_stmt(stmt_list->stmt);
	if (stmt_list->stmt_list != NULL)
		print_stmt_list(stmt_list->stmt_list);

}

void print_assign_stmt(struct assign_stmtNode* assign_stmt)
{
	printf("%s ", assign_stmt->id);
	printf("= ");
	print_expression_prefix(assign_stmt->expr);
	printf("; \n");
}

void print_while_stmt(struct while_stmtNode* while_stmt)
{

}
void print_stmt(struct stmtNode* stmt)
{
	if (stmt->stmtType == ASSIGN)
		print_assign_stmt(stmt->assign_stmt);
}

void print_expression_prefix(struct exprNode* expr)
{
	if (expr->tag == EXPR)
	{
		printf("%s ", reserved[expr->operator]);
		print_expression_prefix(expr->leftOperand);
		print_expression_prefix(expr->rightOperand);
	} else
	if (expr->tag == PRIMARY)
	{
		if (expr->primary->tag == ID)
			printf("%s ", expr->primary->id);
		else
		if (expr->primary->tag == NUM)
			printf("%d ", expr->primary->ival);
		else
		if (expr->primary->tag == REALNUM)
			printf("%.4f ", expr->primary->fval);

	}
}


/*--------------------------------------------------------------------
  CREATING PARSE TREE NODE
---------------------------------------------------------------------*/
struct programNode* make_programNode()
{	
	return (struct programNode*) malloc(sizeof(struct programNode));
}

struct declNode* make_declNode()
{
	return (struct declNode*) malloc(sizeof(struct declNode));
}

struct type_decl_sectionNode* make_type_decl_sectionNode()
{
	return (struct type_decl_sectionNode*) malloc(sizeof(struct type_decl_sectionNode));
}

struct var_decl_sectionNode* make_var_decl_sectionNode()
{
	return (struct var_decl_sectionNode*) malloc(sizeof(struct var_decl_sectionNode));
}

struct var_declNode* make_var_declNode()
{
	return (struct var_declNode*) malloc(sizeof(struct var_declNode));
}

struct type_declNode* make_type_declNode()
{
	return (struct type_declNode*) malloc(sizeof(struct type_declNode));
}

struct type_decl_listNode* make_type_decl_listNode()
{
	return (struct type_decl_listNode*) malloc(sizeof(struct type_decl_listNode));
}

struct var_decl_listNode* make_var_decl_listNode()
{
	return (struct var_decl_listNode*) malloc(sizeof(struct var_decl_listNode));
}

struct id_listNode* make_id_listNode()
{
	return (struct id_listNode*) malloc(sizeof(struct id_listNode));
}

struct type_nameNode* make_type_nameNode()
{
	return (struct type_nameNode*) malloc(sizeof(struct type_nameNode));
}

struct bodyNode* make_bodyNode()
{
	return (struct bodyNode*) malloc(sizeof(struct bodyNode));
}

struct stmt_listNode* make_stmt_listNode()
{
	return (struct stmt_listNode*) malloc(sizeof(struct stmt_listNode));
}

struct stmtNode* make_stmtNode()
{
	return (struct stmtNode*) malloc(sizeof(struct stmtNode));
}

struct while_stmtNode* make_while_stmtNode()
{
	return (struct while_stmtNode*) malloc(sizeof(struct while_stmtNode));
}

struct assign_stmtNode* make_assign_stmtNode()
{
	return (struct assign_stmtNode*) malloc(sizeof(struct assign_stmtNode));
}

struct exprNode* make_exprNode()
{
	return (struct exprNode*) malloc(sizeof(struct exprNode));
}

struct primaryNode* make_primaryNode()
{
	return (struct primaryNode*) malloc(sizeof(struct primaryNode));
}

struct conditionNode* make_conditionNode()
{
	return (struct conditionNode*) malloc(sizeof(struct conditionNode));
}

/*--------------------------------------------------------------------*/

/*--------------------------------------------------------------------
  PARSING AND BUILDING PARSE TREE
---------------------------------------------------------------------*/
struct primaryNode* primary()/*primary can be found using factor()*/
{
	struct primaryNode* primar;
	
	return primar;
}

struct conditionNode* condition()
{/* using factor() instead of primary(). */
	struct conditionNode* con;
	con = make_conditionNode();
	con -> left_operand = factor();
	ttype = getToken();
	if(ttype <= GTEQ && ttype >= NOTEQUAL){
		con -> relop = ttype;
		con -> right_operand = factor();
		return con;
	}else if(ttype == LBRACE){
		ungetToken();
		con -> relop = NOOP;
		con -> right_operand = NULL;
		return con;
	}else{
		syntax_error("condition. RELOP expected", line_no);
		exit(0);
	}
	return NULL;
}

struct exprNode* factor()
{
	struct exprNode* facto;
	
	ttype = getToken();
	
	if (ttype == LPAREN)
	{	facto = expr();
		ttype = getToken();
		if (ttype == RPAREN)
			return facto;
		else 
		{	syntax_error("factor. RPAREN expected", line_no);
			exit(0);
		}
	} else
	if (ttype == NUM)
	{	facto = make_exprNode();
		facto->primary = make_primaryNode();
		facto->tag = PRIMARY;
		facto->operator = NOOP;
		facto->leftOperand = NULL;
		facto->rightOperand = NULL;
		facto->primary->tag = NUM;
		facto->primary->ival = atoi(token);
		return facto;
	} else
	if (ttype == REALNUM)
	{	facto = make_exprNode();
		facto->primary = make_primaryNode();
		facto->tag = PRIMARY;
		facto->operator = NOOP;
		facto->leftOperand = NULL;
		facto->rightOperand = NULL;
		facto->primary->tag = REALNUM;
		facto->primary->fval = atof(token);
		return facto;
	} else
	if (ttype == ID)
	{	facto = make_exprNode();
		facto->primary = make_primaryNode();
		facto->tag = PRIMARY;
		facto->operator = NOOP;
		facto->leftOperand = NULL;
		facto->rightOperand = NULL;
		facto->primary->tag = ID;
		facto->primary->id = (char *) malloc((tokenLength+1)*sizeof(char));
		strcpy(facto->primary->id,token);
		return facto;
	} else
	{	syntax_error("factor. NUM, REALNUM, or ID, expected", line_no);
		exit(0);
	}
}

struct exprNode*term()
{
	struct exprNode* ter;
	struct exprNode* f;

	
	ttype = getToken();
	if ((ttype == ID)|(ttype == LPAREN)|(ttype == NUM)|(ttype == REALNUM))
	{	ungetToken();
		f = factor();
		ttype = getToken();
		if ((ttype == MULT) | (ttype == DIV))
		{	ter = make_exprNode();
			ter->operator = ttype; 
			ter->leftOperand = f;
			ter->rightOperand = term();
			ter->tag = EXPR;
			return ter;
		} else	
		if ((ttype == SEMICOLON)|(ttype == PLUS)|(ttype == MINUS)|(ttype == RPAREN))
		{	ungetToken();
			return f;
		} else
		{	syntax_error("term. MULT or DIV expected", line_no);
			exit(0);
		}
	} else
	{	syntax_error("term. ID, LPAREN, NUM, or REALNUM expected", line_no);
		exit(0);
	}
}


struct exprNode* expr()
{
	struct exprNode* exp;
	struct exprNode* t;
	
	ttype = getToken();
	if ((ttype == ID)|(ttype == LPAREN)|(ttype == NUM)|(ttype == REALNUM))
	{	ungetToken();
		t = term();
		ttype = getToken();
		if ((ttype == PLUS) | (ttype == MINUS))
		{	exp = make_exprNode();
			exp->operator = ttype; 
			exp->leftOperand = t;
			exp->rightOperand = expr();			
			exp->tag = EXPR;
			return exp;
		} else	
		if ((ttype == SEMICOLON)|(ttype == MULT)|(ttype==DIV)|(ttype == RPAREN))
		{	ungetToken();
			return t;
		} else
		{	syntax_error("expr. PLUS, MINUS, or SEMICOLON expected", line_no);
			exit(0);
		}
	} else
	{	syntax_error("expr. ID, LPAREN, NUM, or REALNUM expected", line_no);
		exit(0);
	}
}

struct assign_stmtNode* assign_stmt()
{	struct assign_stmtNode* assignStmt;

	ttype = getToken();
	if (ttype == ID) 
	{	assignStmt = make_assign_stmtNode();
		assignStmt->id = (char *) malloc((tokenLength+1)*sizeof(char));
		strcpy(assignStmt->id,token);
		ttype = getToken();
		if (ttype == EQUAL)
		{	assignStmt->expr = expr();
			return assignStmt;
		} else
		{	syntax_error("assign_stmt. EQUAL expected", line_no);
			exit(0);
		}
	} else
	{
		syntax_error("assign_stmt. ID expected", line_no);
		exit(0);
	}
}

struct while_stmtNode* while_stmt()
{
	struct while_stmtNode* wstm;
	ttype = getToken();
	if(ttype == WHILE){
		wstm = make_while_stmtNode();
		wstm->condition = condition();
		ttype = getToken();
		if(ttype == LBRACE){
			ungetToken();
			wstm->body = body();
			return wstm;
		}else{
			syntax_error("while_stmt. LBRACE expected", line_no);
			exit(0);
		}
	}else{
		syntax_error("while_stmt. WHILE expected", line_no);
		exit(0);
	}
}

struct stmtNode* stmt()
{
	struct stmtNode* stm;
	ttype = getToken();
	stm = make_stmtNode();

	if (ttype == ID) // assign_stmt
	{	ungetToken();
		stm->assign_stmt = assign_stmt();
		stm->stmtType = ASSIGN;
		ttype = getToken();
		if (ttype == SEMICOLON)
		{	return stm;
		}
		else
		{	syntax_error("stmt. SEMICOLON expected", line_no);
			exit(0);
		}
	} else if (ttype == WHILE) // while_stmt
	{	ungetToken();
		stm->while_stmt = while_stmt();
		stm->stmtType = WHILE;	
		return stm;
	} else // syntax error
	{
		syntax_error("stmt. ID or WHILE expected", line_no);
		exit(0);
	}	
}

struct stmt_listNode* stmt_list()
{
	struct stmt_listNode* stmtList;

	ttype = getToken();

	if ((ttype == ID)||(ttype == WHILE))
	{	ungetToken();
		stmtList = make_stmt_listNode();
		stmtList->stmt = stmt();
		ttype = getToken();
		if (ttype == ID || (ttype == WHILE))
		{	ungetToken();
			stmtList->stmt_list = stmt_list();
			return stmtList;
		} else	// If the next token is not in FOLLOW(stmt_list), 
			// let the caller handle it. 
		{	ungetToken();
			return stmtList;
		}
	} else
	{
		syntax_error("stmt_list. ID or WHILE expected", line_no);
		exit(0);
	}
	
}

struct bodyNode* body()
{	struct bodyNode* bod;

	ttype = getToken();
	if (ttype == LBRACE)
	{	bod = make_bodyNode();
		bod->stmt_list = stmt_list();
		ttype = getToken();
		if (ttype == RBRACE)
			return bod;
		else
		{	syntax_error("body. RBRACE expected", line_no);
			exit(0); 
		}
	} else
	{	syntax_error("body. LBRACE expected", line_no);
		exit(0); 
	}
}

struct type_nameNode* type_name()
{
	struct type_nameNode* tName;
	tName = make_type_nameNode();

	ttype = getToken();
	if ((ttype == ID)|(ttype == INT)|(ttype==REAL)
		|(ttype == STRING)|(ttype==BOOLEAN))
	{	tName->type = ttype;
		if (ttype == ID)
		{	tName->id = (char *) malloc(tokenLength+1);
			strcpy(tName->id,token);	
		}
		return tName;
	} else
	{	syntax_error("type_name. type name expected", line_no);
		exit(0);
	}
}

struct id_listNode* id_list()
{
	struct id_listNode* idList;
	idList = make_id_listNode();
	ttype = getToken();
	if (ttype == ID)
	{	
		idList->id = (char*) malloc(tokenLength+1);
		strcpy(idList->id, token);
ttype = getToken();
		if (ttype == COMMA)
		{
			idList->id_list = id_list();
			return idList;
			
		} else
		if (ttype == COLON)
		{	ungetToken();
			idList->id_list = NULL;
			return idList;
		} else
		{	syntax_error("id_list. COMMA or COLON expected", line_no);
			exit(0);
		}
	} else
	{	syntax_error("id_list. ID expected", line_no);
		exit(0);
	}

}

struct type_declNode* type_decl()
{	
	struct type_declNode* typeDecl;
	typeDecl = make_type_declNode();
	ttype = getToken();
	if (ttype == ID)
	{	ungetToken();
		typeDecl->id_list = id_list();
		ttype = getToken();
		if (ttype == COLON)
		{	typeDecl->type_name = type_name();
			ttype = getToken();
			if (ttype == SEMICOLON)
			{	return typeDecl;
			}
			else
			{	syntax_error("type_decl. SEMICOLON expected", line_no);
				exit(0);
			}
		} 
	} else
	{
		syntax_error("type_decl. ID expected", line_no);
	}
}

struct var_declNode* var_decl()
{
	struct var_declNode* varDecl;
	varDecl = make_var_declNode();
	ttype = getToken();
	if (ttype == ID)
	{	ungetToken();
		varDecl->id_list = id_list();
		ttype = getToken();
		if (ttype == COLON)
		{	varDecl->type_name = type_name();
			ttype = getToken();
			if (ttype == SEMICOLON)
			{	return varDecl;
			}
			else
			{	syntax_error("var_decl. SEMICOLON expected", line_no);
				exit(0);
			}
		} 
	}
}	

struct var_decl_listNode* var_decl_list()
{
	struct var_decl_listNode* varDeclList;
	varDeclList = make_var_decl_listNode();

	ttype = getToken();
	if (ttype == ID)
	{	ungetToken();
		varDeclList->var_decl = var_decl();
		ttype = getToken();
		if (ttype == ID)
		{	ungetToken();
			varDeclList->var_decl_list = var_decl_list();
			return varDeclList;
		}  else	
		{	ungetToken();
			return varDeclList;
		} 
	} else
	{	syntax_error("var_decl_list. ID expected", line_no);
		exit(0);
	} 
}

struct type_decl_listNode* type_decl_list()
{
	struct type_decl_listNode* typeDeclList;
	typeDeclList = make_type_decl_listNode();

	ttype = getToken();
	if (ttype == ID)
	{	ungetToken();
		typeDeclList->type_decl = type_decl();
		ttype = getToken();
		if (ttype == ID)
		{	ungetToken();
			typeDeclList->type_decl_list = type_decl_list();
			return typeDeclList;
		}  else	
		{	ungetToken();
			return typeDeclList;
		} 
	} else
	{	syntax_error("type_decl_list. ID expected", line_no);
		exit(0);
	} 
}

struct var_decl_sectionNode* var_decl_section()
{
	struct var_decl_sectionNode *varDeclSection;
	varDeclSection = make_var_decl_sectionNode();

	ttype = getToken();
	if (ttype == VAR)
	{	// no need to ungetToken() 
		varDeclSection->var_decl_list = var_decl_list();  
		return varDeclSection;
	} else
	{	syntax_error("var_decl_section. VAR expected", line_no);
		exit(0);
	}
}

struct type_decl_sectionNode* type_decl_section()
{
	struct type_decl_sectionNode *typeDeclSection;
	typeDeclSection = make_type_decl_sectionNode();

	ttype = getToken();
	if (ttype == TYPE)
	{
		typeDeclSection->type_decl_list = type_decl_list();
		return typeDeclSection;            
	} else
	{	syntax_error("type_decl_section. TYPE expected", line_no);
		exit(0);
	}
}


struct declNode* decl()
{	
	struct declNode* dec;
	dec = make_declNode();

	ttype = getToken();
	if (ttype == TYPE)
	{	ungetToken();
		dec->type_decl_section = type_decl_section();
		ttype = getToken();
		if (ttype == VAR)
    		{	// type_decl_list is epsilon
			// or type_decl already parsed and the 
			// next token is checked
			ungetToken();
       			dec->var_decl_section = var_decl_section();
    		} else
		{	ungetToken();
			dec->var_decl_section = NULL;
		}
		return dec;
	} else
	{
		dec->type_decl_section = NULL; 
	    	if (ttype == VAR)
    		{	// type_decl_list is epsilon
			// or type_decl already parsed and the 
			// next token is checked
			ungetToken(); 
       			dec->var_decl_section = var_decl_section();
			return dec;
    		} else
		{	if (ttype == LBRACE)
			{	ungetToken();	
				dec->var_decl_section = NULL;
				return dec;
			} else
			{	syntax_error("decl. LBRACE expected", line_no);
				exit(0);		// stop after first syntax error
			}
		}
	}
}

struct programNode* program()
{	struct programNode* prog;

	prog = make_programNode();
	ttype = getToken();
	if ((ttype == TYPE) | (ttype == VAR) | (ttype == LBRACE))
	{	ungetToken();  
		prog->decl = decl();
		prog->body = body();
		return prog;
	} else
	{	syntax_error("program. TYPE or VAR or LBRACE expected", line_no);
		exit(0);		// stop after first syntax error
	}
}

void print_s_table(){
	int i,j,k,first;
	k=0;
	for(i=10;i<new_type;i++){
		first = TRUE;
		type_dec = TRUE;
		k = find_new_type(i);
	  if(symbol_count > 4){
		for(type_dec = TRUE; type_dec > -1; type_dec--){
			for(j=4; j<symbol_count; j++){
				if(s_table[j].typeNum == i && s_table[j].isType == type_dec){
					if(strcmp(s_table[j].name,s_table[k].name) != 0){
					if(first == TRUE){
						printf("%s : %s ",s_table[k].name, s_table[j].name);
						first = FALSE;
					}else{
						printf("%s ", s_table[j].name);
					}
					}
				}
			}
		}
		if(first == FALSE)
			printf("#\n");
		k++;
	  }
	 }
}

int find_new_type(int nt){
	int i = 0;
	for(i=0; i<symbol_count; i++){
		if(s_table[i].typeNum == nt)
			return i;
	}
}

void change_type(int type, int ntype){
	int i = 0;
	for(i=0; i<symbol_count; i++){
		if(s_table[i].typeNum == type)
			s_table[i].typeNum = ntype;
	}
}

// COMMON mistakes:
//    *     = instead of ==
//    *     not allocating space before strcpy
main()
{       struct programNode* parseTree;

	symt_init();
	parseTree = program();
	type_var(parseTree->decl);
	check_body(parseTree->body);
	print_s_table();
}

