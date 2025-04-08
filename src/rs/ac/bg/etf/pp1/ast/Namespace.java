// generated with ast extension for cup
// version 0.8
// 3/1/2024 20:43:13


package rs.ac.bg.etf.pp1.ast;

public class Namespace implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private NamespaceName NamespaceName;
    private GlobalsList GlobalsList;
    private MethodDeclList MethodDeclList;

    public Namespace (NamespaceName NamespaceName, GlobalsList GlobalsList, MethodDeclList MethodDeclList) {
        this.NamespaceName=NamespaceName;
        if(NamespaceName!=null) NamespaceName.setParent(this);
        this.GlobalsList=GlobalsList;
        if(GlobalsList!=null) GlobalsList.setParent(this);
        this.MethodDeclList=MethodDeclList;
        if(MethodDeclList!=null) MethodDeclList.setParent(this);
    }

    public NamespaceName getNamespaceName() {
        return NamespaceName;
    }

    public void setNamespaceName(NamespaceName NamespaceName) {
        this.NamespaceName=NamespaceName;
    }

    public GlobalsList getGlobalsList() {
        return GlobalsList;
    }

    public void setGlobalsList(GlobalsList GlobalsList) {
        this.GlobalsList=GlobalsList;
    }

    public MethodDeclList getMethodDeclList() {
        return MethodDeclList;
    }

    public void setMethodDeclList(MethodDeclList MethodDeclList) {
        this.MethodDeclList=MethodDeclList;
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
        if(NamespaceName!=null) NamespaceName.accept(visitor);
        if(GlobalsList!=null) GlobalsList.accept(visitor);
        if(MethodDeclList!=null) MethodDeclList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(NamespaceName!=null) NamespaceName.traverseTopDown(visitor);
        if(GlobalsList!=null) GlobalsList.traverseTopDown(visitor);
        if(MethodDeclList!=null) MethodDeclList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(NamespaceName!=null) NamespaceName.traverseBottomUp(visitor);
        if(GlobalsList!=null) GlobalsList.traverseBottomUp(visitor);
        if(MethodDeclList!=null) MethodDeclList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Namespace(\n");

        if(NamespaceName!=null)
            buffer.append(NamespaceName.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(GlobalsList!=null)
            buffer.append(GlobalsList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(MethodDeclList!=null)
            buffer.append(MethodDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Namespace]");
        return buffer.toString();
    }
}
