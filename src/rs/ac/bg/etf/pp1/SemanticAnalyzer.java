package rs.ac.bg.etf.pp1;

import org.apache.log4j.Logger;

import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.symboltable.*;
import rs.etf.pp1.symboltable.concepts.*;

public class SemanticAnalyzer extends VisitorAdaptor {
	Logger log = Logger.getLogger(getClass());
	
	boolean errorDetected = false;
	
	int constants = 0;
	int locals = 0;
	int globals = 0;
	
	Struct lastType = TabExtended.noType;
	Struct methodReturnType = null;
	int nvars = 0;
	
	private static String dump(Obj o) {
		StringBuilder output = new StringBuilder();
		
		switch (o.getKind()) {
		case Obj.Con:  output.append("Con "); break;
		case Obj.Var:  output.append("Var "); break;
		case Obj.Type: output.append("Type "); break;
		case Obj.Meth: output.append("Meth "); break;
		case Obj.Fld:  output.append("Fld "); break;
		case Obj.Prog: output.append("Prog "); break;
		case Obj.Elem: output.append("Namespace "); break;
		}
		
		output.append(o.getName());
		output.append(": ");
		
		output.append(nameOfStruct(o.getType()));
		
		output.append(", ");
		output.append(o.getAdr());
		output.append(", ");
		output.append(o.getLevel() + " ");
		
		return output.toString();
	}
	
	//None, Int, Char, Array, Class, Bool, Enum, Interface
	private static String nameOfStruct(Struct s) {
		String types[] = {"notype", "int", "char", "Arr of ", "class", "bool", "namespace", "interface"};
		if (s.getKind() == 3) return types[3] + nameOfStruct(s.getElemType());
		return (s.getKind()<=7 && s.getKind()>=0)?types[s.getKind()]:"Out of bounds!";
	}
	
	public void report_error(String message, SyntaxNode info) {
		errorDetected = true;
		StringBuilder msg = new StringBuilder(message);
		int line = (info == null)?0:info.getLine();
		if (line != 0) msg.append(" na liniji ").append(line);
		log.error(msg.toString());
	}
	
	public void report_info(String message, SyntaxNode info) {
		StringBuilder msg = new StringBuilder(message);
		int line = (info == null)?0:info.getLine();
		if (line != 0) msg.append(" na liniji ").append(line);
		log.info(msg.toString());
	}
	
	public void visit(ProgName progName) {
		progName.obj = TabExtended.insert(Obj.Prog, progName.getName(), TabExtended.noType);
		TabExtended.openScope();
	}
	
	public void visit(Program program) {
		nvars += TabExtended.currentScope.getnVars();
		TabExtended.chainLocalSymbols(program.getProgName().obj);
		TabExtended.closeScope();
	}
	
	public void visit(NamespaceName name) {
		name.obj = TabExtended.insert(Obj.Elem, name.getNamespaceName(), TabExtended.namespaceType);
		TabExtended.currentScope = new Scope(TabExtended.currentScope);
	}
	
	public void visit(Namespace n) {
		n.getNamespaceName().obj.setLevel(TabExtended.currentScope.getnVars());
		TabExtended.chainLocalSymbols(n.getNamespaceName().obj);
		nvars += TabExtended.currentScope.getnVars();
		TabExtended.currentScope = TabExtended.currentScope.getOuter();
	}
	
	public void visit(TypeIdent type) {
		Obj typeNode = TabExtended.find(type.getTypeName());
		if (typeNode == TabExtended.noObj) {
			report_error("Nije pronadjen tip " + type.getTypeName() + " u tabeli simbola!", type);
			type.struct = TabExtended.noType;
		} else {
			if (typeNode.getKind() == Obj.Type) {
				type.struct = typeNode.getType();
			} else {
				report_error("Ime " + type.getTypeName() + " ne predstavlja tip!", type);
				type.struct = TabExtended.noType;
			}
		}
		lastType = type.struct;
	}
	
