package prob0719;

public class UnaryInstr extends ACode{
    private final Mnemon mnemonic;
    public UnaryInstr(Mnemon mn) {
        mnemonic = mn;
    }
    @Override
    public String generateListing() {
        return Maps.mnemonStringTable.get(mnemonic) + "\n";
    }
    @Override
    public String generateCode() {
        switch (mnemonic) {
            case M_STOP:
                return "00\n";
            case M_ASLA:
                return "0A\n";
            case M_ASRA:
                return "0C\n";
            default:
                return ""; // Should not occur.
        }
    }
}
