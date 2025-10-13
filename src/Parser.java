import java.util.ArrayList;

public class Parser {
    public ArrayList<Token> tokenList;
    public Token ts;
    public int currentTokenIndex;

    public void STMT(){
        if(ts.type.equals("id")){
            MATCH("id");
            MATCH("assign");
            VAL();
            EXPR();
        }
        else{
            if(ts.type.equals("print")) {
                MATCH("print");
                MATCH("id");
            }
            else{
                System.out.println("Error in STMT invalid type: " + ts.type);
            }
        }
    }

    public void STMTS(){
        if(ts.type.equals("id") || ts.type.equals("print")){
            STMT();
            STMTS();
        }
        else{
            if(ts.type.equals("$")){
                //Do nothing here
            }
            else{
                System.out.println("Error in STMTS invalid type: " + ts.type);
            }
        }
    }

    public Parser(ArrayList<Token> tokenList){
        this.tokenList = tokenList;
        this.currentTokenIndex = 0;
        this.ts = tokenList.get(currentTokenIndex);
    }

    public void Parse() {
        STMTS();
        if (!ts.type.equals("$")) {
            System.out.println("Extra Tokens");
        } else {
            System.out.println("Parsing complete.");
        }
    }

    public void advance(){
        this.currentTokenIndex++;
        if (this.currentTokenIndex < this.tokenList.size()) {
            this.ts = tokenList.get(this.currentTokenIndex);
        }
    }

    public void VAL() {
        if (ts.type.equals("num") || ts.type.equals("id")) {
            System.out.println("Val(): Found value " + ts.val);
            MATCH(ts.type);  // consume it
        } else {
            System.out.println("Error in Val(): expected 'num' or 'id' but found '" + ts.type + "'");
        }
    }

    public void EXPR() {
        while (ts.type.equals("op")) {
            System.out.println("Expr(): Found operator " + ts.val);
            MATCH("op");
            VAL();
        }
    }

    public void MATCH(String expectedType) {
        if (ts.type.equals(expectedType)) {
            advance();
        } else {
            System.out.println("Syntax Error: expected " + expectedType + " but found " + ts.type);
        }
    }

}
