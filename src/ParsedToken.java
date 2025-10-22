public class ParsedToken {
    String Id;
    String Type;

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

}
