import java.util.HashMap;

public class SemanticAnalysis {
    public static HashMap<String, String> SymbolTable = new HashMap<String, String>();

    //This takes a new token that has the type to distinguish between float and integer
    //It also has the id variable name to store into the table
    public static void VISIT(ParsedToken n){
        if(n.getType() == "floatdcl"){
            EnterSymbol(n.getId(), "float");
        }
        else{
            EnterSymbol(n.getId(), "integer");
        }
    }

    public static void EnterSymbol(String name, String type){
        if(SymbolTable.get(name) == null){
            SymbolTable.put(name, type);
        }
        else{
            System.out.println("SymbolError in Symbol Table repeated value: duplicate declaration " + name);
        }
    }

    public static String LookupSymbol(String name){
        return SymbolTable.get(name);
    }

    public static void printSymbolTable(){
        System.out.println("Symbol Table:");
        for (var entry : SymbolTable.entrySet()) {
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }
    }

}
