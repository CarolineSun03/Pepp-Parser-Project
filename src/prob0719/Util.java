package prob0719;

public class Util {
    public static boolean isDigit(char ch){
        return ('0' <= ch) && (ch <= '9');
    }

    public static boolean isInt1(char ch){
        return ('0' == ch);
    }

    public static boolean isInt2(char ch){
        return ('1' <= ch) && (ch <= '9');
    }

    public static boolean isAlpha(char ch){
        return (('a' <= ch) && (ch <= 'z'))||(('A' <= ch) && (ch <= 'Z'));
    }

    public static boolean isHex(char ch){
        return ('0' <= ch && '9' >= ch)||('A' <= ch && 'F' >= ch)||('a' <= ch && 'f' >= ch);
    }

    public static boolean isHex1(char ch){
        return ('x' == ch)||('X' == ch);
    }

    public static int hexToDigit(char hexNum) {
        if (('0' <= hexNum) && (hexNum <= '9')) {
            return hexNum - '0';
        } else if (('a' <= hexNum) && (hexNum <= 'f')){
            return hexNum - 'W';
        } else {
            return hexNum - '7';
        }

    }
}
