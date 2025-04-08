package rs.ac.bg.etf.pp1;

import java_cup.runtime.Symbol;

%%

%{

	// ukljucivanje informacije o poziciji tokena
	private Symbol new_symbol(int type) {
		return new Symbol(type, yyline+1, yycolumn);
	}
	
	// ukljucivanje informacije o poziciji tokena
	private Symbol new_symbol(int type, Object value) {
		return new Symbol(type, yyline+1, yycolumn, value);
	}

%}

%cup
%line
%column

%xstate COMMENT

%eofval{
	return new_symbol(sym.EOF);
%eofval}

%%

" " 	{ }
"\b" 	{ }
"\t" 	{ }
"\r\n" 	{ }
"\f" 	{ }


"program"   { return new_symbol(sym.PROG, yytext()); }
"break"		{ return new_symbol(sym.BREAK, yytext()); }
"class"		{ return new_symbol(sym.CLASS, yytext()); }
"else"		{ return new_symbol(sym.ELSE, yytext()); }
"const"		{ return new_symbol(sym.CONST, yytext()); }
"if"		{ return new_symbol(sym.IF, yytext()); }
"new"		{ return new_symbol(sym.NEW, yytext()); }
"print"		{ return new_symbol(sym.PRINT, yytext()); }
"read"		{ return new_symbol(sym.READ, yytext()); }
"return"	{ return new_symbol(sym.RET, yytext()); }
"void"		{ return new_symbol(sym.VOID, yytext()); }
"extends"	{ return new_symbol(sym.EXT, yytext()); }
"continue"	{ return new_symbol(sym.CONT, yytext()); }
"for"		{ return new_symbol(sym.FOR, yytext()); }
"static"	{ return new_symbol(sym.STAT, yytext()); }
"namespace"	{ return new_symbol(sym.NSPACE, yytext()); }


"+" 		{ return new_symbol(sym.PLUS, yytext()); }
"-" 		{ return new_symbol(sym.MINUS, yytext()); }
"*" 		{ return new_symbol(sym.MUL, yytext()); }
"/" 		{ return new_symbol(sym.DIV, yytext()); }
"%" 		{ return new_symbol(sym.MOD, yytext()); }
"==" 		{ return new_symbol(sym.EQCOMP, yytext()); }
"!="		{ return new_symbol(sym.NEQ, yytext()); }
">" 		{ return new_symbol(sym.GT, yytext()); }
">=" 		{ return new_symbol(sym.GEQ, yytext()); }
"<" 		{ return new_symbol(sym.LT, yytext()); }
"<=" 		{ return new_symbol(sym.LEQ, yytext()); }
"&&" 		{ return new_symbol(sym.BAND, yytext()); }
"||" 		{ return new_symbol(sym.BOR, yytext()); }
"="			{ return new_symbol(sym.EQ, yytext()); }
"++" 		{ return new_symbol(sym.INC, yytext()); }
"--" 		{ return new_symbol(sym.DEC, yytext()); }
";" 		{ return new_symbol(sym.SEMI, yytext()); }
":" 		{ return new_symbol(sym.COLON, yytext()); }
"," 		{ return new_symbol(sym.COMMA, yytext()); }
"." 		{ return new_symbol(sym.DOT, yytext()); }
"(" 		{ return new_symbol(sym.LPAREN, yytext()); }
")"			{ return new_symbol(sym.RPAREN, yytext()); }
"[" 		{ return new_symbol(sym.LBOX, yytext()); }
"]" 		{ return new_symbol(sym.RBOX, yytext()); }
"{" 		{ return new_symbol(sym.LBRACE, yytext()); }
"}" 		{ return new_symbol(sym.RBRACE, yytext()); }
"=>" 		{ return new_symbol(sym.LAMBDA, yytext()); }


<YYINITIAL> "//"	{ yybegin(COMMENT); }
<COMMENT> "\r\n"	{ yybegin(YYINITIAL); }
<COMMENT> .			{ yybegin(COMMENT); }


"true"|"false"			{ return new_symbol(sym.BOOL, Boolean.parseBoolean(yytext())); }
[0-9]+  				{ return new_symbol(sym.NUMBER, new Integer (yytext())); }
'[\ -~]'				{ return new_symbol(sym.CHAR, yytext().charAt(1)); }
[a-zA-Z][a-zA-Z0-9_]*	{ return new_symbol (sym.IDENT, yytext()); }



. { System.err.println("Leksicka greska ("+yytext()+") u liniji "+(yyline+1)); }






