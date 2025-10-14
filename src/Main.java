import java.util.ArrayList;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        String test_input = "x = 5";
        ArrayList<Token> tokenList = Scanner.getTokenList(test_input);
        Parser parser = new Parser (tokenList);
        parser.Parse();
    }
}