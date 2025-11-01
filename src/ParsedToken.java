public class ParsedToken {
    String Id;
    String Type;
    ParsedToken child1;
    ParsedToken child2;

    public ParsedToken(String Id, String Type){
        this.Id = Id;
        this.Type = Type;
    }

    public String getId(){
        return this.Id;
    }
    public String getType(){
        return this.Type;
    }

    @Override
    public String toString() {
        return "ParsedToken{type='" + Type + "', id='" + Id + "'}";
    }
}
