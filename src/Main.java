import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        String test_input = "i a " +
                "f b " +
                "a = 4 " +
                "b = a + 3.2 " +
                "p b";
        ArrayList<Token> tokenList = Scanner.getTokenList(test_input);
        Parser parser = new Parser(tokenList);
        parser.Parse();

        ArrayList<ParsedToken> parsedTokens = parser.parsedNodes;
        SemanticAnalysis.SemanticAnalysis(parsedTokens);
        SemanticAnalysis.printSymbolTable();

        CodeGeneration.SymbolTable = SemanticAnalysis.SymbolTable;
        for (ParsedToken currentToken : parsedTokens) {
            String type = currentToken.getType();
            if (!type.equals("intdcl") && !type.equals("floatdcl")) {
                CodeGeneration.CodeGen(currentToken);
            }
        }
    }
}