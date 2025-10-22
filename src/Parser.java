import java.util.ArrayList;

public class Parser {
    public ArrayList<Token> tokenList;
    public Token ts;
    public int currentTokenIndex;
    public ArrayList<ParsedToken> parsedNodes = new ArrayList<>();


    public void STMT(){
        if(ts.type.equals("id")){
            String nodeId = ts.val; //save id before its consumed

            MATCH("id");
            MATCH("assign");

            String nodeVal = ts.val; //Save the value before its consumed
            String nodeType = "";
            if (ts.type.equals("inum")) {
                nodeType = "integer";
            }
            if (ts.type.equals("fnum")) {
                nodeType = "floatdcl";
            }

            parsedNodes.add(new ParsedToken(nodeId, nodeType, nodeVal));
            VAL();
            EXPR();
        }
        else{
            if(ts.type.equals("print")) {
                MATCH("print");
                String nodeId = ts.val; //save id before its consumed
                MATCH("id");

                parsedNodes.add(new ParsedToken(nodeId, "print", null));
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

        System.out.println("Parsed Tokens: ");
        for(ParsedToken token : parsedNodes){
            System.out.println(token.toString());
        }
    }

    public void advance(){
        this.currentTokenIndex++;
        if (this.currentTokenIndex < this.tokenList.size()) {
            this.ts = tokenList.get(this.currentTokenIndex);
        }
    }

    public void VAL() {
        if (ts.type.equals("id") || ts.type.equals("inum") || ts.type.equals("fnum")) {
            System.out.println("Val(): " + ts.val);
            MATCH(ts.type);
        } else {
            System.out.println("Error in VAL(): expected 'id', 'inum', or 'fnum', found " + ts.type);
        }
    }

    public void EXPR() {
        while (ts.type.equals("plus") || ts.type.equals("minus")) { //THis while loop accounts for mulitple operations ex. 3 + 2 + 1
            System.out.println("Expr(): Found operator " + ts.val);
            MATCH(ts.type);
            VAL(); //This VAL handles any additional values part of the expression after the operator
        }
    }

    public void MATCH(String expectedType) {
        if (ts.type.equals(expectedType)) {
            advance();
        } else {
            System.out.println("Error in MATCH(): expected " + expectedType + " but found " + ts.type);
        }
    }
}
