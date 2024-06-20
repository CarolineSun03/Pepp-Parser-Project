package prob0719;

public class Hex extends Token {
    private final int hexValue;

    public Hex(int i){
        hexValue = i;
    }

    @Override
    public String getDescription(){
        return String.format("");
    }

    public int getValue(){
        return hexValue;
    }

}
