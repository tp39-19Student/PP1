// generated with ast extension for cup
// version 0.8
// 3/1/2024 20:43:13


package rs.ac.bg.etf.pp1.ast;

public interface Visitor { 

    public void visit(VarDeclSubunit VarDeclSubunit);
    public void visit(Mulop Mulop);
    public void visit(GlobalsPart GlobalsPart);
    public void visit(ConstDeclSublist ConstDeclSublist);
    public void visit(GlobalsList GlobalsList);
    public void visit(StatementList StatementList);
    public void visit(NamespaceList NamespaceList);
    public void visit(Addop Addop);
    public void visit(Factor Factor);
    public void visit(Designator Designator);
    public void visit(FormParsList FormParsList);
    public void visit(RetType RetType);
    public void visit(ConstDeclSubunit ConstDeclSubunit);
    public void visit(VarDeclSublist VarDeclSublist);
    public void visit(VarDeclList VarDeclList);
    public void visit(Expr Expr);
    public void visit(DesignatorStatement DesignatorStatement);
    public void visit(Statement Statement);
    public void visit(Type Type);
    public void visit(ExprSublist ExprSublist);
    public void visit(MethodDeclList MethodDeclList);
    public void visit(TermSublist TermSublist);
    public void visit(FormPars FormPars);
    public void visit(FormParam FormParam);
    public void visit(AddopMinus AddopMinus);
    public void visit(AddopPlus AddopPlus);
    public void visit(MulopMod MulopMod);
    public void visit(MulopDiv MulopDiv);
    public void visit(MulopMul MulopMul);
    public void visit(DesignatorStatmentError DesignatorStatmentError);
    public void visit(DesignatorStatementDecrement DesignatorStatementDecrement);
    public void visit(DesignatorStatementIncrement DesignatorStatementIncrement);
    public void visit(DesignatorStatementAssign DesignatorStatementAssign);
    public void visit(DesignatorNoNamespaceArray DesignatorNoNamespaceArray);
    public void visit(DesignatorWithNamespaceArray DesignatorWithNamespaceArray);
    public void visit(DesignatorNoNamespace DesignatorNoNamespace);
    public void visit(DesignatorWithNamespace DesignatorWithNamespace);
    public void visit(FactorSubexpression FactorSubexpression);
    public void visit(FactorNewArray FactorNewArray);
    public void visit(FactorBoolean FactorBoolean);
    public void visit(FactorCharacter FactorCharacter);
    public void visit(FactorNumber FactorNumber);
    public void visit(FactorDesignator FactorDesignator);
    public void visit(TermSubunit TermSubunit);
    public void visit(TermSublistEmpty TermSublistEmpty);
    public void visit(TermSublistChain TermSublistChain);
    public void visit(Term Term);
    public void visit(ExprSubunit ExprSubunit);
    public void visit(ExprSublistEmpty ExprSublistEmpty);
    public void visit(ExprSublistChain ExprSublistChain);
    public void visit(ExprNegative ExprNegative);
    public void visit(ExprPositive ExprPositive);
    public void visit(StatementPrintFormat StatementPrintFormat);
    public void visit(StatementPrint StatementPrint);
    public void visit(StatementRead StatementRead);
    public void visit(StatementDesignator StatementDesignator);
    public void visit(StatementListEmpty StatementListEmpty);
    public void visit(StatementListChain StatementListChain);
    public void visit(VarDeclListEmpty VarDeclListEmpty);
    public void visit(VarDeclListChain VarDeclListChain);
    public void visit(FormParamArray FormParamArray);
    public void visit(FormParamOne FormParamOne);
    public void visit(FormParsListSingle FormParsListSingle);
    public void visit(FormParsListChain FormParsListChain);
    public void visit(FormParsNotEmpty FormParsNotEmpty);
    public void visit(FormParsEmpty FormParsEmpty);
    public void visit(RetTypeVoid RetTypeVoid);
    public void visit(RetTypeType RetTypeType);
    public void visit(MethodTypeName MethodTypeName);
    public void visit(MethodDecl MethodDecl);
    public void visit(MethodDeclListEmpty MethodDeclListEmpty);
    public void visit(MethodDeclListChain MethodDeclListChain);
    public void visit(TypeNamespace TypeNamespace);
    public void visit(TypeIdent TypeIdent);
    public void visit(VarDeclSubunitError VarDeclSubunitError);
    public void visit(VarDeclSubunitArray VarDeclSubunitArray);
    public void visit(VarDeclSubunitIdent VarDeclSubunitIdent);
    public void visit(VarDeclSublistSingle VarDeclSublistSingle);
    public void visit(VarDeclSublistChain VarDeclSublistChain);
    public void visit(VarDecl VarDecl);
    public void visit(ConstDeclSubunitBool ConstDeclSubunitBool);
    public void visit(ConstDeclSubunitChar ConstDeclSubunitChar);
    public void visit(ConstDeclSubunitInt ConstDeclSubunitInt);
    public void visit(ConstDeclSublistSingle ConstDeclSublistSingle);
    public void visit(ConstDeclSublistChain ConstDeclSublistChain);
    public void visit(ConstDecl ConstDecl);
    public void visit(GlobalsPartVar GlobalsPartVar);
    public void visit(GlobalsPartConst GlobalsPartConst);
    public void visit(GlobalsListEmpty GlobalsListEmpty);
    public void visit(GlobalsListChain GlobalsListChain);
    public void visit(NamespaceName NamespaceName);
    public void visit(Namespace Namespace);
    public void visit(NamespaceListEmpty NamespaceListEmpty);
    public void visit(NamespaceListChain NamespaceListChain);
    public void visit(ProgName ProgName);
    public void visit(Program Program);

}
