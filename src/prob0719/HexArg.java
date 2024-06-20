package prob0719;

public class HexArg extends AArg{
    private final int intValue;
    public HexArg(int i) {
        intValue = i;
    }
    @Override
    public String generateCode() {
        return String.format("0x%04X", intValue);
    }
}