	public void visit(TypeNamespace type) {
		Obj typeNode = TabExtended.find(type.getNamespaceName() + "::" + type.getTypeName());
		if (typeNode == null) {
			report_error("Nije pronadjen prostor imena " + type.getNamespaceName() + " u tabeli simbola!", type);
			type.struct = TabExtended.noType;
		} else if (typeNode == TabExtended.noObj) {
			report_error("Prostor imena " + type.getNamespaceName() + " ne sadrzi tip " + type.getTypeName(), type);
			type.struct = TabExtended.noType;
		} else if (typeNode.getKind() != Obj.Type) {
					report_error("Ime " + type.getNamespaceName() + "::" + type.getTypeName() + " ne predstavlja tip!", type);
					type.struct = TabExtended.noType;
		}
		
		lastType = type.struct;
	}
	
	public void visit(VarDeclSubunitIdent v) {
		if (TabExtended.currentScope.findSymbol(v.getVarName()) != null) {
			report_error("Redeklaracija simbola " + v.getVarName(), v);
			return;
		}
		Obj o = TabExtended.insert(Obj.Var, v.getVarName(), lastType);
		log.info("Deklarisana " + (methodReturnType!=null?"lokalna":"globalna") + " promenljiva, linija: " + v.getLine() + ", " + dump(o));
		if (o.getLevel() == 0) { globals++; o.setAdr(o.getAdr() + nvars);}
		else locals++;
	}
	
	public void visit(VarDeclSubunitArray v) {
		if (TabExtended.currentScope.findSymbol(v.getVarName()) != null) {
			report_error("Redeklaracija simbola " + v.getVarName(), v);
			return;
		}
		Obj o = TabExtended.insert(Obj.Var, v.getVarName(), new Struct(Struct.Array, lastType));
		log.info("Deklarisana " + (methodReturnType!=null?"lokalna":"globalna") + " promenljiva, linija: " + v.getLine() + ", " + dump(o));
		if (o.getLevel() == 0) { globals++; o.setAdr(o.getAdr() + nvars);}
		else locals++;
	}
	
	public void visit(ConstDeclSubunitInt c) {
		Obj o;
		if (TabExtended.currentScope.findSymbol(c.getConstName()) != null) {
			report_error("Redeklaracija simbola " + c.getConstName(), c);
			return;
		}
		if (lastType != TabExtended.intType) {
			report_error("Dodela int vrednosti konstanti tipa " + nameOfStruct(lastType) + "!", c);
			o = new Obj(Obj.Con, c.getConstName(), lastType, 0, 0);
		} else {
			o = new Obj(Obj.Con, c.getConstName(), lastType, c.getValue(), 0);
		}
		TabExtended.currentScope.addToLocals(o);
		log.info("Deklarisana simbolicka konstanta, linija: " + c.getLine() + ", " + dump(o));
		constants++;
	}
	
	public void visit(ConstDeclSubunitChar c) {
		Obj o;
		if (TabExtended.currentScope.findSymbol(c.getConstName()) != null) {
			report_error("Redeklaracija simbola " + c.getConstName(), c);
			return;
		}
		if (lastType != TabExtended.charType) {
			report_error("Dodela char vrednosti konstanti tipa " + nameOfStruct(lastType) + "!", c);
			o = new Obj(Obj.Con, c.getConstName(), lastType, 0, 0);
		} else {
			o = new Obj(Obj.Con, c.getConstName(), lastType, c.getValue(), 0);
		}
		TabExtended.currentScope.addToLocals(o);
		log.info("Deklarisana simbolicka konstanta, linija: " + c.getLine() + ", " + dump(o));
		constants++;
	}

	public void visit(ConstDeclSubunitBool c) {
		Obj o;
		if (TabExtended.currentScope.findSymbol(c.getConstName()) != null) {
			report_error("Redeklaracija simbola " + c.getConstName(), c);
			return;
		}
		if (lastType != TabExtended.boolType) {
			report_error("Dodela bool vrednosti konstanti tipa " + nameOfStruct(lastType) + "!", c);
			o = new Obj(Obj.Con, c.getConstName(), lastType, 0, 0);
		} else {
			o = new Obj(Obj.Con, c.getConstName(), lastType, c.getValue()==true?1:0, 0);
		}
		TabExtended.currentScope.addToLocals(o);
		log.info("Deklarisana simbolicka konstanta, linija: " + c.getLine() + ", " + dump(o));
		constants++;
	}
	
