package prob0719;

public class Invalid extends Token{
    @Override
    public String getDescription(){
        return "Syntax error";
    }
    @Override
    public int getValue(){
        return 0;
    }
}
