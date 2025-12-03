import java.util.ArrayList;
import java.util.HashMap;

public class SemanticAnalysis {
    public static HashMap<String, String> SymbolTable = new HashMap<>();

    public static void VISIT_SYMDECLARING(ParsedToken n) {
        String declType = n.getType().equals("floatdcl") ? "float" : "integer";
        EnterSymbol(n.getId(), declType);
    }

    public static void EnterSymbol(String name, String type) {
        if (SymbolTable.get(name) == null) {
            SymbolTable.put(name, type);
        } else {
            System.out.println("SymbolError: duplicate declaration " + name);
            throw new RuntimeException("SymbolError: duplicate declaration " + name);
        }
    }

    public static String LookupSymbol(String name) {
        return SymbolTable.get(name);
    }

    public static void printSymbolTable() {
        System.out.println("Symbol Table:");
        for (var entry : SymbolTable.entrySet()) {
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }
    }

    public static void VISIT_ASSIGNING(ParsedToken n) {
        String leftType = LookupSymbol(n.child1.getId());
        String rightType = n.child2.getType();
        if (leftType == null) {
            System.out.println("Error: variable " + n.child1.getId() + " not declared");
            throw new RuntimeException("Error: variable " + n.child1.getId() + " not declared");
        }
        CONVERT(n.child2, leftType);
    }

    public static void VISIT_INTCONSTING(ParsedToken n) {
        n.Type = "integer";
    }

    public static void VISIT_FLOATCONSTING(ParsedToken n) {
        n.Type = "float";
    }

    public static void CONVERT(ParsedToken n, String t) {
        if (n.getType().equals("float") && t.equals("integer")) {
            System.out.println("Error: Illegal conversion from float to integer");
            throw new RuntimeException("Error: Illegal conversion from float to integer");
        }
    }

    public static void SemanticAnalysis(ArrayList<ParsedToken>  parsedTokens){
        for (ParsedToken currentToken : parsedTokens) {
            switch (currentToken.getType()) {
                case "intdcl":
                case "floatdcl":
                    SemanticAnalysis.VISIT_SYMDECLARING(currentToken);
                    break;
                case "assign":
                    SemanticAnalysis.VISIT_ASSIGNING(currentToken);
                    break;
                case "integer":
                    SemanticAnalysis.VISIT_INTCONSTING(currentToken);
                    break;
                case "float":
                    SemanticAnalysis.VISIT_FLOATCONSTING(currentToken);
                    break;
                case "print":
                    break;
                default:
                    break;
            }
        }
    }
}
