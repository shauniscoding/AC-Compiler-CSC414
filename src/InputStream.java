public class InputStream {
    String input_value;
    int stream_index;
    boolean is_end_of_file;

    public InputStream(String input_value){
        this.input_value = input_value;
        this.stream_index = 0;
        this.is_end_of_file = false;
    }

    public char Peek(){
        return input_value.charAt(stream_index);
    }

    public char Advance(){
        char current_char = input_value.charAt(stream_index);
        this.stream_index++;
        if(this.stream_index >= input_value.length()){
            this.is_end_of_file = true;
            System.out.println("Reached end of file");
        }

        return current_char;
    }
}
