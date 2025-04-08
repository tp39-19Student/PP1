// generated with ast extension for cup
// version 0.8
// 3/1/2024 20:43:13


package rs.ac.bg.etf.pp1.ast;

public class TermSublistChain extends TermSublist {

    private TermSublist TermSublist;
    private TermSubunit TermSubunit;

    public TermSublistChain (TermSublist TermSublist, TermSubunit TermSubunit) {
        this.TermSublist=TermSublist;
        if(TermSublist!=null) TermSublist.setParent(this);
        this.TermSubunit=TermSubunit;
        if(TermSubunit!=null) TermSubunit.setParent(this);
    }

    public TermSublist getTermSublist() {
        return TermSublist;
    }

    public void setTermSublist(TermSublist TermSublist) {
        this.TermSublist=TermSublist;
    }

    public TermSubunit getTermSubunit() {
        return TermSubunit;
    }

    public void setTermSubunit(TermSubunit TermSubunit) {
        this.TermSubunit=TermSubunit;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(TermSublist!=null) TermSublist.accept(visitor);
        if(TermSubunit!=null) TermSubunit.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(TermSublist!=null) TermSublist.traverseTopDown(visitor);
        if(TermSubunit!=null) TermSubunit.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(TermSublist!=null) TermSublist.traverseBottomUp(visitor);
        if(TermSubunit!=null) TermSubunit.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("TermSublistChain(\n");

        if(TermSublist!=null)
            buffer.append(TermSublist.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(TermSubunit!=null)
            buffer.append(TermSubunit.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [TermSublistChain]");
        return buffer.toString();
    }
}