	public void visit(RetTypeType type) {
		type.struct = type.getType().struct;
	}
	
	public void visit(RetTypeVoid type) {
		type.struct = TabExtended.noType;
	}
	
	public void visit(MethodTypeName m) {
		m.obj = TabExtended.insert(Obj.Meth, m.getName(), m.getRetType().struct);
		methodReturnType = m.getRetType().struct;
		TabExtended.openScope();
	}
	
	public void visit(MethodDecl methodDecl) {
		TabExtended.chainLocalSymbols(methodDecl.getMethodTypeName().obj);
		methodReturnType = null;
		TabExtended.closeScope();
	}
	
	public void visit(FormParamOne paramOne) {
		TabExtended.insert(Obj.Var, paramOne.getParamName(), paramOne.getType().struct);
	}
	
	public void visit(FormParamArray paramArray) {
		TabExtended.insert(Obj.Var, paramArray.getParamName(), new Struct(Struct.Array, paramArray.getType().struct));
	}
	
	public void visit(DesignatorWithNamespace des) {
		StringBuilder s = new StringBuilder("Pretraga na " + des.getLine() + "(" + des.getNamespaceName() + "::" + des.getDesName() + ")");
		
		Obj objNode = TabExtended.find(des.getNamespaceName() + "::" + des.getDesName());
		if (objNode == null) {
			report_error("Nije pronadjen prostor imena " + des.getNamespaceName() + " u tabeli simbola!", des);
			des.obj = TabExtended.noObj;
			s.append(", nije nadjeno!");
		} else if (objNode == TabExtended.noObj) {
			report_error("Prostor imena " + des.getNamespaceName() + " nema deklarisan simbol " + des.getDesName() + "!", des);
			des.obj = TabExtended.noObj;
			s.append(", nije nadjeno!");
		} else if (objNode.getKind() != Obj.Var && objNode.getKind() != Obj.Con) {
			report_error("Ime " + des.getNamespaceName() + "::" + des.getDesName() + " ne predstavlja Var/Con", des);
			des.obj = TabExtended.noObj;
			s.append(", nije nadjeno!");
		} else {
			des.obj = objNode;
			s.append(", Nadjeno " + dump(objNode));
		}
			
		log.info(s.toString());
	}
	
	public void visit(DesignatorNoNamespace des) {
		StringBuilder s = new StringBuilder("Pretraga na " + des.getLine() + "(" + des.getDesName() + ")");
		Obj objNode = TabExtended.find(des.getDesName());
		if (objNode == TabExtended.noObj) {
			report_error("Simbol " + des.getDesName() + " nije deklarisan!", des);
			des.obj = TabExtended.noObj;
			s.append(", nije nadjeno!");
		} else {
			if (objNode.getKind() != Obj.Var && objNode.getKind() != Obj.Con) {
				report_error("Ime " + des.getDesName() + " ne predstavlja Var/Con", des);
				des.obj = TabExtended.noObj;
				s.append(", nije nadjeno!");
			} else {
				des.obj = objNode;
				s.append(", Nadjeno " + dump(objNode));
			}
		}
		
		log.info(s.toString());
	}
	
