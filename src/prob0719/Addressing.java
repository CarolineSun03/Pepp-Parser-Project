package prob0719;

public class Addressing extends Token{
    private final String stringValue;

    public Addressing(StringBuffer stringBuffer){
        stringValue = new String(stringBuffer);
    }

    @Override
    public String getDescription(){
        return String.format("%s", stringValue);
    }

    @Override
    public int getValue(){
        return 0;
    }
}
