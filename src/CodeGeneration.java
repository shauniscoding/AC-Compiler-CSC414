import java.util.ArrayList;
import java.util.HashMap;

public class CodeGeneration {

    public static HashMap<String, String> SymbolTable;

    public static void CodeGen(ParsedToken n){

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

    //come back to this one
    public static void VISIT_COMPUTING(ParsedToken n){
        CodeGen(n.child1);
        CodeGen(n.child2);

        //figure out how to put the operation value in here
        EMIT("s");
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
        EMIT(SymbolTable.get(n.getId()));
    }

}
