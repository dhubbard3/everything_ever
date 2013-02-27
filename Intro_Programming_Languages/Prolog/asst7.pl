% CSE240 Assignment 7
% Predicates in Prolog
% Name: Dave Hubbard

% factorial/2
% factorial(N, FN) is true if FN is N!
% ex. factorial(5, 120).

factorial(0, 1).
factorial(X, Y) :- X > 0,
		   X1 is X-1,
		   factorial(X1, Z),
		   Y is X * Z.



% stutter/2
% stutter(List, StutList) is true if StutList is a stuttered version of List.
% ex. stutter([h, e, l, l, o], [h, h, e, e, l, l, o, o]).

stutter([],[]).
stutter([H|T], StList) :- stutter(T, T2),
			  append([H],[H],Newlist),
			  append(Newlist,T2, StList).
			

% flatten/2 
% flatten(List, FlatList) is true if FlatList is a list of the elements in List. 
% ex. flatten([[a, b], c, [[d], 1], [[[e, 2]]]], [a, b, c, d, 1, e, 2]). 
% Note: you might need the predicate is_list/1 to help you. 
% Recall that \+ is negation. 

flatten([],[]).
flatten(List, FlatList) :- List = [H|T],
			   is_list(H),
			   
			   flatten(T , X),
			   append(H, X, FlatList).
			
flatten(List, FlatList) :- List = [H|T],
			   \+ is_list(H),
			   flatten(T , X),
			   append([H], X, FlatList).

% myPrefix/2
% myPrefix(Prefix, List) is true if Prefix is a prefix of List.
% ex. myPrefix([h, e, l], [h, e, l, l, o]).

myPrefix([],_).
myPrefix(Prefix, List) :- 
			  List = [H|T],
			  Prefix = [H2|T2],
			  H = H2,
			  myPrefix(T2,T),
			  append([H],T2,Prefix).
			 

% pal/2
% pal(Word, PalindromeWord) is true if PalindromeWord is a list that is
% Word appended with the reverse of Word.

pal(X, Result) :- reverse(P, X), 
		append(X,P,Result).
		