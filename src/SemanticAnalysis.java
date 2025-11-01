import java.util.HashMap;

public class SemanticAnalysis {
    public static HashMap<String, String> SymbolTable = new HashMap<String, String>();

    //CC3 section

    //This takes a new token that has the type to distinguish between float and integer
    //It also has the id variable name to store into the table
    public static void VISIT_SYMDECLARING(ParsedToken n){
        if(n.getType().equals("floatdcl")){
            EnterSymbol(n.getId(), "floatdcl");
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


    //CC4 section
    public static void VISIT_COMPUTING(ParsedToken n){
        n.Type = CONSISTENT(n.child1, n.child2);
    }

    public static void VISIT_ASSIGNING(ParsedToken n){
        n.Type = CONVERT(n.child2, n.child1.getType());
    }

    public static void VISIT_SYMREFERENCING(ParsedToken n){
        n.Type = LookupSymbol(n.getId());
    }

    public static void VISIT_INTCONSTING(ParsedToken n){
        n.Type = "integer";
    }

    public static void VISIT_FLOATCONSTING(ParsedToken n){
        n.Type = "floatdcl";
    }

    public static String CONSISTENT(ParsedToken c1, ParsedToken c2){
        String m = GENERALIZE(c1.getType(), c2.getType());
        CONVERT(c1, m);
        CONVERT(c2, m);
        return m;
    }

    public static String GENERALIZE(String t1, String t2){
        if(t1.equals("floatdcl") || t2.equals("floatdcl")){
            return "floatdcl";
        }
        else{
            return "integer";
        }
    }

    public static String CONVERT(ParsedToken n, String t){
        if(n.getType().equals("floatdcl") && t.equals("integer")){
            System.out.println("Error in Semantic Analysis: Illegal type conversion from float to integer");
        }
        if(n.getType().equals("integer") && t.equals("floatdcl")){
            n = new ParsedToken(n.getId(), "floatdcl");
            return "floatdcl";
        }
        return t;
    }













}