	public void visit(DesignatorWithNamespaceArray des) {
		StringBuilder s = new StringBuilder("Pretraga na " + des.getLine() + "(" + des.getNamespaceName() + "::" + des.getDesName() + ")");
		
		Obj objNode = TabExtended.find(des.getNamespaceName() + "::" + des.getDesName());
		if (objNode == null) {
			report_error("Nije pronadjen prostor imena " + des.getNamespaceName() + " u tabeli simbola!", des);
			des.obj = TabExtended.noObj;
			s.append(", nije nadjeno!");
		} else if (objNode == TabExtended.noObj) {
			report_error("Prostor imena " + des.getNamespaceName() + " nema deklarisan simbol " + des.getDesName() + "!", des);
			des.obj = TabExtended.noObj;
			s.append(", nije nadjeno!");
		} else if (objNode.getKind() != Obj.Var) {
			report_error("Ime " + des.getNamespaceName() + "::" + des.getDesName() + " ne predstavlja Var", des);
			des.obj = TabExtended.noObj;
			s.append(", nije nadjeno!");
		} else if (objNode.getType().getKind() != Struct.Array) {
			report_error("Ime " + des.getNamespaceName() + "::" + des.getDesName() + " ne predstavlja Array", des);
			des.obj = TabExtended.noObj;
			s.append(", nije nadjeno!");
		} else if (des.getExpr().struct != TabExtended.intType) {
			report_error("Index za pristup nizu " + des.getNamespaceName() + "::" + des.getDesName() + " nije int!", des);
			des.obj = TabExtended.noObj;
			s.append(", nije nadjeno!");
		} else {
			s.append(", Nadjeno " + dump(objNode));
			des.obj = new Obj(Obj.Elem, objNode.getName(), objNode.getType().getElemType(), objNode.getAdr(), objNode.getLevel());
		}
		
		log.info(s.toString());
	}
	
	public void visit(DesignatorNoNamespaceArray des) {
		StringBuilder s = new StringBuilder("Pretraga na " + des.getLine() + "(" + des.getDesName() + ")");
		Obj objNode = TabExtended.find(des.getDesName());
		if (objNode == TabExtended.noObj) {
			report_error("Simbol " + des.getDesName() + " nije deklarisan!", des);
			des.obj = TabExtended.noObj;
			s.append(", nije nadjeno!");
		} else {
			if (objNode.getKind() != Obj.Var) {
				report_error("Ime " + des.getDesName() + " ne predstavlja Var", des);
				des.obj = TabExtended.noObj;
				s.append(", nije nadjeno!");
			} else {
				if (objNode.getType().getKind() != Struct.Array) {
					report_error("Ime " + des.getDesName() + " ne predstavlja Array", des);
					des.obj = TabExtended.noObj;
					s.append(", nije nadjeno!");
				} else if (des.getExpr().struct != TabExtended.intType) {
					report_error("Index za pristup nizu " + des.getDesName() + " nije int!", des);
					des.obj = TabExtended.noObj;
					s.append(", nije nadjeno!");
				} else  {
					s.append(", Nadjeno " + dump(objNode));
					des.obj = new Obj(Obj.Elem, objNode.getName(), objNode.getType().getElemType(), objNode.getAdr(), objNode.getLevel());
				}
			}
		}
		
		log.info(s.toString());
	}
	
	public void visit(FactorDesignator f) {
		f.struct = f.getDesignator().obj.getType();
	}
	
	public void visit(FactorNumber f) {
		f.struct = TabExtended.intType;
	}
	
	public void visit(FactorCharacter f) {
		f.struct = TabExtended.charType;
	}
	
	public void visit(FactorBoolean f) {
		f.struct = TabExtended.boolType;
	}
	
	public void visit(FactorNewArray f) {
		if (f.getExpr().struct != TabExtended.intType) {
			report_error("Broj elemenata pri kreiranju niza nije broj!", f);
			f.struct = TabExtended.noType;
		} else f.struct = new Struct(Struct.Array, f.getType().struct);
	}
	
	public void visit(FactorSubexpression f) {
		f.struct = f.getExpr().struct;
	}
	
	public void visit(TermSubunit t) {
		if (t.getFactor().struct != TabExtended.intType) {
			report_error("Faktor mora biti tipa int! (TermSubunit)", t);
		}
		t.struct = t.getFactor().struct;
	}
	
	public void visit(TermSublistEmpty t) {
		t.struct = TabExtended.noType;
	}
	
	public void visit(TermSublistChain t) {
		t.struct = t.getTermSubunit().struct;
	}
	
