//asst1.cpp
//Author: Dave Hubbard

#include <iostream>
#include <string>
#include <vector>
#include <fstream>
#include <cstdlib>
#include <algorithm>
using namespace std;

#define TERMS 34
	
string terminals[] = 
	{
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
		"PLUS",
		"MINUS",
		"UNDERSCORE", 
		"DIV", 
		"MULT", 
		"EQUAL", 
		"COLON", 
		"COMMA", 
		"SEMICOLON", 
		"LBRAC", 
		"RBRAC", 
		"LPAREN", 
		"RPAREN", 
		"NOTEQUAL", 
		"GREATER", 
		"LESS",
		"LTEQ",
		"GTEQ",
		"DOT",
        "ID",
        "NUM",
        "REALNUM"
	};
vector<string> nonterminal;				//Nonterminal symbols
string s;								//Input buffer
vector<string> l_rules;					//Left side rules
vector< vector<string> > r_rules;		//Right side rules
vector< vector<string> > first;			//FIRST sets
vector< vector<string> > follow;		//FOLLOW sets

void error_code(int i){
	cout << "ERROR CODE " << i << endl;
	exit(1);
}

void error_check(){
	bool match;
	// check if all nonterminals have rules
	for(int i = 0; i < nonterminal.size(); i++){
		match = false;
		for(int j = 0; j < l_rules.size();j++){
			if(nonterminal[i] == l_rules[j]){
				match = true;
			}
		}
		if (match == false){
			error_code(1);
		}
	}
	//check for stray rules with no defined nonterminal
	for(int i = 0; i < l_rules.size(); i++){
		match = false;
		for(int j = 0; j < nonterminal.size();j++){
			if(l_rules[i] == nonterminal[j]){
				match = true;
			}
		}
		if (match == false){
			error_code(2);
		}
	}
}

bool is_terminal(string s){
	for(int i = 0; i < TERMS; i++){
		if(s == terminals[i])
			return true;
		}
	return false;
}	 

bool has_epsilon(vector<string>& v){
	for(int i = 0; i < v.size(); i++){
		if(v[i] == "#"){
			return true;
		}
	}
	return false;
}

int match_element(string s){
	for(int k=0; k < first.size(); k++){	//find element
		if(s == first[k][0]){
			return k;
		}
	}
	return 0;
}

int add_terminal_FIRST(int current, string terminal){
	
	if(first[current].size() > 1){
		for(int i = 1; i < first[current].size(); i++){
			if(terminal == first[current][i]){
				return 0;
			}
		}
		first[current].push_back(terminal);
		return 1;
	}else{
		first[current].push_back(terminal);
		return 1;
	}
}

int t_FOLLOW(vector<string>& v, string terminal){
	
	if(terminal != "#"){
		if(v.size() > 1){
			for(int i = 1; i < v.size(); i++){
				if(terminal == v[i]){
					return 0;
				}
			}
			v.push_back(terminal);
			return 1;
		}else{
			v.push_back(terminal);
			return 1;
		}
	}
	return 0;
}

int add_F(vector<string>& v, vector<string>& w){	//set w into set v
	
	bool add;
	int elem_added = 0;
	if(v.size() > 1){
		for(int i = 1; i < w.size(); i++){
			add = true;
			for(int j = 1; j < v.size(); j++){
				if( w[i] == v[j]){
					add = false;
				}
			}
			if(add == true && w[i] != "#"){
				v.push_back(w[i]);
				elem_added = 1;
			}
		}		
	}else{
		for(int i = 1; i < w.size(); i++){
			if(w[i] != "#"){
				v.push_back(w[i]);
				elem_added = 1;
			}
		}
	}
	return elem_added;	
}		

void calc_FIRST(){
	int i,j,k,n,current,other,changed=1;
	bool endloop;
	while(changed > 0){						//checks changes in FIRST sets
		changed = 0;

		for(i = 0; i < l_rules.size(); i++){	//traverse l_rules
			endloop = false;
			for(k=0; k < first.size(); k++){	//find FIRST element
				if(l_rules[i] == first[k][0]){
					current = k;
				}
			}
			n = r_rules[i].size();
			j = 0;
			while(endloop == false){

				if (r_rules[i][0] == "#"){		//check for epsilon
					if(!has_epsilon(first[current])){
						changed += add_terminal_FIRST(current, "#");
						}
						endloop = true;
				}
				
				if(endloop == false){	
					if(is_terminal(r_rules[i][j])){
						changed += add_terminal_FIRST(current,r_rules[i][j]);
						endloop = true;
					}else{
						
						for(k=0; k < first.size(); k++){
							if(r_rules[i][j] == first[k][0]){
								other = k;
							}
						}
						
						changed += add_F(first[current], first[other]);
						endloop = true;
						
						if(has_epsilon(first[other])){
							if(j == n-1){
								changed += add_terminal_FIRST(current, "#");
							}else{
								endloop = false;
								j++;
							}	
						}
					}
				}
			}//end inner while
		}// end outer for
	}// end while(change > 0)
}//end calc_FIRST
						
