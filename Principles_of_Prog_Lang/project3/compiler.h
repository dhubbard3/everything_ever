#include <stdio.h>
#include <stdlib.h>
#include <string.h>


typedef struct varNode {
	int value;
	char* id;
} vNode;

typedef struct assignmentNode {
	struct varNode* lvalue;
	struct varNode* op1;
	struct varNode* op2;
	int operator;
} aNode;

typedef struct statementNode{
	int sType;
	struct assignmentNode* assign_st;
	struct printNode* print_st;
	struct ifNode* if_st;
	struct whileNode* while_st;
	struct gotoNode* goto_st;
	struct statementNode* next;
} sNode; 

typedef struct conditionNode {
	int operator;
	struct varNode* op1;
	struct varNode* op2;
	struct statementNode* trueBranch;
	struct statementNode* falseBranch;
} cNode;

typedef struct ifNode {
	struct conditionNode* condition;
	struct statementNode* stmt;
} iNode;

typedef struct whileNode {
	struct conditionNode* condition;
	struct statementNode* stmt;
} wNode;

typedef struct printNode {
	struct varNode* id;
} pNode;

typedef struct gotoNode {
	struct statementNode* target;
} gNode;

vNode* make_vNode()
{	
	return (vNode*) malloc(sizeof(vNode));
}

sNode* make_sNode()
{	
	return (sNode*) malloc(sizeof(sNode));
}

aNode* make_aNode()
{	
	return (aNode*) malloc(sizeof(aNode));
}

pNode* make_pNode()
{	
	return (pNode*) malloc(sizeof(pNode));
}

wNode* make_wNode()
{	
	return (wNode*) malloc(sizeof(wNode));
}

iNode* make_iNode()
{	
	return (iNode*) malloc(sizeof(iNode));
}

cNode* make_cNode()
{	
	return (cNode*) malloc(sizeof(cNode));
}

gNode* make_gNode()
{	
	return (gNode*) malloc(sizeof(gNode));
}

sNode* create_program_body();
