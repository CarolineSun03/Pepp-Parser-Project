package prob0719;

public class Tokenizer {
    private final InBuffer b;

    public Tokenizer(InBuffer inBuffer){
        b = inBuffer;
    }

    char nextChar;
    StringBuffer localStringValue = new StringBuffer("");
    int localIntValue = 0;
    int sign = +1;
    Token aToken = new Empty();
    LexState state = LexState.LS_START;

    public Token getToken(){
        char nextChar;
        StringBuffer localStringValue = new StringBuffer("");
        int localIntValue = 0;
        int sign = +1;
        Token aToken = new Empty();
        LexState state = LexState.LS_START;
        do {
            nextChar = b.advanceInput();
            switch (state){
                case LS_START:
                    if (Util.isAlpha(nextChar)) {
                        localStringValue.append(nextChar);
                        state = LexState.LS_IDENT;
                    } else if(nextChar == '-') {
                        sign = -1;
                        state = LexState.LS_SIGN;
                    } else if(nextChar == '+') {
                        sign = +1;
                        state = LexState.LS_SIGN;
                    } else if(Util.isInt1(nextChar)) {
                        localIntValue = nextChar - '0';
                        state = LexState.LS_INT1;
                    } else if(Util.isInt2(nextChar)) {
                        localIntValue = nextChar - '0';
                        state = LexState.LS_INT2;
                    } else if(nextChar == '.') {
                        state = LexState.LS_DOT1;
                    } else if(nextChar == ','){
                        state = LexState.LS_ADDR1;
                    } else if(nextChar == '\n') {
                        state = LexState.LS_STOP;
                    } else if(nextChar != ' ') {
                        aToken = new Invalid();
                    }
                    break;
                case LS_IDENT:
                    if (Util.isAlpha(nextChar) || Util.isDigit(nextChar)) {
                        localStringValue.append(nextChar);
                    } else {
                        b.backUpInput();
                        aToken = new Identifier(localStringValue);
                        state = LexState.LS_STOP;
                    }
                    break;
                case LS_SIGN:
                    if (Util.isDigit(nextChar)) {
                        localIntValue = 10 * localIntValue + nextChar - '0';
                        state = LexState.LS_INT2;
                    } else {
                        aToken = new Invalid();
                    }
                    break;
                case LS_INT1:
                    if (Util.isHex1(nextChar)) {
                        state = LexState.LS_HEX1;
                    } else if(Util.isDigit(nextChar)){
                        localIntValue = 10 * localIntValue + nextChar - '0';
                        state = LexState.LS_INT2;
                    } else {
                        b.backUpInput();
                        state = LexState.LS_STOP;
                    }
                    break;
                case LS_INT2:
                    if (Util.isDigit(nextChar)){
                        localIntValue = 10 * localIntValue + nextChar - '0';
                        if(localIntValue < -32768 || localIntValue > 65535){
                            aToken = new Invalid();
                            break;
                        }
                        state = LexState.LS_INT2;
                    } else {
                        b.backUpInput();
                        aToken = new Integer(sign * localIntValue);
                        state = LexState.LS_STOP;
                    }
                    break;
                case LS_HEX1:
                    if(Util.isHex(nextChar)){
                        localIntValue = 16 * localIntValue + Util.hexToDigit(nextChar);
                        if(localIntValue < 0 || localIntValue > 65535){
                            aToken = new Invalid();
                            break;
                        }
                        state = LexState.LS_HEX2;
                    }else{
                        aToken = new Invalid();
                    }
                    break;
                case LS_HEX2:
                    if(Util.isHex(nextChar)){
                        localIntValue = 16 * localIntValue + Util.hexToDigit(nextChar);
                        state = LexState.LS_HEX2;
                    }else{
                        b.backUpInput();
                        aToken = new Hex(localIntValue);
                        state = LexState.LS_STOP;
                    }
                    break;
                case LS_DOT1:
                    if(Util.isAlpha(nextChar)){
                        localStringValue.append(nextChar);
                        state = LexState.LS_DOT2;
                    } else {
                        aToken = new Invalid();
                    }
                    break;
                case LS_DOT2:
                    if(Util.isAlpha(nextChar)){
                        localStringValue.append(nextChar);
                        state = LexState.LS_DOT2;
                    } else {
                        b.backUpInput();
                        aToken = new Dot(localStringValue);
                        state = LexState.LS_STOP;
                    }
                    break;
                case LS_ADDR1:
                    if(Util.isAlpha(nextChar)){
                        localStringValue.append(nextChar);
                        state = LexState.LS_ADDR2;
                    } else if(nextChar == ' '){

                    }else {
                        aToken = new Invalid();
                    }
                    break;
                case LS_ADDR2:
                    if(Util.isAlpha(nextChar)){
                        localStringValue.append(nextChar);
                        state = LexState.LS_ADDR2;
                    } else {
                        b.backUpInput();
                        aToken = new Addressing(localStringValue);
                        state = LexState.LS_STOP;
                    }
                    break;
            }
        } while((state != LexState.LS_STOP) && !(aToken instanceof Invalid));
        return aToken;
    }

}
