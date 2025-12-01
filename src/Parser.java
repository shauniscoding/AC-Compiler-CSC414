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

    public ParsedToken VAL() {
        ParsedToken node = null;
        if (ts.type.equals("id")) {
            node = new ParsedToken(ts.val, "id");
            MATCH("id");
        } else if (ts.type.equals("inum")) {
            node = new ParsedToken(ts.val, "integer");
            MATCH("inum");
        } else if (ts.type.equals("fnum")) {
            node = new ParsedToken(ts.val, "float");
            MATCH("fnum");
        } else {
            System.out.println("Error in VAL(): expected id, inum, or fnum, found " + ts.type);
        }
        return node;
    }

    public ParsedToken EXPR() {
        if (ts.type.equals("plus") || ts.type.equals("minus")) {
            String operation = ts.type;
            MATCH(ts.type);

            ParsedToken rightVal = VAL();
            ParsedToken rightExpr = EXPR();

            if (rightExpr != null) {
                ParsedToken opNode = new ParsedToken(operation, "compute");
                opNode.operation = operation;
                opNode.child1 = rightVal;
                opNode.child2 = rightExpr;
                return opNode;
            } else {
                ParsedToken opNode = new ParsedToken(operation, "compute");
                opNode.operation = operation;
                opNode.child1 = rightVal;
                opNode.child2 = null;
                return opNode;
            }
        }
        return null;
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

            ParsedToken valNode = VAL();
            ParsedToken exprNode = EXPR();

            ParsedToken left = new ParsedToken(nodeId, "id");
            ParsedToken right;

            if (exprNode != null) {
                right = new ParsedToken(exprNode.operation, "compute");
                right.operation = exprNode.operation;
                right.child1 = valNode;
                right.child2 = exprNode.child1;

                if (exprNode.child2 != null) {
                    right.child2 = new ParsedToken(exprNode.operation, "compute");
                    right.child2.operation = exprNode.operation;
                    right.child2.child1 = exprNode.child1;
                    right.child2.child2 = exprNode.child2;
                }
            } else {
                right = valNode;
            }

            ParsedToken assignNode = new ParsedToken("assign", "assign");
            assignNode.child1 = left;
            assignNode.child2 = right;
            parsedNodes.add(assignNode);

        } else if (ts.type.equals("print")) {
            MATCH("print");
            String varName = ts.val;
            MATCH("id");
            ParsedToken printNode = new ParsedToken(varName, "print");
            parsedNodes.add(printNode);

        } else {
            System.out.println("Error in STMT(): invalid type " + ts.type);
        }
    }

    public void STMTS() {
        if (ts.type.equals("intdcl") || ts.type.equals("floatdcl") ||
                ts.type.equals("id") || ts.type.equals("print")) {
            STMT();
            STMTS();
        } else if (ts.type.equals("$")) {
        } else {
            System.out.println("Error in STMTS(): unexpected token " + ts.type);
        }
    }
}