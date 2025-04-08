// generated with ast extension for cup
// version 0.8
// 3/1/2024 20:43:13


package rs.ac.bg.etf.pp1.ast;

public class ExprPositive extends Expr {

    private Term Term;
    private ExprSublist ExprSublist;

    public ExprPositive (Term Term, ExprSublist ExprSublist) {
        this.Term=Term;
        if(Term!=null) Term.setParent(this);
        this.ExprSublist=ExprSublist;
        if(ExprSublist!=null) ExprSublist.setParent(this);
    }

    public Term getTerm() {
        return Term;
    }

    public void setTerm(Term Term) {
        this.Term=Term;
    }

    public ExprSublist getExprSublist() {
        return ExprSublist;
    }

    public void setExprSublist(ExprSublist ExprSublist) {
        this.ExprSublist=ExprSublist;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Term!=null) Term.accept(visitor);
        if(ExprSublist!=null) ExprSublist.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Term!=null) Term.traverseTopDown(visitor);
        if(ExprSublist!=null) ExprSublist.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Term!=null) Term.traverseBottomUp(visitor);
        if(ExprSublist!=null) ExprSublist.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ExprPositive(\n");

        if(Term!=null)
            buffer.append(Term.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ExprSublist!=null)
            buffer.append(ExprSublist.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ExprPositive]");
        return buffer.toString();
    }
}
