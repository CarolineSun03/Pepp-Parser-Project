package prob0719;

public class NonUnaryInstr extends ACode{
    private final Mnemon mnemonic;
    private final AArg oprndSpec;
    private final Mnemon addrMode;

    public NonUnaryInstr(Mnemon mn, AArg oArg, Mnemon aArg) {
        mnemonic = mn;
        oprndSpec = oArg;
        addrMode = aArg;
    }
    @Override
    public String generateListing() {
        return String.format("%s" + "%s" + "%s\n",
                Maps.mnemonStringTable.get(mnemonic),
                oprndSpec.generateCode(),
                Maps.mnemonStringTable.get(addrMode));
    }
    @Override
    public String generateCode() {
        switch (mnemonic) {
            default:
                return ""; // Should not occur.
        }
    }
}
