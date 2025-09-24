import java.util.Set;
public class Scanner {
    //Scanner function to convert string into tokens
    public static Token Scanner(InputStream s){
        Token ans = new Token();
        Set<Character> digits = Set.of('0', '1', '2', '3', '4', '5', '6', '7', '8', '9');

        while(s.Peek() == (' ')){
            s.Advance();
        }
        if(s.is_end_of_file){
            ans.type = "$";
        }
        else{
            if(digits.contains(s.Peek())){
                ans = ScanDigits(s);
            }
            else{
                char ch = s.Advance();

                switch(ch){
                    case 'a': case 'b': case 'c': case 'd': case 'e':
                    case 'g': case 'h': case 'j': case 'k': case 'l':
                    case 'm': case 'n': case 'o': case 'q': case 'r':
                    case 's': case 't': case 'u': case 'v': case 'w':
                    case 'x': case 'y': case 'z':
                        ans.type = "id";
                        ans.val = Character.toString(ch);
                        break;
                    case 'f':
                        ans.type = "floatdcl";
                        break;
                    case 'i':
                        ans.type = "intdcl";
                        break;
                    case 'p':
                        ans.type = "print";
                        break;
                    case '=':
                        ans.type = "assign";
                        break;
                    case '+':
                        ans.type = "plus";
                        break;
                    case '-':
                        ans.type = "minus";
                        break;
                    default:
                        System.out.println("LexicalError in scanner invalid char: " + ch);
                }
            }
        }

        return ans;
    }

    //Helper function to turn numbers into tokens
    public static Token ScanDigits(InputStream s){
        Token tok = new Token();
        tok.val = "";
        Set<Character> digits = Set.of('0', '1', '2', '3', '4', '5', '6', '7', '8', '9');

        while(digits.contains(s.Peek())){
            tok.val += s.Advance();
        }
        if (s.Peek() != '.'){
            tok.type = "inum";
        }
        else{
            tok.type = "fnum";
            do{
                tok.val += s.Advance();
            }while(digits.contains(s.Peek()));
        }

        return tok;
    }
}
