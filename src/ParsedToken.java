public class ParsedToken {
    String Id;
    String Type;
    String Val;


    public ParsedToken(String Id, String Type, String Val){
        this.Id = Id;
        this.Type = Type;
        this.Val = Val;
    }

    public String getId(){
        return this.Id;
    }
    public String getType(){
        return this.Type;
    }
    public String getVal(){
        return this.Val;
    }

    @Override
    public String toString() {
        return "ParsedToken{type='" + Type + "', id='" + Id + "', val='" + Val + "'}";
    }
}