	public void visit(Term t) {
		if (t.getTermSublist().struct != TabExtended.noType) {
			if (t.getFactor().struct != TabExtended.intType) {
				report_error("Faktor mora biti tipa int! (Term)", t);
			}
			t.struct = TabExtended.intType;
		} else {
			t.struct = t.getFactor().struct;
		}
	}
	
	public void visit(ExprSubunit e) {
		if (e.getTerm().struct != TabExtended.intType) {
			report_error("Term mora biti tipa int! (ExprSubunit)", e);
		}
		e.struct = TabExtended.intType;
	}
	
	public void visit(ExprSublistEmpty e) {
		e.struct = TabExtended.noType;
	}
	
	public void visit(ExprSublistChain e) {
		e.struct = e.getExprSubunit().struct;
	}
	
	public void visit(ExprNegative e) {
		if (e.getTerm().struct != TabExtended.intType) {
			report_error("Term mora biti tipa int! (ExprNegative)", e);
		}
		e.struct = TabExtended.intType;
	}
	
	public void visit(ExprPositive e) {
		if (e.getExprSublist().struct != TabExtended.noType) {
			if (e.getTerm().struct.compatibleWith(e.getExprSublist().struct) && 
					e.getTerm().struct == TabExtended.intType && 
					e.getExprSublist().struct == TabExtended.intType) {}
			else {
				report_error("Term mora biti tipa int! (ExprPositive)", e);
			}
			e.struct = TabExtended.intType;
		} else {
			e.struct = e.getTerm().struct;
		}
	}
	
	public void visit(DesignatorStatementIncrement d) {
		if ((d.getDesignator().obj.getKind() != Obj.Var && d.getDesignator().obj.getKind() != Obj.Elem) || d.getDesignator().obj.getType().getKind() == Struct.Array) {
			report_error("Designator mora biti promenljiva / polje niza!", d);
		} else if (d.getDesignator().obj.getType() != TabExtended.intType) {
			report_error("Designator mora da bude tipa int!", d);
		}
	}
	
	public void visit(DesignatorStatementDecrement d) {
		if ((d.getDesignator().obj.getKind() != Obj.Var && d.getDesignator().obj.getKind() != Obj.Elem) || d.getDesignator().obj.getType().getKind() == Struct.Array) {
			report_error("Designator mora biti promenljiva / polje niza!", d);
		} else if (d.getDesignator().obj.getType() != TabExtended.intType) {
			report_error("Designator mora da bude tipa int!", d);
		}
	}
	
	public void visit(DesignatorStatementAssign d) {
		if (d.getDesignator().obj.getKind() != Obj.Var && d.getDesignator().obj.getKind() != Obj.Elem) {
			report_error("(DSA) Designator mora biti promenljiva / polje niza / niz!", d);
		} else if (!d.getExpr().struct.assignableTo(d.getDesignator().obj.getType())) {
			report_error("Desna strana nije kompatibilna pri dodeli sa levom!", d);
		}
	}
	
	public void visit(StatementPrint s) {
		if (s.getExpr().struct != TabExtended.intType &&
				s.getExpr().struct != TabExtended.charType &&
				s.getExpr().struct != TabExtended.boolType) {
			report_error("Expr mora biti tipa int, char ili bool!", s);
		}
	}
	
	public void visit(StatementPrintFormat s) {
		if (s.getExpr().struct != TabExtended.intType &&
				s.getExpr().struct != TabExtended.charType &&
				s.getExpr().struct != TabExtended.boolType) {
			report_error("Expr mora biti tipa int, char ili bool!", s);
		}
	}
	
	public void visit(StatementRead s) {
		if ((s.getDesignator().obj.getKind() != Obj.Var && s.getDesignator().obj.getKind() != Obj.Elem) || s.getDesignator().obj.getType().getKind() == Struct.Array) {
			report_error("Designator mora biti promenljiva / polje niza!", s);
		}
	}
	
	public boolean passed() {
		return !errorDetected;
	}
}
