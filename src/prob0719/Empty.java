package prob0719;

public class Empty extends Token{
    @Override
    public String getDescription(){
        return "Empty token";
    }
    @Override
    public int getValue(){
        return 0;
    }
}
