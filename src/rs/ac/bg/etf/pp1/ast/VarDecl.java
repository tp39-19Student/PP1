// generated with ast extension for cup
// version 0.8
// 3/1/2024 20:43:13


package rs.ac.bg.etf.pp1.ast;

public class VarDecl implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private Type Type;
    private VarDeclSublist VarDeclSublist;

    public VarDecl (Type Type, VarDeclSublist VarDeclSublist) {
        this.Type=Type;
        if(Type!=null) Type.setParent(this);
        this.VarDeclSublist=VarDeclSublist;
        if(VarDeclSublist!=null) VarDeclSublist.setParent(this);
    }

    public Type getType() {
        return Type;
    }

    public void setType(Type Type) {
        this.Type=Type;
    }

    public VarDeclSublist getVarDeclSublist() {
        return VarDeclSublist;
    }

    public void setVarDeclSublist(VarDeclSublist VarDeclSublist) {
        this.VarDeclSublist=VarDeclSublist;
    }

    public SyntaxNode getParent() {
        return parent;
    }

    public void setParent(SyntaxNode parent) {
        this.parent=parent;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line=line;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Type!=null) Type.accept(visitor);
        if(VarDeclSublist!=null) VarDeclSublist.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Type!=null) Type.traverseTopDown(visitor);
        if(VarDeclSublist!=null) VarDeclSublist.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Type!=null) Type.traverseBottomUp(visitor);
        if(VarDeclSublist!=null) VarDeclSublist.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("VarDecl(\n");

        if(Type!=null)
            buffer.append(Type.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(VarDeclSublist!=null)
            buffer.append(VarDeclSublist.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [VarDecl]");
        return buffer.toString();
    }
}
