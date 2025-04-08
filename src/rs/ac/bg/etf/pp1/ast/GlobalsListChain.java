// generated with ast extension for cup
// version 0.8
// 3/1/2024 20:43:13


package rs.ac.bg.etf.pp1.ast;

public class GlobalsListChain extends GlobalsList {

    private GlobalsList GlobalsList;
    private GlobalsPart GlobalsPart;

    public GlobalsListChain (GlobalsList GlobalsList, GlobalsPart GlobalsPart) {
        this.GlobalsList=GlobalsList;
        if(GlobalsList!=null) GlobalsList.setParent(this);
        this.GlobalsPart=GlobalsPart;
        if(GlobalsPart!=null) GlobalsPart.setParent(this);
    }

    public GlobalsList getGlobalsList() {
        return GlobalsList;
    }

    public void setGlobalsList(GlobalsList GlobalsList) {
        this.GlobalsList=GlobalsList;
    }

    public GlobalsPart getGlobalsPart() {
        return GlobalsPart;
    }

    public void setGlobalsPart(GlobalsPart GlobalsPart) {
        this.GlobalsPart=GlobalsPart;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(GlobalsList!=null) GlobalsList.accept(visitor);
        if(GlobalsPart!=null) GlobalsPart.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(GlobalsList!=null) GlobalsList.traverseTopDown(visitor);
        if(GlobalsPart!=null) GlobalsPart.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(GlobalsList!=null) GlobalsList.traverseBottomUp(visitor);
        if(GlobalsPart!=null) GlobalsPart.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("GlobalsListChain(\n");

        if(GlobalsList!=null)
            buffer.append(GlobalsList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(GlobalsPart!=null)
            buffer.append(GlobalsPart.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [GlobalsListChain]");
        return buffer.toString();
    }
}
