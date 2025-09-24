import java.util.Set;

public class Main {
    public static void main(String[] args) {
        String test_input = "a = 1 + 2";
        InputStream s = new InputStream(test_input);
        Token token;
        do {
            token = Scanner.Scanner(s);
            System.out.println("type:" + token.type + " val:" + token.val);
        } while(!token.type.equals("$"));
    }
}