package prob0719;

public class BRBlockInstr extends ACode {
    private final Mnemon mnemonic;
    private final AArg oprndSpec;

    public BRBlockInstr(Mnemon mn, AArg oArg) {
        mnemonic = mn;
        oprndSpec = oArg;
    }
    @Override
    public String generateListing() {
        return String.format("%s" + "%s\n",
                Maps.mnemonStringTable.get(mnemonic),
                oprndSpec.generateCode());
    }
    @Override
    public String generateCode() {
        switch (mnemonic) {
            case M_BR:
                return "12 ";
            default:
                return ""; // Should not occur.
        }
    }
}
