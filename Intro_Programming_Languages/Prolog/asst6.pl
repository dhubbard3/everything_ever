% CSE240 Assignment 6
% Family Tree Relationships in Prolog
% Name: Dave Hubbard

% Facts about a few women in a family.
female(jean).
female(faith).
female(bonnie).
female(cathy).
female(renee).
female(julie).
female(michelle).
female(laura).
female(kelly).
female(mary).
female(jeanne).

% Facts about a few men in a family.
male(melvin).
male(ralph).
male(dave).
male(james).
male(ben).
male(robert).
male(brian).
male(kevin).
male(michael).
male(ron).
male(mark).

% Facts about people who are married.
spouse(jean, melvin).
spouse(ron, bonnie).
spouse(dave, mary).
spouse(james, jeanne).
spouse(faith, michael).
spouse(cathy, mark).

% Facts about which people are other people's parents.
parent(jean, ralph).
parent(jean, dave).
parent(jean, james).
parent(jean, ben).
parent(jean, bonnie).
parent(jean, faith).
parent(jean, cathy).
parent(melvin, ralph).
parent(melvin, dave).
parent(melvin, james).
parent(melvin, ben).
parent(melvin, bonnie).
parent(melvin, faith).
parent(melvin, cathy).
parent(bonnie, robert).
parent(bonnie, renee).
parent(ron, robert).
parent(ron, renee).
parent(dave, julie).
parent(dave, michelle).
parent(mary, julie).
parent(mary, michelle).
parent(james, laura).
parent(james, brian).
parent(jeanne, laura).
parent(jeanne, brian).
parent(faith, kevin).
parent(faith, kelly).
parent(michael, kevin).
parent(michael, kelly).

% Some predicates we wrote in lecture:

% A person is a husband if they are someone's spouse and male.
husband(X) :- spouse(_, X), male(X).
husband(X) :- spouse(X, _), male(X).

% A person is a wife if they are someone's spouse and female.
wife(X) :- spouse(_, X), female(X).
wife(X) :- spouse(X, _), female(X).

% X is Y's mother if X is female and they are Y's parent.
mother(X, Y) :- female(X), parent(X, Y).

% X is Y's father if X is male and they are Y's parent.
father(X, Y) :- male(X), parent(X, Y).

% Two people are siblings, if and only if they share mother and
% father.  Although this definition isn't totally true to the real
% world, it makes our predicate easy to implement without duplicates.
sibling(S1, S2) :- 
	mother(M, S1), mother(M, S2), 
	S1 \== S2, % someone is not their own sibling
	father(F, S1), father(F, S2).

%% Here you should add your predicates for assignment 6.

% Two people are married if X is the spouse of Y or Y is the spouse 
% of X.
married(X,Y):- spouse(X,Y).
married(X,Y):- spouse(Y,X).

% A grandparent is the parent of a person's parent.
grandmother(X, Z) :- female(X), parent(X, Y), parent(Y, Z).
grandfather(X, Z) :- male(X), parent(X, Y), parent(Y, Z). 

% Uncles are the male siblings of a person's parent, or married to 
% their parent's sibling.
uncle(X, Z) :- male(X), sibling(X, Y), parent(Y, Z).
uncle(W, Z) :- male(W), married(X, W), sibling(X, Y), parent(Y, Z).

% Cousins are the children of a parent's siblings.
cousin(W, Z) :- parent(X, W), sibling(X, Y), parent(Y, Z).