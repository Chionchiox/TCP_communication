public class Colori {
    /* STILI */
    public static final String RESET = "\u001B[0m";
    public static final String BOLD = "\u001B[1m";
    public static final String ITALIC = "\u001B[3m";

    /* COLORI */
    public static final String GRAY = "\u001B[90m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String WHITE = "\u001B[37m";
    public static final String BG_RED = "\u001B[41m";

    public static void PRINT_ERROR(String testo){
        System.err.println(BG_RED + testo + RESET);
    }

    public static void PRINT_COMUNICATION(String testo){
        System.out.println(GRAY + ITALIC + testo + RESET);
    }

    public static void PRINT_MESSAGE(String testo){
        System.out.println(BOLD + GREEN + testo + RESET);
    }
}

