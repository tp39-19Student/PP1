// generated with ast extension for cup
// version 0.8
// 3/1/2024 20:43:13


package rs.ac.bg.etf.pp1.ast;

public class ConstDeclSublistChain extends ConstDeclSublist {

    private ConstDeclSublist ConstDeclSublist;
    private ConstDeclSubunit ConstDeclSubunit;

    public ConstDeclSublistChain (ConstDeclSublist ConstDeclSublist, ConstDeclSubunit ConstDeclSubunit) {
        this.ConstDeclSublist=ConstDeclSublist;
        if(ConstDeclSublist!=null) ConstDeclSublist.setParent(this);
        this.ConstDeclSubunit=ConstDeclSubunit;
        if(ConstDeclSubunit!=null) ConstDeclSubunit.setParent(this);
    }

    public ConstDeclSublist getConstDeclSublist() {
        return ConstDeclSublist;
    }

    public void setConstDeclSublist(ConstDeclSublist ConstDeclSublist) {
        this.ConstDeclSublist=ConstDeclSublist;
    }

    public ConstDeclSubunit getConstDeclSubunit() {
        return ConstDeclSubunit;
    }

    public void setConstDeclSubunit(ConstDeclSubunit ConstDeclSubunit) {
        this.ConstDeclSubunit=ConstDeclSubunit;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ConstDeclSublist!=null) ConstDeclSublist.accept(visitor);
        if(ConstDeclSubunit!=null) ConstDeclSubunit.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ConstDeclSublist!=null) ConstDeclSublist.traverseTopDown(visitor);
        if(ConstDeclSubunit!=null) ConstDeclSubunit.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ConstDeclSublist!=null) ConstDeclSublist.traverseBottomUp(visitor);
        if(ConstDeclSubunit!=null) ConstDeclSubunit.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ConstDeclSublistChain(\n");

        if(ConstDeclSublist!=null)
            buffer.append(ConstDeclSublist.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ConstDeclSubunit!=null)
            buffer.append(ConstDeclSubunit.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConstDeclSublistChain]");
        return buffer.toString();
    }
}
