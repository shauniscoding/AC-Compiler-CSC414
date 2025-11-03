import java.util.ArrayList;

public class Parser {
    public ArrayList<Token> tokenList;
    public Token ts;
    public int currentTokenIndex;
    public ArrayList<ParsedToken> parsedNodes = new ArrayList<>();

    public Parser(ArrayList<Token> tokenList) {
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
        for (ParsedToken token : parsedNodes) {
            System.out.println(token.toString());
        }
    }

    public void advance() {
        this.currentTokenIndex++;
        if (this.currentTokenIndex < this.tokenList.size()) {
            this.ts = tokenList.get(this.currentTokenIndex);
        }
    }

    public void MATCH(String expectedType) {
        if (ts.type.equals(expectedType)) {
            advance();
        } else {
            System.out.println("Error in MATCH(): expected " + expectedType + " but found " + ts.type);
        }
    }

    public void VAL() {
        if (ts.type.equals("id") || ts.type.equals("inum") || ts.type.equals("fnum")) {
            MATCH(ts.type);
        } else {
            System.out.println("Error in VAL(): expected id, inum, or fnum, found " + ts.type);
        }
    }

    public void STMT() {
        if (ts.type.equals("intdcl") || ts.type.equals("floatdcl")) {
            String declType = ts.type;
            MATCH(ts.type);
            String varName = ts.val;
            MATCH("id");
            parsedNodes.add(new ParsedToken(varName, declType));
        } else if (ts.type.equals("id")) {
            String nodeId = ts.val;
            MATCH("id");
            MATCH("assign");
            String nodeVal = ts.val;
            String nodeType = "";
            if (ts.type.equals("inum")) nodeType = "integer";
            if (ts.type.equals("fnum")) nodeType = "float";
            ParsedToken left = new ParsedToken(nodeId, "id");
            ParsedToken right = new ParsedToken(nodeVal, nodeType);
            ParsedToken assignNode = new ParsedToken("assign", "assign");
            assignNode.child1 = left;
            assignNode.child2 = right;
            parsedNodes.add(assignNode);
            VAL();
        } else {
            System.out.println("Error in STMT(): invalid type " + ts.type);
        }
    }

    public void STMTS() {
        while (ts.type.equals("intdcl") || ts.type.equals("floatdcl") || ts.type.equals("id")) {
            STMT();
        }
    }
}
