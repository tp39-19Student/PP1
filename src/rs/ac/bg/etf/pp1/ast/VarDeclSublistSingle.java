// generated with ast extension for cup
// version 0.8
// 3/1/2024 20:43:13


package rs.ac.bg.etf.pp1.ast;

public class VarDeclSublistSingle extends VarDeclSublist {

    private VarDeclSubunit VarDeclSubunit;

    public VarDeclSublistSingle (VarDeclSubunit VarDeclSubunit) {
        this.VarDeclSubunit=VarDeclSubunit;
        if(VarDeclSubunit!=null) VarDeclSubunit.setParent(this);
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
        if(VarDeclSubunit!=null) VarDeclSubunit.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(VarDeclSubunit!=null) VarDeclSubunit.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(VarDeclSubunit!=null) VarDeclSubunit.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("VarDeclSublistSingle(\n");

        if(VarDeclSubunit!=null)
            buffer.append(VarDeclSubunit.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [VarDeclSublistSingle]");
        return buffer.toString();
    }
}
