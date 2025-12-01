import java.util.ArrayList;
import java.util.HashMap;

public class CodeGeneration {

    public static HashMap<String, String> SymbolTable;

    public static void CodeGen(ParsedToken n){
        if (n == null) {
            return;
        }

        String type = n.getType();

        switch (type) {
            case "assign":
                VISIT_ASSIGNING(n);
                break;
            case "compute":
                VISIT_COMPUTING(n);
                break;
            case "id":
                VISIT_SYMREFERENCING(n);
                break;
            case "print":
                VISIT_PRINTING(n);
                break;
            case "converting":
                VISIT_CONVERTING(n);
                break;
            case "integer":
            case "float":
                VISIT_CONSTING(n);
                break;
            default:
                System.out.println("Unknown node type in CodeGen: " + type);
                break;
        }
    }

    public static void EMIT(String output){
        System.out.println(output);
    }

    public static void VISIT_ASSIGNING(ParsedToken n){
        CodeGen(n.child2);
        EMIT("s");
        EMIT(n.child1.getId());
        EMIT("0 k");
    }

    public static void VISIT_COMPUTING(ParsedToken n){
        CodeGen(n.child1);
        CodeGen(n.child2);
        EMIT(n.operation);
    }

    public static void VISIT_SYMREFERENCING(ParsedToken n){
        EMIT("l");
        EMIT(n.getId());
    }

    public static void VISIT_PRINTING(ParsedToken n){
        EMIT("l");
        EMIT(n.getId());
        EMIT("p");
        EMIT("si");
    }

    public static void VISIT_CONVERTING(ParsedToken n){
        CodeGen(n.child1);
        EMIT("5 k");
    }

    public static void VISIT_CONSTING(ParsedToken n){
        EMIT(n.getId());
    }

}