import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        String test_input = "f x x = 312.4";
        ArrayList<Token> tokenList = Scanner.getTokenList(test_input);
        Parser parser = new Parser(tokenList);
        parser.Parse();

        ArrayList<ParsedToken> parsedTokens = parser.parsedNodes;
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
                default:
                    break;
            }
        }
        SemanticAnalysis.printSymbolTable();
    }
}
