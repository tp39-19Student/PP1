// generated with ast extension for cup
// version 0.8
// 3/1/2024 20:43:13


package rs.ac.bg.etf.pp1.ast;

public class MulopMod extends Mulop {

    public MulopMod () {
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
        buffer.append("MulopMod(\n");

        buffer.append(tab);
        buffer.append(") [MulopMod]");
        return buffer.toString();
    }
}
