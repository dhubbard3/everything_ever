%Fluents
% ontable(X) -- meaning X is on table
% on(X,Y) -- meaning X is on top of Y.
% Derived fluent
% clear(X) -- meaning that there is nothing on top of X.

% Actions
%  move(X,Y)  - move block X ontop of block Y.
% mtable(X)  -- move block X to the table.

% mtable(b). mtable(d). move(c,b). move(a,c). move(d,a).

% For each action
% when is it executable
% What is its impact

% initially F --- this means a fluent F is initialy true

% A planning problem will give:
% i. the list of fluents  that can describe various states of the world
% ii. the list of actions
% iii. description of when the actions are executable and what its impact are
% iv. description of an initial state
% v. description of the goal

block(a;b;c;d).
time(0..5).

fluent(ontable(X)) :- block(X).
fluent(on(X,Y)) :- block(X), block(Y), X!=Y.
fluent(clear(X)) :- block(X).

action(mtable(X)) :- block(X).
action(move(X,Y)) :- block(X), block(Y), X!=Y.

% executable move(X,Y)  if clear(X) and clear(Y) and X != Y.
exec(move(X,Y), T) :- holds(clear(X),T), holds(clear(Y),T), X != Y, time(T), block(X), block(Y).

% move(X,Y) causes ~clear(Y), on(X,Y).
nholds(clear(Y), T+1) :- occurs(move(X,Y),T), block(X), block(Y), time(T).
holds(on(X,Y), T+1) :- occurs(move(X,Y),T), block(X), block(Y), time(T).

% move(X,Y) causes clear(Z), ~on(X,Z) if on(X,Z).
holds(clear(Z), T+1) :- occurs(move(X,Y),T), block(X), block(Y), block(Z), holds(on(X,Z),T), time(T), Y!=Z.
nholds(on(X,Z), T+1) :- occurs(move(X,Y),T), block(X), block(Y), block(Z), holds(on(X,Z),T), time(T), Y!=Z.

% executable mtable(X) if clear(X).
exec(mtable(X),T) :- holds(clear(X), T), block(X), time(T).

% mtable(X) causes ontable(X).
holds(ontable(X), T+1) :- occurs(mtable(X),T), block(X), time(T).

% mtable(X) causes clear(Y), ~on(X,Y) if on(X,Y).
holds(clear(Y), T+1) :- occurs(mtable(X),T),  holds(on(X,Y),T), block(Y), block(X), time(T).
nholds(on(X,Y), T+1) :- occurs(mtable(X),T),  holds(on(X,Y),T), block(Y), block(X), time(T).

initially(ontable(a)).
initially(ontable(c)).
initially(on(b,a)).
initially(on(d,c)).
initially(clear(b)).
initially(clear(d))

holds(F,0) :- fluent(F), initially(F).
nholds(F,0) :- fluent(F), not holds(F,0).

occurs(move(b,d),0).

%Goal:   ontable(b), on(c,b), on(a,c), on(d,a).

hide initially(X).
hide block(X).
hide time(X).
hide action(X).
hide fluent(X).
hide nholds(X,Y).
