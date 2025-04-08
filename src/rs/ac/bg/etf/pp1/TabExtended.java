package rs.ac.bg.etf.pp1;

import java.util.ArrayList;
import java.util.Collection;

import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Scope;
import rs.etf.pp1.symboltable.concepts.Struct;
import rs.etf.pp1.symboltable.visitors.DumpSymbolTableVisitor;
import rs.etf.pp1.symboltable.visitors.SymbolTableVisitor;

public class TabExtended extends Tab {
	public static final Struct boolType = new Struct(Struct.Bool);
	public static final Struct namespaceType = new Struct(Struct.Enum);
	
	public static void init() {
		Tab.init();
		currentScope.addToLocals(new Obj(Obj.Type, "bool", boolType));
	}
	
	public static Obj find(String name) {
		if (!name.contains("::")) return Tab.find(name);
		String namespace = name.substring(0, name.indexOf("::"));
		Obj resultObj = null;
		for (Scope s = currentScope; s != null; s = s.getOuter()) {
			if (s.getLocals() != null) {
				resultObj = s.getLocals().searchKey(namespace);
				if (resultObj != null) break;
			}
		}
		if (resultObj == null) return null;
		if (resultObj.getKind() != Obj.Elem) return null;
		String key = name.substring(name.indexOf("::") + 2);
		
		ArrayList<Obj> list = new ArrayList<>(resultObj.getLocalSymbols());
		for (Obj o : list) {
			if (o.getName().equals(key)) return o;
		}
		return noObj;
	}
	
	public static void dump() {
		dump(new DumpSymbolTableVisitorExtended());
	}
}
