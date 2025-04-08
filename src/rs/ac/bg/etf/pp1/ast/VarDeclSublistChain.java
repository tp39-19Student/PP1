// generated with ast extension for cup
// version 0.8
// 3/1/2024 20:43:13


package rs.ac.bg.etf.pp1.ast;

public class VarDeclSublistChain extends VarDeclSublist {

    private VarDeclSublist VarDeclSublist;
    private VarDeclSubunit VarDeclSubunit;

    public VarDeclSublistChain (VarDeclSublist VarDeclSublist, VarDeclSubunit VarDeclSubunit) {
        this.VarDeclSublist=VarDeclSublist;
        if(VarDeclSublist!=null) VarDeclSublist.setParent(this);
        this.VarDeclSubunit=VarDeclSubunit;
        if(VarDeclSubunit!=null) VarDeclSubunit.setParent(this);
    }

    public VarDeclSublist getVarDeclSublist() {
        return VarDeclSublist;
    }

    public void setVarDeclSublist(VarDeclSublist VarDeclSublist) {
        this.VarDeclSublist=VarDeclSublist;
    }

    public VarDeclSubunit getVarDeclSubunit() {
        return VarDeclSubunit;
    }

    public void setVarDeclSubunit(VarDeclSubunit VarDeclSubunit) {
        this.VarDeclSubunit=VarDeclSubunit;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(VarDeclSublist!=null) VarDeclSublist.accept(visitor);
        if(VarDeclSubunit!=null) VarDeclSubunit.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(VarDeclSublist!=null) VarDeclSublist.traverseTopDown(visitor);
        if(VarDeclSubunit!=null) VarDeclSubunit.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(VarDeclSublist!=null) VarDeclSublist.traverseBottomUp(visitor);
        if(VarDeclSubunit!=null) VarDeclSubunit.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("VarDeclSublistChain(\n");

        if(VarDeclSublist!=null)
            buffer.append(VarDeclSublist.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(VarDeclSubunit!=null)
            buffer.append(VarDeclSubunit.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [VarDeclSublistChain]");
        return buffer.toString();
    }
}
