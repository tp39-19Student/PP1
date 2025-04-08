package rs.ac.bg.etf.pp1;

import rs.ac.bg.etf.pp1.ast.FormParamArray;
import rs.ac.bg.etf.pp1.ast.FormParamOne;
import rs.ac.bg.etf.pp1.ast.VarDeclSubunitArray;
import rs.ac.bg.etf.pp1.ast.VarDeclSubunitIdent;
import rs.ac.bg.etf.pp1.ast.VisitorAdaptor;

public class CounterVisitor extends VisitorAdaptor {

	protected int count = 0;
	
	public int getCount() {
		return count;
	}
	
	public static class FormParamCounter extends CounterVisitor {
		public void visit(FormParamOne f) {
			count++;
		}
		public void visit(FormParamArray f) {
			count++;
		}
	}
	
	public static class VarCounter extends CounterVisitor {
		public void visit(VarDeclSubunitIdent v) {
			count++;
		}
		public void visit(VarDeclSubunitArray v) {
			count++;
		}
	}
	
}
