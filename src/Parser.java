import java.util.ArrayList;

public class Parser {
    public ArrayList<Token> tokenList;
    public Token ts;
    public int currentTokenIndex;

    public void STMT(){
        if(ts.type == "id"){
            //Match(ts, id)
            //Match(ts, assign)
            //call VAL()
            //call EXPR()
        }
        else{
            if(ts.type == "print") {
                //Match(ts, print)
                //Match(ts, id)
            }
            else{
                System.out.println("Error in STMT invalid type: " + ts.type);
            }
        }
    }

    public void STMTS(){
        if(ts.type == "id" || ts.type == "print"){
            STMT();
            STMTS();
        }
        else{
            if(ts.type == "$"){
                //Do nothing here
            }
            else{
                System.out.println("Error in STMTS invalid type: " + ts.type);
            }
        }
    }

    public Parser(ArrayList<Token> tokenList){
        this.tokenList = tokenList;
        this.ts = tokenList.get(currentTokenIndex);
    }
    public void advance(){
        this.currentTokenIndex = 1;
    }





}
