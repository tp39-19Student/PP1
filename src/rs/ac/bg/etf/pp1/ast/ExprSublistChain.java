// generated with ast extension for cup
// version 0.8
// 3/1/2024 20:43:13


package rs.ac.bg.etf.pp1.ast;

public class ExprSublistChain extends ExprSublist {

    private ExprSublist ExprSublist;
    private ExprSubunit ExprSubunit;

    public ExprSublistChain (ExprSublist ExprSublist, ExprSubunit ExprSubunit) {
        this.ExprSublist=ExprSublist;
        if(ExprSublist!=null) ExprSublist.setParent(this);
        this.ExprSubunit=ExprSubunit;
        if(ExprSubunit!=null) ExprSubunit.setParent(this);
    }

    public ExprSublist getExprSublist() {
        return ExprSublist;
    }

    public void setExprSublist(ExprSublist ExprSublist) {
        this.ExprSublist=ExprSublist;
    }

    public ExprSubunit getExprSubunit() {
        return ExprSubunit;
    }

    public void setExprSubunit(ExprSubunit ExprSubunit) {
        this.ExprSubunit=ExprSubunit;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ExprSublist!=null) ExprSublist.accept(visitor);
        if(ExprSubunit!=null) ExprSubunit.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ExprSublist!=null) ExprSublist.traverseTopDown(visitor);
        if(ExprSubunit!=null) ExprSubunit.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ExprSublist!=null) ExprSublist.traverseBottomUp(visitor);
        if(ExprSubunit!=null) ExprSubunit.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ExprSublistChain(\n");

        if(ExprSublist!=null)
            buffer.append(ExprSublist.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ExprSubunit!=null)
            buffer.append(ExprSubunit.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ExprSublistChain]");
        return buffer.toString();
    }
}
