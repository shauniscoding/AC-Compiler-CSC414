import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static String readInputFile(String fileName) {
        Path filePath = Paths.get(fileName);

        try {
            String content = Files.readString(filePath);
            content = content.replaceAll("\\s+", " ").trim();

            return content;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void main(String[] args) {

        String input = readInputFile("ACinput6.txt");
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