void calc_FOLLOW(){
	int i,j,k,n,current,from,to,changed=1;
	bool endloop;
	follow[0].push_back("$");				//FOLLOW rule I
	while(changed > 0){						//checks changes in FOLLOW sets
		changed = 0;

		for(i = 0; i < l_rules.size(); i++){	//traverse l_rules
			endloop = false;
			for(k=0; k < follow.size(); k++){	//find FOLLOW element
				if(l_rules[i] == follow[k][0]){
					current = k;
				}
			}
			n = r_rules[i].size();
			j = n - 1;
			do{									//rules II and III
				if(is_terminal(r_rules[i][j])|| r_rules[i][j] == "#"){
					endloop = true;
				}else{
					to = match_element(r_rules[i][j]);
					changed += add_F(follow[to],follow[current]);
					if(has_epsilon(first[to]) && j != 0){
						j--;
					}else{
						endloop = true;
					}
				}	
			}while(endloop == false);
			endloop = false;

			for(j=0; j<n; j++){				//rules IV and V
			  k = 1;
			  endloop = false;
			  while(endloop == false && r_rules[i][j] != "#"){
				int num = j+k;
				if(num < n){
					if(!is_terminal(r_rules[i][j])){
					 if(!is_terminal(r_rules[i][num])){
						to = match_element(r_rules[i][j]);
						from = match_element(r_rules[i][num]);
						changed += add_F(follow[to], first[from]);
						endloop = true;
						if(has_epsilon(first[from])){
							endloop = false;	
						}
					  }else{
					  	to = match_element(r_rules[i][j]);
						changed += t_FOLLOW(follow[to],r_rules[i][num]);
						endloop = true;
					  }
					}
				}else{
					endloop = true;
				}
				k++;
			  }
			}
		}
	}//end outer while
}// end calc_FOLLOW

void print_SET(vector<vector<string> > v, string s){ //s = FIRST or FOLLOW
	for(int i = 0; i < v.size(); i++){
		int k = v[i].size();
		std::sort(v[i].begin()+1,v[i].end());
		for(int j = 0; j < k; j++){
			if(j == 0){
				cout << s <<"(" << v[i][j] << ") = { ";
			}
			if(j < (k - 1) && j != 0){
				cout << v[i][j] << ", ";
			}
			if(j == (k-1)){
				cout << v[i][j] << " }\n";
			}
		}
	}
}

int main(int argc, char *argv[]){
	
	if (argc != 2){
		exit(1);
	}
	
	int i = 0, j = 0, k = 0;
	bool rule = false;
	ifstream is;
	is.open(argv[1]); 					//input format
	
	if(is.is_open()){
		do{
			is >> s;
			if(s != "#"){
				if(is_terminal(s)){
					error_code(0);
				}
				nonterminal.push_back(s);
				vector<string> temp;
				temp.push_back(s);
				first.push_back(temp);
				follow.push_back(temp);
			}
		}while(!is.eof() && s != "#");	
	
		while(!is.eof() && s != "##"){
			if(s == "#"){				//left side creation
				is >> s;
				if(s == "#" || s == "->")
					error_code(0);
				if(!is.eof() && s != "##"){
					if(!is_terminal(s)){
					l_rules.push_back(s);
					is >> s;
					}else{
					error_code(3);
					}
				}
			}
			
			if(s == "->"){				//right side creation
				vector<string> v;
				is >> s;
			
				if(s == "#"){
					v.push_back("#");
				}
				if(s == "##"){
					v.push_back("#");
				}
				
				while(s != "#" && s != "##"){
					v.push_back(s);
					is >> s;
				}
	
				r_rules.push_back(v);
			}
		
		}
	}
	/*for(k = 0; k < l_rules.size();k++){
		cout << l_rules.at(k) << " -> ";
		
		for(j =0; j< r_rules[k].size(); j++){	//prints grammar rules
			cout << r_rules[k][j] << " ";
		}
		
		cout << "\n";
	}*/
	error_check();
	calc_FIRST();
	calc_FOLLOW();
	print_SET(first, "FIRST");
	print_SET(follow, "FOLLOW");

	return 0;
}
