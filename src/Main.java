import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        String test_input = "x = 5 + 3 + 312";
        ArrayList<Token> tokenList = Scanner.getTokenList(test_input);
        Parser parser = new Parser (tokenList);
        parser.Parse();


        ArrayList<ParsedToken> parsedTokens = new ArrayList<ParsedToken>();
        for(ParsedToken currentToken : parsedTokens){
            SemanticAnalysis.VISIT(currentToken);
        }
        SemanticAnalysis.printSymbolTable();

    }
}