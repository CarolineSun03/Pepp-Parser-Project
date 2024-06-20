package prob0719;

public class Integer extends Token{
    private final int intValue;

    public Integer(int i){
        intValue = i;
    }

    @Override
    public String getDescription(){ return ""; }

    @Override
    public int getValue(){
        return intValue;
    }
}
