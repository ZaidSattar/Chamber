public class WordScramble {
    public static void wordScramble(String rem, String scr) {
        if (rem.isEmpty()) {
            System.out.println(scr + rem);
        }
        else {
            for (int i = 0; i < rem.length(); ++i) {
                wordScramble((rem.substring(0, i) + rem.substring(i + 1, rem.length())), (scr + rem.charAt(i)));
            }
        }
    }


    public static void main(String args[]) {
        wordScramble("123", "");
    }
}