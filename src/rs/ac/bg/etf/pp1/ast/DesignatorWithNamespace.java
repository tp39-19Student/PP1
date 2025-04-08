// generated with ast extension for cup
// version 0.8
// 3/1/2024 20:43:13


package rs.ac.bg.etf.pp1.ast;

public class DesignatorWithNamespace extends Designator {

    private String namespaceName;
    private String desName;

    public DesignatorWithNamespace (String namespaceName, String desName) {
        this.namespaceName=namespaceName;
        this.desName=desName;
    }

    public String getNamespaceName() {
        return namespaceName;
    }

    public void setNamespaceName(String namespaceName) {
        this.namespaceName=namespaceName;
    }

    public String getDesName() {
        return desName;
    }

    public void setDesName(String desName) {
        this.desName=desName;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesignatorWithNamespace(\n");

        buffer.append(" "+tab+namespaceName);
        buffer.append("\n");

        buffer.append(" "+tab+desName);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesignatorWithNamespace]");
        return buffer.toString();
    }
}
