package prob0719;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public final class Maps {
    public static final Map<String, Mnemon> unaryMnemonTable;
    public static final Map<String, Mnemon> nonUnaryMnemonTable;
    public static final Map<String, Mnemon> nonUnaryOprndMnemonTable;

    public static final Map<String, Mnemon> dotCommandMnemonTable;
    public static final Map<String, Mnemon> addressingModeMnemonTable;
    public static final Map<Mnemon, Integer> mnemonDeciTable;
    public static final Map<Mnemon, String> mnemonStringTable;
    static {
        unaryMnemonTable = new HashMap<>();
        unaryMnemonTable.put("stop", Mnemon.M_STOP);
        unaryMnemonTable.put("asla", Mnemon.M_ASLA);
        unaryMnemonTable.put("asra", Mnemon.M_ASRA);

        nonUnaryMnemonTable = new HashMap<>();
        nonUnaryMnemonTable.put("br", Mnemon.M_BR);
        nonUnaryMnemonTable.put("brlt", Mnemon.M_BRLT);
        nonUnaryMnemonTable.put("breq", Mnemon.M_BREQ);
        nonUnaryMnemonTable.put("brle", Mnemon.M_BRLE);
        nonUnaryMnemonTable.put("cpwa", Mnemon.M_CPWA);
        nonUnaryMnemonTable.put("deci", Mnemon.M_DECI);
        nonUnaryMnemonTable.put("deco", Mnemon.M_DECO);
        nonUnaryMnemonTable.put("adda", Mnemon.M_ADDA);
        nonUnaryMnemonTable.put("suba", Mnemon.M_SUBA);
        nonUnaryMnemonTable.put("ldwa", Mnemon.M_LDWA);
        nonUnaryMnemonTable.put("stwa", Mnemon.M_STWA);

        nonUnaryOprndMnemonTable = new HashMap<>();
        nonUnaryOprndMnemonTable.put("br", Mnemon.M_BR);
        nonUnaryOprndMnemonTable.put("brlt", Mnemon.M_BRLT);
        nonUnaryOprndMnemonTable.put("breq", Mnemon.M_BREQ);
        nonUnaryOprndMnemonTable.put("brle", Mnemon.M_BRLE);

        dotCommandMnemonTable = new HashMap<>();
        dotCommandMnemonTable.put("block", Mnemon.M_BLOCK);
        dotCommandMnemonTable.put("end", Mnemon.M_END);

        addressingModeMnemonTable = new HashMap<>();
        addressingModeMnemonTable.put("i", Mnemon.M_i);
        addressingModeMnemonTable.put("d", Mnemon.M_d);
        addressingModeMnemonTable.put("n", Mnemon.M_n);
        addressingModeMnemonTable.put("s", Mnemon.M_s);
        addressingModeMnemonTable.put("sf", Mnemon.M_sf);
        addressingModeMnemonTable.put("x", Mnemon.M_x);
        addressingModeMnemonTable.put("sx", Mnemon.M_sx);
        addressingModeMnemonTable.put("sfx", Mnemon.M_sfx);

        mnemonDeciTable = new EnumMap<>(Mnemon.class);
        mnemonDeciTable.put(Mnemon.M_BR, 18);
        mnemonDeciTable.put(Mnemon.M_BRLE, 20);
        mnemonDeciTable.put(Mnemon.M_BRLT, 22);
        mnemonDeciTable.put(Mnemon.M_BREQ, 24);
        mnemonDeciTable.put(Mnemon.M_CPWA, 160);
        mnemonDeciTable.put(Mnemon.M_DECI, 48);
        mnemonDeciTable.put(Mnemon.M_DECO, 56);
        mnemonDeciTable.put(Mnemon.M_ADDA, 96);
        mnemonDeciTable.put(Mnemon.M_SUBA, 112);
        mnemonDeciTable.put(Mnemon.M_LDWA, 192);
        mnemonDeciTable.put(Mnemon.M_STWA, 224);

        mnemonStringTable = new EnumMap<>(Mnemon.class);
        mnemonStringTable.put(Mnemon.M_STOP, "STOP");
        mnemonStringTable.put(Mnemon.M_ASLA, "ASLA");
        mnemonStringTable.put(Mnemon.M_ASRA, "ASRA");
        mnemonStringTable.put(Mnemon.M_BR, "BR      ");
        mnemonStringTable.put(Mnemon.M_BRLT, "BRLT   ");
        mnemonStringTable.put(Mnemon.M_BREQ, "BREQ   ");
        mnemonStringTable.put(Mnemon.M_BRLE, "BRLE   ");
        mnemonStringTable.put(Mnemon.M_CPWA, "CPWA   ");
        mnemonStringTable.put(Mnemon.M_DECI, "DECI   ");
        mnemonStringTable.put(Mnemon.M_DECO, "DECO   ");
        mnemonStringTable.put(Mnemon.M_ADDA, "ADDA   ");
        mnemonStringTable.put(Mnemon.M_SUBA, "SUBA   ");
        mnemonStringTable.put(Mnemon.M_LDWA, "LDWA   ");
        mnemonStringTable.put(Mnemon.M_STWA, "STWA   ");
        mnemonStringTable.put(Mnemon.M_BLOCK, ".BLOCK   ");
        mnemonStringTable.put(Mnemon.M_END, ".END");
        mnemonStringTable.put(Mnemon.M_i, ",i");
        mnemonStringTable.put(Mnemon.M_d, ",d");
        mnemonStringTable.put(Mnemon.M_n, ",n");
        mnemonStringTable.put(Mnemon.M_s, ",s");
        mnemonStringTable.put(Mnemon.M_sf, ",sf");
        mnemonStringTable.put(Mnemon.M_x, ",x");
        mnemonStringTable.put(Mnemon.M_sx, ",sx");
        mnemonStringTable.put(Mnemon.M_sfx, ",sfx");
    }
}
