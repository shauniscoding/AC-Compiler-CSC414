import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        //java -cp out/production/"AC Compiler" Main ACinput1.txt

        if (args.length == 0) {
            System.out.println("No filepath specified");
            return;
        }
        String inputFile = args[0];
        String input = InputStream.readInputFile(inputFile);

        ArrayList<Token> tokenList = Scanner.getTokenList(input);
        Parser parser = new Parser(tokenList);
        parser.Parse();

        ArrayList<ParsedToken> parsedTokens = parser.parsedNodes;
        SemanticAnalysis.SemanticAnalysis(parsedTokens);

        CodeGeneration.SymbolTable = SemanticAnalysis.SymbolTable;
        for (ParsedToken currentToken : parsedTokens) {
            String type = currentToken.getType();
            if (!type.equals("intdcl") && !type.equals("floatdcl")) {
                CodeGeneration.CodeGen(currentToken);
            }
        }
    }
}