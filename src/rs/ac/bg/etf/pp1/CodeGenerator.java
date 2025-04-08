package rs.ac.bg.etf.pp1;

import rs.ac.bg.etf.pp1.ast.VisitorAdaptor;

import org.apache.log4j.Logger;

import rs.ac.bg.etf.pp1.CounterVisitor.FormParamCounter;
import rs.ac.bg.etf.pp1.CounterVisitor.VarCounter;
import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.mj.runtime.*;
import rs.etf.pp1.symboltable.*;
import rs.etf.pp1.symboltable.concepts.*;

public class CodeGenerator extends VisitorAdaptor {
	private int mainPc;
	
	Logger log = Logger.getLogger(getClass());
	
	public int getMainPc() {
		return mainPc;
	}
	
	public void visit(StatementPrint s) {
		if (s.getExpr().struct == TabExtended.intType) {
			Code.loadConst(5);
			Code.put(Code.print);
		} else if (s.getExpr().struct == TabExtended.charType || s.getExpr().struct == TabExtended.boolType) {
			Code.loadConst(1);
			Code.put(Code.bprint);
		}
	}
	
	public void visit(StatementPrintFormat s) {
		if (s.getExpr().struct == TabExtended.intType) {
			Code.loadConst(s.getValue());
			Code.put(Code.print);
		} else if (s.getExpr().struct == TabExtended.charType || s.getExpr().struct == TabExtended.boolType) {
			Code.loadConst(s.getValue());
			Code.put(Code.bprint);
		}
	}
	
	public void visit(StatementRead s) {
		if (s.getDesignator().obj.getType().getKind() == Struct.Int) Code.put(Code.read);
		else Code.put(Code.bread);
		
		if (s.getDesignator().obj.getKind() == Obj.Elem) {
			if (s.getDesignator().obj.getType().getKind() == Struct.Int) {
				Code.put(Code.astore);
			} else {
				Code.put(Code.bastore);
			}
		} else {
			Code.store(s.getDesignator().obj);
		}
	}
	
	public void visit(FactorNumber f) {
		Obj o = new Obj(Obj.Con, "$", f.struct);
		o.setLevel(0);
		o.setAdr(f.getValue());
		
		Code.load(o);
	}
	
	public void visit(FactorCharacter f) {
		Obj o = new Obj(Obj.Con, "$", f.struct);
		o.setLevel(0);
		o.setAdr(f.getValue());
		
		Code.load(o);
	}
	
	public void visit(FactorBoolean f) {
		Obj o = new Obj(Obj.Con, "$", f.struct);
		o.setLevel(0);
		o.setAdr(f.getValue()?'1':'0');
		
		Code.load(o);
	}
	
	public void visit(MethodTypeName m) {
		if (m.getName().equalsIgnoreCase("main")) {
			mainPc = Code.pc;
		}
		m.obj.setAdr(Code.pc);
		
		SyntaxNode methodNode = m.getParent();
		
		FormParamCounter fpc = new FormParamCounter();
		VarCounter vc = new VarCounter();
		
		methodNode.traverseTopDown(fpc);
		methodNode.traverseTopDown(vc);
		
		int formParamCount = fpc.getCount();
		int varCount = vc.getCount();
		
		Code.put(Code.enter);
		Code.put(formParamCount);
		Code.put(varCount + formParamCount);
	}
	
	public void visit(MethodDecl m) {
		Code.put(Code.exit);
		Code.put(Code.return_);
	}
	
	public void visit(DesignatorStatementAssign d) {
		if (d.getDesignator().obj.getKind() == Obj.Elem) {
			if (d.getDesignator().obj.getType().getKind() == Struct.Int) {
				Code.put(Code.astore);
			} else {
				Code.put(Code.bastore);
			}
		} else {
			Code.store(d.getDesignator().obj);
		}
	}
	
	public void visit(DesignatorStatementIncrement d) {
		if (d.getDesignator().obj.getLevel() != 0 && d.getDesignator().obj.getKind() != Obj.Elem) {
			Code.put(Code.inc);
			Code.put(d.getDesignator().obj.getAdr());
			Code.put(1);
		} else {
			if (d.getDesignator().obj.getKind() == Obj.Elem) {
				Code.put(Code.dup2);
				if (d.getDesignator().obj.getType().getKind() == Struct.Int) Code.put(Code.aload);
				else Code.put(Code.baload);
				Code.loadConst(1);
				Code.put(Code.add);
				if (d.getDesignator().obj.getType().getKind() == Struct.Int) Code.put(Code.astore);
				else Code.put(Code.bastore);
			} else {
				Code.load(d.getDesignator().obj);
				Code.loadConst(1);
				Code.put(Code.add);
				Code.store(d.getDesignator().obj);
			}
		}
	}
	
	public void visit(DesignatorStatementDecrement d) {
		if (d.getDesignator().obj.getLevel() != 0 && d.getDesignator().obj.getKind() != Obj.Elem) {
			Code.put(Code.inc);
			Code.put(d.getDesignator().obj.getAdr());
			Code.put(-1);
		} else {
			if (d.getDesignator().obj.getKind() == Obj.Elem) {
				Code.put(Code.dup2);
				if (d.getDesignator().obj.getType().getKind() == Struct.Int) Code.put(Code.aload);
				else Code.put(Code.baload);
				Code.loadConst(-1);
				Code.put(Code.add);
				if (d.getDesignator().obj.getType().getKind() == Struct.Int) Code.put(Code.astore);
				else Code.put(Code.bastore);
			} else {
				Code.load(d.getDesignator().obj);
				Code.loadConst(-1);
				Code.put(Code.add);
				Code.store(d.getDesignator().obj);
			}
		}
	}
	
	public void visit(DesignatorWithNamespaceArray d) {
		Code.load(new Obj(Obj.Var, "$", TabExtended.noType, d.obj.getAdr(), d.obj.getLevel()));
		Code.put(Code.dup_x1);
		Code.put(Code.pop);
	}
	
	public void visit(DesignatorNoNamespaceArray d) {
		Code.load(new Obj(Obj.Var, "$", TabExtended.noType, d.obj.getAdr(), d.obj.getLevel()));
		Code.put(Code.dup_x1);
		Code.put(Code.pop);
	}
	
	public void visit(FactorDesignator f) {
		if (f.getDesignator().obj.getKind() == Obj.Elem ) {
			if (f.getDesignator().obj.getType().getKind() == Struct.Int) {
				Code.put(Code.aload);
			} else {
				Code.put(Code.baload);
			}
		} else {
			Code.load(f.getDesignator().obj);
		}
	}
	
	public void visit(TermSubunit t) {
		switch(t.getMulop().getClass().getSimpleName()) {
		case "MulopMul":
			Code.put(Code.mul);
			break;
		case "MulopDiv":
			Code.put(Code.div);
			break;
		case "MulopMod":
			Code.put(Code.rem);
			break;
		}
	}
	
	public void visit(ExprSubunit e) {
		switch(e.getAddop().getClass().getSimpleName()) {
		case "AddopPlus":
			Code.put(Code.add);
			break;
		case "AddopMinus":
			Code.put(Code.sub);
			break;
		}
	}
	
	public void visit(FactorNewArray f) {
		Code.put(Code.newarray);

		if (f.getType().struct == TabExtended.intType) {
			Code.put(1);
		} else {
			Code.put(0);
		}
	}
	
	public void visit(Term t) {
		if (t.getParent().getClass() == ExprNegative.class) {
			Code.put(Code.neg);
		}
	}
	
	
}
