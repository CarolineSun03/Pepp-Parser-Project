package prob0719;


import java.util.ArrayList;

public class Translator {
    private final InBuffer b;
    private Tokenizer t;
    private ACode aCode;
    public Translator(InBuffer inBuffer) {
        b = inBuffer;
    }
    // Sets aCode and returns boolean true if end statement is processed.
    private boolean parseLine() {
        boolean terminate = false;
        AArg localoprnd = new DecArg(0);
        Mnemon localaddr = Mnemon.M_i;
        // Compiler requires following useless initialization.
        Mnemon localMnemon = Mnemon.M_END;
        Token aToken;
        aCode = new EmptyInstr();
        ParseState state = ParseState.PS_START;
        do {
            aToken = t.getToken();
            switch (state) {
                case PS_START:
                    if (aToken instanceof Identifier) {
                        Identifier localTIdentifier = (Identifier) aToken;
                        String tempStr = localTIdentifier.getDescription();
                        if (Maps.unaryMnemonTable.containsKey(
                                tempStr.toLowerCase())) {
                            localMnemon = Maps.unaryMnemonTable.get(
                                    tempStr.toLowerCase());
                            aCode = new UnaryInstr(localMnemon);
                            terminate = localMnemon == Mnemon.M_END;
                            state = ParseState.PS_UNARY;
                        } else if (Maps.nonUnaryMnemonTable.containsKey(
                                tempStr.toLowerCase())) {
                            localMnemon = Maps.nonUnaryMnemonTable.get(
                                    tempStr.toLowerCase());
                            state = ParseState.PS_NONUNARY;
                        } else {
                            aCode = new Error(
                                    "Illegal Mnemonic.");
                        }
                    } else if (aToken instanceof Dot){
                        Dot localDot = (Dot) aToken;
                        String tempStr = localDot.getDescription();
                        if(Maps.dotCommandMnemonTable.containsKey(
                                tempStr.toLowerCase())){
                            localMnemon = Maps.dotCommandMnemonTable.get(tempStr.toLowerCase());
                            if(tempStr.toLowerCase().equals("block")){
                                state = ParseState.PS_BLOCK1;
                            }else{
                                state = ParseState.PS_DOT;
                            }
                        }
                    } else if (aToken instanceof Empty) {
                        aCode = new EmptyInstr();
                        state = ParseState.PS_FINISH;
                    } else {
                        aCode = new Error(
                                "Line must begin with an identifier.");
                    }
                    break;
                case PS_UNARY:
                    if(aToken instanceof Empty){
                        aCode = new UnaryInstr(localMnemon);
                        state = ParseState.PS_FINISH;
                    } else {
                        aCode = new Error(
                                "Illegal unary instruction.");
                    }
                    break;
                case PS_NONUNARY:
                    if (aToken instanceof Integer) {
                        Integer localInt = (Integer) aToken;
                        int tempInt = localInt.getValue();
                        localoprnd = new DecArg(tempInt);
                        state = ParseState.PS_OPRND;
                    } else if (aToken instanceof Hex){
                        Hex localHex = (Hex) aToken;
                        int tempHex = localHex.getValue();
                        localoprnd = new HexArg(tempHex);
                        state = ParseState.PS_OPRND;
                    } else {
                        aCode = new Error(
                                "Illegal operand.");
                    }
                    break;
                case PS_OPRND:
                    if (aToken instanceof Addressing) {
                        Addressing localAddressing = (Addressing) aToken;
                        String tempStr = localAddressing.getDescription();
                        if (Maps.addressingModeMnemonTable.containsKey(
                                tempStr.toLowerCase())) {
                            localaddr = Maps.addressingModeMnemonTable.get(
                                    tempStr.toLowerCase());
                            state = ParseState.PS_ADDR;
                        }else{
                            aCode = new Error(
                                    "Illegal addressing mode.");
                        }
                    } else if(aToken instanceof Empty) {
                        aCode = new BRBlockInstr(localMnemon, localoprnd);
                        state = ParseState.PS_FINISH;
                    } else {
                        aCode = new Error(
                                "Illegal addressing mode.");
                    }
                    break;
                case PS_ADDR:
                    if(aToken instanceof Empty){
                        aCode = new NonUnaryInstr(localMnemon, localoprnd, localaddr);
                        state = ParseState.PS_FINISH;
                    } else {
                        aCode = new Error(
                                "Illegal addressing mode.");
                    }
                    break;
                case PS_BLOCK1:
                    if (aToken instanceof Integer) {
                        Integer localInt = (Integer) aToken;
                        int tempInt = localInt.getValue();
                        localoprnd = new DecArg(tempInt);
                        state = ParseState.PS_BLOCK2;
                    } else if (aToken instanceof Hex){
                        Hex localHex = (Hex) aToken;
                        int tempHex = localHex.getValue();
                        localoprnd = new HexArg(tempHex);
                        state = ParseState.PS_BLOCK2;
                    } else {
                        aCode = new Error(
                                "Illegal block instruction.");
                    }
                    break;
                case PS_BLOCK2:
                    if(aToken instanceof Empty) {
                        aCode = new BRBlockInstr(localMnemon, localoprnd);
                        state = ParseState.PS_FINISH;
                    } else {
                        aCode = new Error(
                                "Illegal block instruction.");
                    }
                    break;
                case PS_DOT:
                    if(aToken instanceof Empty){
                        aCode = new UnaryInstr(localMnemon);
                        terminate = true;
                        state = ParseState.PS_FINISH;
                    } else {
                        aCode = new Error(
                                "Illegal dot command.");
                    }
            }
        }while (state != ParseState.PS_FINISH && !(aCode instanceof Error));
        return terminate;
    }

    public void translate() {
        ArrayList<ACode> codeTable = new ArrayList<>();
        int numErrors = 0;
        t = new Tokenizer(b);
        boolean terminateWithEnd = false;
        b.getLine();
        while (b.inputRemains() && !terminateWithEnd) {
            terminateWithEnd = parseLine(); // Sets aCode and returns boolean.
            codeTable.add(aCode);
            if (aCode instanceof Error) {
                numErrors++;
            }
            b.getLine();
        }
        if (!terminateWithEnd) {
            aCode = new Error("Missing \"end\" sentinel.");
            codeTable.add(aCode);
            numErrors++;
        }
        if (numErrors == 0) {
            System.out.printf("Object code:\n");
            for (int i = 0; i < codeTable.size(); i++) {
                System.out.printf("%s", codeTable.get(i).generateCode());
            }
        }
        if (numErrors == 1) {
            System.out.printf("One error was detected.\n");
        } else if (numErrors > 1) {
            System.out.printf("%d errors were detected.\n", numErrors);
        }
        System.out.printf("\nProgram listing:\n");
        for (int i = 0; i < codeTable.size(); i++) {
            System.out.printf("%s", codeTable.get(i).generateListing());
        }
    }
}
