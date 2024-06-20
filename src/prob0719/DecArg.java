package prob0719;

public class DecArg extends AArg{
    private final int intValue;
    public DecArg(int i) {
        intValue = i;
    }
    @Override
    public String generateCode() {
        return String.format("%d", intValue);
    }
}
